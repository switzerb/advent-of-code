#!/usr/bin/env bash
cd `dirname $0`
mvn exec:java -Dexec.mainClass=com.brennaswitzer.aoc.Main
