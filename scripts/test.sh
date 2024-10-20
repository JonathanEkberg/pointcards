#!/bin/bash
javac -d bin -cp "lib/*:src" src/test/requirements/*.java src/test/*.java

echo "Running tests..."
for file in src/test/*.java
do
    NAME=$(basename $file .java)
    java -ea -cp "lib/*:bin" org.junit.runner.JUnitCore test.$NAME
done

echo "

Running requirements tests..."
for file in src/test/requirements/*.java
do
    
    # Convert to only filename withbin the .java extension
    NAME=$(basename $file .java)
    echo "
Testing $NAME"
    java -ea -cp "lib/*:bin" org.junit.runner.JUnitCore test.requirements.$NAME

    # To run with all logs run the following command instead
    # LOGGER_LEVEL=debug java -ea -cp "lib/*:bin" org.junit.runner.JUnitCore test.requirements.$NAME
done