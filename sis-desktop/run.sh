#!/usr/bin/env bash

export JAVA_HOME=/d/opt/programs/java/oracle/jdk-17.0.7
export PATH=$JAVA_HOME/bin:$PATH
java -version

./mvnw javafx:run
