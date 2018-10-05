/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.atos.qrowd.processors.nifiCKANprocessor;

import net.atos.qrowd.handlers.CKAN_API_Handler;
import org.apache.nifi.annotation.behavior.ReadsAttribute;
import org.apache.nifi.annotation.behavior.ReadsAttributes;
import org.apache.nifi.annotation.documentation.CapabilityDescription;
import org.apache.nifi.annotation.documentation.Tags;
import org.apache.nifi.components.AllowableValue;
import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.components.Validator;
import org.apache.nifi.flowfile.FlowFile;
import org.apache.nifi.flowfile.attributes.CoreAttributes;
import org.apache.nifi.logging.LogLevel;
import org.apache.nifi.processor.*;
import org.apache.nifi.processor.exception.ProcessException;
import org.apache.nifi.processor.util.StandardValidators;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Tags({"ckan","web service","request","local"})
@CapabilityDescription("Nifi Processor that will upload the specified flowfile to CKAN through its API, it will create the organization and package if needed.")
@ReadsAttributes
({@ReadsAttribute(attribute = "filename", description = "The filename to use when writing the FlowFile to disk."),
@ReadsAttribute(attribute = "ckan_package_name", description= "The name of the CKAN package to store the flowfile into")}
)
public class CKAN_Flowfile_Uploader extends AbstractProcessor {

    private static final AllowableValue PRIVATE_TRUE = new AllowableValue("True", "Private", "Marks the package as private");
    private static final AllowableValue PRIVATE_FALSE = new AllowableValue("False", "Public", "Marks the package as public");


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
    private static final PropertyDescriptor organization_id = new PropertyDescriptor
            .Builder().name("organization_id")
            .displayName("Organization id to add the file to")
            .description("Organization id to add the package to, or create if necessary. Must contain only alphanumeric characters.")
            .addValidator(StandardValidators.NON_EMPTY_VALIDATOR)
            .required(true)
            .build();
    private static final PropertyDescriptor package_name = new PropertyDescriptor
            .Builder().name("package_name")
            .displayName("Name of the package to add the file to")
            .description("Name of the package to add the package to, or create if necessary. Must contain only alphanumeric characters. In case this is empty, the name of the file will be used.")
            .addValidator(Validator.VALID)
            .required(false)
            .build();
    private static final PropertyDescriptor package_description = new PropertyDescriptor
            .Builder().name("package_description")
            .displayName("Description of the package to add the file to")
            .description("Description of the package to add the package to.")
            .addValidator(StandardValidators.NON_EMPTY_VALIDATOR)
            .expressionLanguageSupported(true)
            .required(false)
            .build();
    private static final PropertyDescriptor package_private = new PropertyDescriptor.Builder()
            .name("Package visibility")
            .description("Select if the package to be created will be marked as private or public")
            .expressionLanguageSupported(false)
            .allowableValues(PRIVATE_TRUE, PRIVATE_FALSE)
            .defaultValue(PRIVATE_TRUE.getValue())
            .required(true)
            .build();

    private static final Relationship REL_SUCCESS = new Relationship.Builder()
            .name("SUCCESS")
            .description("Success relationship")
            .build();
    private static final Relationship REL_FAILURE = new Relationship.Builder()
            .name("failure")
            .description(
                    "Any flowfile that cannot be processed, either because an error with the flowfile or an error with the CKAN Api")
            .build();

    private List<PropertyDescriptor> descriptors;

    private Set<Relationship> relationships;

    @Override
    protected void init(final ProcessorInitializationContext context) {
        final List<PropertyDescriptor> descriptors = new ArrayList<>();
        descriptors.add(CKAN_url);
        descriptors.add(api_key);
        descriptors.add(organization_id);
        descriptors.add(package_name);
        descriptors.add(package_description);
        descriptors.add(package_private);

        this.descriptors = Collections.unmodifiableList(descriptors);

        final Set<Relationship> relationships = new HashSet<>();
        relationships.add(REL_SUCCESS);
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
        //This is the way to get the value of a property
        String url = context.getProperty(CKAN_url).getValue();

        String filename = flowFile.getAttribute(CoreAttributes.FILENAME.key());

        String tDir = System.getProperty("java.io.tmpdir");
        String path = tDir + "/"+filename;
        Path file = Paths.get(path);
        session.exportTo(flowFile, file, false);

        final String apiKey = context.getProperty(api_key).getValue();
        final String packageDescription = context.getProperty(package_description).evaluateAttributeExpressions(flowFile).getValue();
        final boolean packagePrivate;
        packagePrivate = context.getProperty(package_private).getValue().equals("True");


        String datasetName = flowFile.getAttribute("ckan_package_name");

        //If the property package_name is not filled, then use the filename (without extension) as package name
        //ToDo: Fix an error, the processor throws null pointer when package_name property is empty
        String filenameNoExtension = "";

        // We can either use the attribute, the property or the filename as package name
        // The order of priority should be property -> attribute -> filename
        if(datasetName != null && datasetName.length()>0)
        {
            filenameNoExtension = datasetName;
            getLogger().info("Dataset name got from attribute: "+filenameNoExtension);
        }else if(context.getProperty(package_name).isSet())
        {
            filenameNoExtension =context.getProperty(package_name).getValue();
            getLogger().info("Dataset name got from processor property: "+filenameNoExtension);
        }
        //Check if the property is filled with spaces, empty, or null to use the file name as filename
        if(filenameNoExtension == null || filenameNoExtension.isEmpty() || filenameNoExtension.trim().length()==0)
        {
            filenameNoExtension=getFileName(filename);
            getLogger().info("Dataset name got from filename: "+filenameNoExtension);
        }
        final String organizationId = context.getProperty(organization_id).getValue();

        //  *******************
        //   Main logic of the CKAN uploader
        // - Create the CKAN API Handler
        // - Check that the target organization exists in CKAN
        //      - If it doesn't, create it
        // - Check if the package exists in CKAN
        //      - If it doesn't, create it
        // - Upload the file to CKAN, with it's filename as ID
        // -- In case of any exception in the process, send the flowfile to FAILURE.
        // *********************

        CKAN_API_Handler ckan_api_handler = new CKAN_API_Handler(url, apiKey, filenameNoExtension, organizationId, packageDescription, packagePrivate);
        try {
            if (!ckan_api_handler.organizationExists()) {
                ckan_api_handler.createOrganization();
            }
            if (!ckan_api_handler.packageExists(filenameNoExtension)) {
                ckan_api_handler.createPackage(filenameNoExtension);
            }
            if(ckan_api_handler.createOrUpdateResource(file.toFile().toString())) {
                getLogger().info("File tried to be uploaded to CKAN: {}", new Object[]{file.toFile().toString()});
                session.transfer(flowFile, REL_SUCCESS);
                ckan_api_handler.close();
            }else
            {
                session.transfer(session.penalize(flowFile), REL_FAILURE);
            }
        }catch(IOException ioe)
        {
            getLogger().log(LogLevel.ERROR, "Error while uploading file {} to CKAN {}: Organization {}.",
                    new Object[]{file, url, organizationId });
            getLogger().error(ioe.toString());
            session.transfer(session.penalize(flowFile), REL_FAILURE);
        }


        // It is critical that we commit the session before we perform the Delete. Otherwise, we could have a case where we
        // ingest the file, delete it, and then NiFi is restarted before the session is committed. That would result in data loss.
        // As long as we commit the session right here, we are safe.
        session.commit();
    }


    private String getFileName(String file){

        getLogger().log(LogLevel.INFO,"Filename to be processed: " + file);
        return file.split("\\.")[0];
    }
}
