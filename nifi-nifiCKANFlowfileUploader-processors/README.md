# Apache Nifi CKAN flowfile uploader

This is a custom [Apache Nifi](https://nifi.apache.org/) processor that, using the CKAN API, is able to:
* Check the existence and create organizations
* Check the existence and create packages
* Upload the flowfile to the package

## Behaviour

It receives an input flowfile with a path to a file and handles the creation (if needed) of the organization and package in ckan to be able to upload that file to it.

## Configuration

The processor has 6 properties to be filled before running:

* **CKAN_url**: Url of the CKAN instance to write to
* **api_key**: Personal API-Key provided by CKAN
* **organization_id**: Name of the organization to upload the file to, or create if it does not exists.
* **package_name**: *(optional)* Name for the creating of the package. When empty, the filename attribute of the flowfile will be used.
* **package_description**: *(optional)* Description of the package
* **package_visibility**: *(optional)* Choose the visibility of the package between private or public

Developed within the [QROWD](http://qrowd-project.eu/) H2020 EU project