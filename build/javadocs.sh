#!/bin/sh
cd `dirname $0`/..
echo "Running in `pwd`"
rm -rf docs/javadocs
javadoc -d docs/javadocs -sourcepath src edu.calpoly.testy
cd docs/javadocs
mkdir -p ../../out
zip -q -r ../../out/testy-javadocs.zip *
echo "Created out/testy-javadocs.zip"
