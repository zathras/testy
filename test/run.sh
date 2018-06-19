#!/bin/bash

#
# Compile the program:
#
rm -rf out
mkdir out
javac -Xlint:unchecked -sourcepath src:../src -d out src/Main.java
if [ $? != 0 ] ; then
    exit 1
fi

java -cp out -ea Main
