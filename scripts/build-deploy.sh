#!/bin/bash
 
cd ~/NetBeansProjects/PrimesGenerator/
mvn clean install -Dmaven.test.skip=true
cp ~/NetBeansProjects/PrimesGenerator/target/PrimesGenerator.war /opt/jetty-distribution-9.2.0.v20140526/webapps
