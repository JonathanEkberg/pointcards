#!/bin/bash
./scripts/compile.sh
java -ea -cp "lib/json.jar:bin" pointcards.Main --type client
