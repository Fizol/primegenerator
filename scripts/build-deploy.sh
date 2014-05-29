#!/bin/bash
 
cd ~/NetBeansProjects/PrimeGenerator/
mvn clean install
cp ~/NetBeansProjects/PrimeGenerator/target/PrimeGenerator.war /opt/jetty-distribution-9.2.0.v20140526/webapps
