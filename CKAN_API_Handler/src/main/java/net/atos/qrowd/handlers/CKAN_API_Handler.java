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
package net.atos.qrowd.handlers;

import com.google.gson.Gson;
import net.atos.qrowd.pojos.CkanFullList;
import net.atos.qrowd.pojos.Package_;
import net.atos.qrowd.pojos.Resource;
import net.atos.qrowd.pojos.ResourceResponse;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CKAN_API_Handler {
    private final Logger log = Logger.getLogger(CKAN_API_Handler.class);

    private String HOST;
    private String api_key;
    private String package_id;
    private String organization_id;
    private String package_description;
    private CloseableHttpClient httpclient;
    private Boolean package_private;

    public CKAN_API_Handler(String HOST, String api_key, String filename, String organization_id, String package_description, Boolean package_private) {
        this.HOST = HOST;
        this.api_key = api_key;
        this.package_id = filename.toLowerCase();
        this.package_description = package_description;
        this.organization_id = organization_id.toLowerCase();
        this.package_private = package_private;

        this.httpclient = HttpClientBuilder.create().build();
    }

    public CKAN_API_Handler(String HOST, String api_key)
    {
        this.HOST = HOST;
        this.api_key = api_key;

        this.httpclient = HttpClientBuilder.create().build();
    }

    // ToDo: Check if the package exists marked as delete, then reactivate it?
    public boolean packageExists() throws IOException{

        String line;
        StringBuilder sb = new StringBuilder();
        HttpPost postRequest;

        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("id",new StringBody(package_id,ContentType.TEXT_PLAIN))
                .build();

        postRequest = new HttpPost(HOST+"/api/action/package_show");
        postRequest.setEntity(reqEntity);
        postRequest.setHeader("X-CKAN-API-Key", api_key);

        HttpResponse response = httpclient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }

        if(statusCode==200)
        {
            log.info("Package with id "+package_id+" exists");
            //Check if that package is deleted

            log.info(sb);
            return true;
        }else{
            log.warn("Package with id "+package_id+" not found");
            log.warn(sb);
            return false;
        }
    }
    public boolean packageExists(String id) throws IOException{

        String line;
        StringBuilder sb = new StringBuilder();
        HttpPost postRequest;

        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("id",new StringBody(id,ContentType.TEXT_PLAIN))
                .build();

        postRequest = new HttpPost(HOST+"/api/action/package_show");
        postRequest.setEntity(reqEntity);
        postRequest.setHeader("X-CKAN-API-Key", api_key);

        HttpResponse response = httpclient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }

        if(statusCode==200)
        {
            log.info("Package with id "+id+" exists");
            //Check if that package is deleted

            log.info(sb);
            return true;
        }else{
            log.warn("Package with id "+id+" not found");
            log.warn(sb);
            return false;
        }
    }

    public Package_ getPackageByName(String name) throws IOException {
        HttpPost postRequest;
        StringBuilder sb = new StringBuilder();
        String line;

        Gson gson = new Gson();

        //query the API to get the resources with that file name
        postRequest = new HttpPost(HOST+"/api/3/action/package_search?q=name:"+name);
        postRequest.setHeader("X-CKAN-API-Key", api_key);

        HttpResponse response = httpclient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        // Parse the response into a POJO to be able to get results from it.
        // ToDo: If no result is returned, raise an error (when converting to POJO fails or return code !=200?)
        if(statusCode==200) {
            CkanFullList CkanFullList = gson.fromJson(sb.toString(), CkanFullList.class);
            //by default we get the first package_ of the list of packages
            if (CkanFullList.getPackage().getPackages().size() == 1) {
                log.info("Package: "+name+" was found in CKAN.");
                return CkanFullList.getPackage().getPackages().get(0);
            } else {
                log.warn("Package: "+name+" not found");
                //ToDo: Null, really?
                return null;
            }
        }else{
            return null; //........
        }

    }

    public void createPackage() throws IOException{

        HttpPost postRequest;
        StringBuilder sb = new StringBuilder();
        String line;

        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("name",new StringBody(package_id,ContentType.TEXT_PLAIN))
                .addPart("owner_org",new StringBody(organization_id,ContentType.TEXT_PLAIN))
                .addPart("notes",new StringBody(package_description,ContentType.TEXT_PLAIN))
                .addPart("private",new StringBody(package_private.toString(),ContentType.TEXT_PLAIN))
                .build();

        postRequest = new HttpPost(HOST+"/api/3/action/package_create?use_default_schema=true");
        postRequest.setEntity(reqEntity);
        postRequest.setHeader("X-CKAN-API-Key", api_key);

        HttpResponse response = httpclient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        String message = EntityUtils.toString(response.getEntity());
        log.warn("***** Response: "+message);

        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        sb.append(statusCode);
        sb.append("\n");
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        //ToDo: Save the returned package to store it's alfanumerical id (to be later used when updating the file)
        if(statusCode!=200){
            log.error("statusCode =!=" +statusCode);
            log.error("Error creating the package via CKAN API. Package id: "+package_id);
            log.error(sb);
        }
        else {
            log.info("Request returns statusCode 200: OK");
            log.info(sb);
        }
    }
    public void createPackagePojo(Package_ dataset, String name) throws IOException{

        HttpPost postRequest;
        StringBuilder sb = new StringBuilder();
        String line;

        MultipartEntityBuilder multipart = MultipartEntityBuilder.create()
                .addPart("name",new StringBody(name,ContentType.TEXT_PLAIN));
        // ToDo: Improve this way of handling null values in the returned dataset
                if(dataset.getAuthor()!=null) {
                    multipart.addPart("author", new StringBody(dataset.getAuthor(), ContentType.TEXT_PLAIN));
                }
                if(dataset.getAuthorEmail()!=null) {
                    multipart.addPart("author_email", new StringBody(dataset.getAuthorEmail(), ContentType.TEXT_PLAIN));
                }
                if(dataset.getOwnerOrg()!=null) {
                    multipart.addPart("owner_org", new StringBody(dataset.getOwnerOrg(), ContentType.TEXT_PLAIN));
                }
                if(dataset.getNotes()!=null) {
                    multipart.addPart("notes", new StringBody(dataset.getNotes(), ContentType.TEXT_PLAIN));
                }
                if(dataset.getPrivate()!=null) {
                    multipart.addPart("private", new StringBody(dataset.getPrivate().toString(), ContentType.TEXT_PLAIN));
                }
                if(dataset.getLicenseTitle()!=null) {
                    multipart.addPart("license_title", new StringBody(dataset.getLicenseTitle(), ContentType.TEXT_PLAIN));
                }
                if(dataset.getLicenseId()!=null) {
                    multipart.addPart("license_id", new StringBody(dataset.getLicenseId(), ContentType.TEXT_PLAIN));
                }

        HttpEntity reqEntity = multipart.build();

        postRequest = new HttpPost(HOST+"/api/action/package_create");
        postRequest.setEntity(reqEntity);
        postRequest.setHeader("X-CKAN-API-Key", api_key);

        HttpResponse response = httpclient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        sb.append(statusCode);
        sb.append("\n");
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        if(statusCode!=200){
            log.error("statusCode =!=" +statusCode);
            log.error(sb);
        }
        else {
            log.info("Request returns statusCode 200: OK");
            log.info(sb);
        }
    }

    public boolean organizationExists() throws IOException{
        String line;
        StringBuilder sb = new StringBuilder();
        HttpPost postRequest;

        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("id",new StringBody(organization_id,ContentType.TEXT_PLAIN))
                .build();

        postRequest = new HttpPost(HOST+"/api/action/organization_show");
        postRequest.setEntity(reqEntity);
        postRequest.setHeader("X-CKAN-API-Key", api_key);

        HttpResponse response = httpclient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }

        if(statusCode==200)
        {
            log.info("Organization with id "+organization_id+" exists");
            log.info(sb);
            return true;
        }else{
            log.warn("Organization with id "+organization_id+" not found");
            log.warn(sb);
            return false;
        }
    }

    public void createOrganization() throws IOException{

        HttpPost postRequest;
        StringBuilder sb = new StringBuilder();
        String line;

        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("name", new StringBody(organization_id, ContentType.TEXT_PLAIN))
                .addPart("id", new StringBody(organization_id, ContentType.TEXT_PLAIN))
                .addPart("title", new StringBody(organization_id, ContentType.TEXT_PLAIN))
                .build();

        postRequest = new HttpPost(HOST + "/api/action/organization_create");
        postRequest.setEntity(reqEntity);
        postRequest.setHeader("X-CKAN-API-Key", api_key);

        HttpResponse response = httpclient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        sb.append(statusCode);
        sb.append("\n");
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        if (statusCode != 200) {
            log.error("statusCode =!=" + statusCode);
            log.error(sb);
        } else {
            log.info("Request returns statusCode 200: OK");
            log.info(sb);
        }
    }

    public void uploadFilePojo(Resource resource, String dataset_name, String resourceFileName) throws IOException {

        URL url = new URL(resource.getUrl());
        String tDir = System.getProperty("java.io.tmpdir");
        String path = tDir + "/"+resourceFileName;
        File file = new File(path);
        file.deleteOnExit();
        FileUtils.copyURLToFile(url, file);


        HttpPost postRequest;
        ContentBody cbFile = new FileBody(file, ContentType.TEXT_HTML);
        //ToDo: Handle error when any of the attributes of the resource is null as in the package
        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("file", cbFile)
                //Cannot use getKey() because sometimes it is empty and causes error (resource with no filename in it)
                .addPart("key", new StringBody(resourceFileName.split("\\.")[0],ContentType.TEXT_PLAIN))
                .addPart("name", new StringBody(resourceFileName,ContentType.TEXT_PLAIN))
                .addPart("url",new StringBody(resource.getUrl(),ContentType.TEXT_PLAIN))
                .addPart("package_id",new StringBody(dataset_name,ContentType.TEXT_PLAIN))
                .addPart("format",new StringBody(resource.getFormat(),ContentType.TEXT_PLAIN))
                .addPart("upload",cbFile)
                .addPart("description",new StringBody(resource.getDescription(),ContentType.TEXT_PLAIN))
                .build();

        postRequest = new HttpPost(HOST+"/api/action/resource_create");
        postRequest.setEntity(reqEntity);
        postRequest.setHeader("X-CKAN-API-Key", api_key);

        HttpResponse response = httpclient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        if(statusCode!=200){
            log.error("statusCode =!=" +statusCode);
        }
        else log.info("Request returns statusCode 200: OK");
    }

    public Boolean createOrUpdateResource(String path) throws IOException {
        File file = new File(path);
        String filename = file.getName();
        HttpPost postRequest;
        StringBuilder sb = new StringBuilder();
        String line;

        Gson gson = new Gson();

        //query the API to get the resources with that file name
        postRequest = new HttpPost(HOST+"/api/action/resource_search?query=name:"+filename);
        postRequest.setHeader("X-CKAN-API-Key", api_key);

        HttpResponse response = httpclient.execute(postRequest);
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        //Parse the response into a POJO to be able to get results from it.
        ResourceResponse resResponse = gson.fromJson(sb.toString(),ResourceResponse.class);
        System.out.println(resResponse);
        //Now we need to check if the count of results is 1 (otherwise error)
        //if the count is 0, call uploadFile to create the file
        if(resResponse.getResult().getCount()==0)
        {
            log.info("No resource found under that name, creating it...");
            uploadFile(path);
            return true;
            //if the count is 1, get all the needed data to update the resource
        }else if(resResponse.getResult().getCount()==1)
        {
            String id = resResponse.getResult().getResults().get(0).getId();

            //This is needed to check that the resource belongs to the current package
            Package_ foundPackage = getPackageById(id);
            String foundPackageId = "Not_found";
            if(foundPackage!=null)
            {
                foundPackageId = foundPackage.getId();
            }

            //If the resource's package_id is the same as the current package id (search for package by name and get the id)
            if( foundPackage != null && package_id.equals(foundPackageId)) {
                log.info("Resource found, updating it");
                updateFile(path, id);
                return true;
            }else{
                //If no package is found or the package is different than the current one
                log.error("The found resource does not belong to the same package we are expecting");
                log.error("Package id found:"+foundPackageId+". Package expected:"+package_id);
                return false;
            }
        }else{
            log.error("Found more than one resource with that name. Cancel update...");
            return false;
        }
    }

    private void updateFile(String path, String resourceId) throws IOException {
        File file = new File(path);
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String date=dateFormatGmt.format(new Date());

        HttpPost postRequest;
        ContentBody cbFile = new FileBody(file, ContentType.TEXT_HTML);
        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("id",new StringBody(resourceId,ContentType.TEXT_PLAIN))
                .addPart("file", cbFile)
                .addPart("key", new StringBody(file.getName().split("\\.")[0],ContentType.TEXT_PLAIN))
                .addPart("name", new StringBody(file.getName(),ContentType.TEXT_PLAIN))
                .addPart("url",new StringBody("testURL",ContentType.TEXT_PLAIN))
                .addPart("package_id",new StringBody(package_id,ContentType.TEXT_PLAIN))
                .addPart("upload",cbFile)
                .addPart("description",new StringBody(file.getName()+" created on: "+date,ContentType.TEXT_PLAIN))
                .build();

        postRequest = new HttpPost(HOST+"/api/action/resource_update");
        postRequest.setEntity(reqEntity);
        postRequest.setHeader("X-CKAN-API-Key", api_key);

        HttpResponse response = httpclient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        if(statusCode!=200){
            log.error("statusCode =!=" +statusCode);
        }
        else log.info("Request returns statusCode 200: OK");
    }

    /**
     * Function that uploads a file to CKAN through it's API
     * @param path Local filesystem path of the file to upload
     */
    private void uploadFile(String path) throws IOException {
        File file = new File(path);
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String date=dateFormatGmt.format(new Date());

        HttpPost postRequest;
        ContentBody cbFile = new FileBody(file, ContentType.TEXT_HTML);
        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("file", cbFile)
                .addPart("key", new StringBody(file.getName().split("\\.")[0],ContentType.TEXT_PLAIN))
                .addPart("name", new StringBody(file.getName(),ContentType.TEXT_PLAIN))
                .addPart("url",new StringBody("testURL",ContentType.TEXT_PLAIN))
                .addPart("package_id",new StringBody(package_id,ContentType.TEXT_PLAIN))
                .addPart("upload",cbFile)
                .addPart("description",new StringBody(file.getName()+" created on: "+date,ContentType.TEXT_PLAIN))
                .build();

        postRequest = new HttpPost(HOST+"/api/action/resource_create");
        postRequest.setEntity(reqEntity);
        postRequest.setHeader("X-CKAN-API-Key", api_key);

        HttpResponse response = httpclient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        if(statusCode!=200){
            log.error("statusCode =!=" +statusCode);
        }
        else log.info("Request returns statusCode 200: OK");
    }

    private Package_ getPackageById(String id) throws IOException {
        HttpPost postRequest;
        StringBuilder sb = new StringBuilder();
        String line;

        Gson gson = new Gson();

        //query the API to get the resources with that file name
        postRequest = new HttpPost(HOST+"/api/3/action/package_search?q=id:"+id);
        postRequest.setHeader("X-CKAN-API-Key", api_key);

        HttpResponse response = httpclient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        // Parse the response into a POJO to be able to get results from it.
        // ToDo: If no result is returned, raise an error (when converting to POJO fails or return code !=200?)
        if(statusCode==200) {
            CkanFullList CkanFullList = gson.fromJson(sb.toString(), CkanFullList.class);
            //by default we get the first package_ of the list of packages
            if (CkanFullList.getPackage().getPackages().size() == 1) {
                log.info("Package: "+id+" was found in CKAN.");
                return CkanFullList.getPackage().getPackages().get(0);
            } else {
                log.warn("Package: "+id+" not found");
                //ToDo: Null, really?
                return null;
            }
        }else{
            return null; //........
        }

    }

    public void close()
    {
        //httpclient.getConnectionManager().shutdown();
        try {
            httpclient.close();
        } catch (IOException e) {
            log.error(e);
        }
    }
}
