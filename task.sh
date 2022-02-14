#!/bin/bash

args="$(printf " %q" "${@}")"
mvn compile exec:java -Dexec.mainClass="com.ewan.TaskManager.Main" -Dexec.args="$args"