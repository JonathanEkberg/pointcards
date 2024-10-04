#!/bin/bash
./compile.sh
java -cp out pointcards.Main -type client -port 3000
