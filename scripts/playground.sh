#!/bin/bash
# javac -cp json.jar -sourcepath src -d bin src/pointcards/Playground.java
javac -d bin -cp "json.jar:src" src/pointcards/Playground.java
java -cp "./json.jar:bin" pointcards.Playground
