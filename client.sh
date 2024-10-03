#!/bin/bash
./compile.sh
java -cp out cardgame.Main -type client -port 3000
