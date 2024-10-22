#!/bin/bash
# ./scripts/compile.sh

# java -ea -cp "lib/json.jar:bin" pointcards.Main pointcards.Main --type server --manifest-path ./PSManifestV1.json --players 2 --bots 0 
LOGGER_LEVEL=info java -ea -cp "lib/json.jar:bin" pointcards.Main pointcards.Main --type server --manifest-path ./PSManifestV1.json
