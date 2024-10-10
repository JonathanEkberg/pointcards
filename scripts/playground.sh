#!/bin/bash
# rm -r ./bin
javac -d bin -cp "lib/*:src" src/pointcards/**/*.java src/pointcards/Playground.java
# javac -d bin -cp "lib/*:src" src/pointcards/**/*.java
java -cp "lib/*:bin" pointcards.Playground
