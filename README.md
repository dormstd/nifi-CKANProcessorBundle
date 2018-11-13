# Apache Nifi CKAN

This is a set of custom [Apache Nifi](https://nifi.apache.org/) processor that help using the CKAN API.

## Build and deploy

To deploy this processor in a Apache Nifi instance it first need to be packaged as a .nar file.
To do so just navigate into the project and run:
```
mvn clean package
```

Then we can deploy the generated .nar package that can be found in the `nar` folders
into the libraries folder of the Apache Nifi instance.

Developed within the [QROWD](http://qrowd-project.eu/) H2020 EU project