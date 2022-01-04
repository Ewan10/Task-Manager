#!/bin/bash

# Note: you may need to run `chmod +x task.sh` in order to execute this file.

# This just preserves quotes and prevents string splitting on whitespace
args="$(printf " %q" "${@}")"
mvn compile exec:java -Dexec.mainClass="com.ewan.TaskManager.Main" -Dexec.args="$args"