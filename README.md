PrimesGenerator
========================
A simple web application generating prime numbers.

## How to build ##
You need Java 8 and Maven 3.0.5 version to well build. Java 8 is mandatory
due to lambda expressions. Probably at least Maven 3 is good, but for me
it was 3.0.5. 


```
cd project_directory ##or there where pom.xml
mvn clean install -Dmaven.test.skip=true
```

Skip test to don't wait until performance tests ends. This should also
install project .war target in your local repository.

## How to run ##
Simply deploy .war on your Java servlet server. I use Jetty. After build:

```
cp project_directory/target/PrimesGenerator.war $JETTY_HOME/webapps
```

JETTY_HOME should be folder with your jetty installation. I use
9.2.0.v20140526 Jetty version.
Now you can go on localhost:8080/PrimesGenerator/ address.

Last thing, you can just ignore scripts folder. There are my paths and it's
useless for you.
