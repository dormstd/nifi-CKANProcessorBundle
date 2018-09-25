package net.atos.qrowd.processors.nifiCKANDatasetBackup;

import net.atos.qrowd.processors.pojos.Package_;

import java.io.IOException;

public class CKANUploader {
    public static void main(String[] args) {

        CKAN_API_Handler ckanhandler = new CKAN_API_Handler("http://130.206.127.63","c641640b-eebf-4cbe-a3e3-5050a964f90d","testSpecial","testorg","test_description", false);
        try {
            Package_ p = ckanhandler.getPackageByName("circoscrizioni");
            System.out.println(p);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        if(!ckanhandler.packageExists())
//        {
//            ckanhandler.createPackage();
//        }
//        ckanhandler.uploadFile("/home/rruizs/test.txt");
    }
}
