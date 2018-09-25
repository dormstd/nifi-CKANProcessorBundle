# Apache Nifi CKAN uploader

This is a custom [Apache Nifi](https://nifi.apache.org/) processor that, using the CKAN API, is able to:

* Check if there is a package with the same name as the filename atribute of the input flowfile
* If no package is found, it will output the flowfile via *NOT_FOUND* relationship
* If the package is found:
 * It will create a new package with the name+timestamp
 * It will iterate over the resources of the original package and create a timestamped copy of the resources in the newly created package
 * It will output the flowfile via *SUCCESS* relationship so the next processor can use it

## Build and deploy

To deploy this processor in a Apache Nifi instance it first need to be packaged as a .nar file.
To do so just navigate into the project and run:
```
mvn clean package
```

Then we can deploy the generated .nar package that can be found in `~/nifi-nifiCKANDatasetBackup-nar/target/`
into the libraries folder of the Apache Nifi instance.

## Usage
The processor requires a **filename** attribute present in the input flowfile.


The processor has 2 properties to be filled before running:

* **CKAN_url**: Url of the CKAN instance to write to
* **api_key**: Personal API-Key provided by CKAN
