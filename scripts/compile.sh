#!/bin/bash
echo "⌛ Compiling..."
javac -d bin -cp "lib/json.jar:src" src/pointcards/**/*.java src/pointcards/Main.java
echo "✅ Successfully compiled"
