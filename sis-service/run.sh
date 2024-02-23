#!/usr/bin/env bash

export JAVA_HOME=/d/opt/programs/java/oracle/jdk-21
export PATH=$JAVA_HOME/bin:$PATH
java -version

./mvnw clean package
java -jar ./target/sis-service-0.0.1-SNAPSHOT.jar
