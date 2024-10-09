#!/bin/bash
javac -d bin -cp "lib/*:src" src/test/*.java

for file in src/test/*.java
do
    # Convert to only filename withbin the .java extension
    NAME=$(basename $file .java)
    java -cp "lib/*:bin" org.junit.runner.JUnitCore test.$NAME
done