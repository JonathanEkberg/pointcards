#!/bin/bash
./scripts/compile.sh
java -cp "lib/json.jar:bin" pointcards.Main -type server
