/**
 * Copyright 2018 Atos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.atos.qrowd.processors.nifiCKANDatasetBackup;

import net.atos.qrowd.handlers.CKAN_API_Handler;
import net.atos.qrowd.pojos.Package_;
import net.atos.qrowd.pojos.Resource;
import org.apache.nifi.annotation.behavior.EventDriven;
import org.apache.nifi.annotation.behavior.ReadsAttribute;
import org.apache.nifi.annotation.behavior.SupportsBatching;
import org.apache.nifi.annotation.documentation.CapabilityDescription;
import org.apache.nifi.annotation.documentation.Tags;
import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.components.Validator;
import org.apache.nifi.flowfile.FlowFile;
import org.apache.nifi.logging.LogLevel;
import org.apache.nifi.processor.*;
import org.apache.nifi.processor.exception.ProcessException;
import org.apache.nifi.processor.util.StandardValidators;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@EventDriven
@SupportsBatching
@Tags({"ckan","backup","web service","request","local"})
@CapabilityDescription("Nifi Processor that will look into CKAN for a package named as the filename of the flowfile. If not found, output flowfile to NOT_FOUND relationship. If found it will create a backup of the dataset and all its resources with a new name (dated/timestamped).")
@ReadsAttribute(attribute = "filename", description = "The filename to use when writing the FlowFile to disk.")
public class CKAN_Package_Backup extends AbstractProcessor {

    private static final PropertyDescriptor CKAN_url = new PropertyDescriptor
            .Builder().name("CKAN_url")
            .displayName("CKAN Url")
            .description("Hostname of the CKAN instance to write to")
            .addValidator(StandardValidators.URL_VALIDATOR)
            .required(true)
            .build();
    private static final PropertyDescriptor api_key = new PropertyDescriptor
            .Builder().name("Api_Key")
            .displayName("File Api_Key")
            .description("Api Key to be used to interact with CKAN")
            .addValidator(StandardValidators.NON_EMPTY_VALIDATOR)
            .required(true)
            .sensitive(true)
            .build();
    private static final PropertyDescriptor package_name = new PropertyDescriptor
            .Builder().name("package_name")
            .displayName("Name of the package to backup")
            .description("Name of the package to be backed up")
            .addValidator(Validator.VALID)
            .required(true)
            .build();
    private static final PropertyDescriptor tag_list = new PropertyDescriptor
            .Builder().name("tag_list")
            .displayName("Comma-separated Tag List")
            .description("Comma-separated tag list to be set for the dataset. Only alphanumeric characters and '_' accepted")
            .addValidator(Validator.VALID)
            .required(false)
            .build();

    private static final Relationship REL_BACKUP_CREATED = new Relationship.Builder()
            .name("BACKUP_SUCCESS")
            .description("Package found and backup successful")
            .build();
    private static final Relationship REL_NO_PACKAGE = new Relationship.Builder()
            .name("NO_PACKAGE_FOUND")
            .description("No package was found with that name")
            .build();
    private static final Relationship REL_FAILURE = new Relationship.Builder()
            .name("failure")
            .description(
                    "Any flowfile that causes an error using the CKAN API or any exception in the processor")
            .build();

    private List<PropertyDescriptor> descriptors;

    private Set<Relationship> relationships;

    @Override
    protected void init(final ProcessorInitializationContext context) {
        final List<PropertyDescriptor> descriptors = new ArrayList<>();
        descriptors.add(CKAN_url);
        descriptors.add(api_key);
        descriptors.add(package_name);
        descriptors.add(tag_list);

        this.descriptors = Collections.unmodifiableList(descriptors);

        final Set<Relationship> relationships = new HashSet<>();
        relationships.add(REL_BACKUP_CREATED);
        relationships.add(REL_NO_PACKAGE);
        relationships.add(REL_FAILURE);
        this.relationships = Collections.unmodifiableSet(relationships);
    }

    @Override
    public Set<Relationship> getRelationships() {
        return this.relationships;
    }

    @Override
    public final List<PropertyDescriptor> getSupportedPropertyDescriptors() {
        return descriptors;
    }

    @Override
    public void onTrigger(final ProcessContext context, final ProcessSession session) throws ProcessException {
        FlowFile flowFile = session.get();
        if (flowFile == null) {
            return;
        }

        //Get the package name to be backed up from the properties
        String packageName = context.getProperty(package_name).getValue();

        String url = context.getProperty(CKAN_url).getValue();
        final String apiKey = context.getProperty(api_key).getValue();

        String tagList = context.getProperty(tag_list).getValue();

        /* *****************
         * Main logic of the CKAN package backup:
         *  - Look in CKAN for a package with the same name as the file
         *  - If not found, output flowfile via not_found relationship
         *  - If found:
         *      - Create a new package with the dated name
         *      - Iterate over all the resources, getting the files from the url and upload the to the dated package
         *      - Output flowfile via success so the next processor can update CKAN (do this here?)
         ******************** */

        CKAN_API_Handler ckan_api_handler = new CKAN_API_Handler(url, apiKey);
        try{
            getLogger().info("Getting the information of package with name: {}",new Object[]{packageName});
            Package_ dataset = ckan_api_handler.getPackageByName(packageName);
            //When package cannot be found on CKAN, returns null
            if(dataset!=null)
            {
                //if found...
                // get the resources linked to this dataset
                List<Resource> resourceList = dataset.getResources();

                //Format the date to something compatible with the CKAN name restrictions (alphanumeric or these symbols: -_ )
                DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                String timeStamp = LocalDateTime.now().format(formatter);

                String datasetName = dataset.getName()+timeStamp;

                getLogger().info("Creating the package: {}", new Object[]{datasetName});
                //Create the new timestamped package
                ckan_api_handler.createPackagePojoNoResources(dataset,datasetName,tagList);

                //Check when the list of resources of the package is empty
                if(resourceList.size()>0) {
                    //For each resource, create a timestamped backup in the previous package
                    for (Resource res : resourceList) {
                        String fileExtension = res.getName().split("\\.")[1];
                        String fileName = res.getName().split("\\.")[0];

                        String resourceFileName = fileName+timeStamp+"."+fileExtension;

                        getLogger().info("Uploading to dataset: {} the resource: {}",new Object[]{datasetName,resourceFileName});
                        ckan_api_handler.uploadFilePojo(res, datasetName, resourceFileName);
                    }
                }
                //Transfer the input file through success relationship
                session.transfer(flowFile, REL_BACKUP_CREATED);
                ckan_api_handler.close();
            }else
            {
                //if not found
                session.transfer(flowFile, REL_NO_PACKAGE);
                ckan_api_handler.close();
            }
            session.commit();
            getLogger().info("Processor finished completely");
        }catch(IOException ioe) {
            getLogger().log(LogLevel.ERROR, "Error while using the CKAN API");
            getLogger().error(ioe.toString());
            session.transfer(session.penalize(flowFile), REL_FAILURE);
        }catch(ArrayIndexOutOfBoundsException oob)
        {
            getLogger().log(LogLevel.ERROR, "Error while splitting the resource filename, it contains no '.'");
            getLogger().error(oob.toString());
            session.transfer(session.penalize(flowFile), REL_FAILURE);
        }catch(Exception e)
        {
            getLogger().error("Unexpected error");
            getLogger().error(e.toString());
            session.transfer(session.penalize(flowFile), REL_FAILURE);
        }
        session.commit();
    }
}
