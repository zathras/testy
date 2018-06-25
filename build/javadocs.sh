#!/bin/sh
cd `dirname $0`/..
echo "Running in `pwd`"
rm -rf docs/javadocs
javadoc -d docs/javadocs -sourcepath src edu.calpoly.testy
cd docs
cp -r javadocs testy-javadocs
mkdir -p ../out
rm -f ../out/testy-javadocs.zip
zip -q -r ../out/testy-javadocs.zip testy-javadocs
rm -rf testy-javadocs
echo "Created out/testy-javadocs.zip"
