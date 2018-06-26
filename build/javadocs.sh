#!/bin/sh
if [ "$JAVA8_HOME" = "" ] ; then
    echo "Please set the JAVA8_HOME environment variable."
    exit 1
fi
cd `dirname $0`/..
echo "Running in `pwd`"
rm -rf docs/javadocs
$JAVA8_HOME/bin/javadoc -d docs/javadocs -sourcepath src edu.calpoly.testy
cd docs
cp -r javadocs testy-javadocs
mkdir -p ../out
rm -f ../out/testy-javadocs.zip
zip -q -r ../out/testy-javadocs.zip testy-javadocs
rm -rf testy-javadocs
echo "Created out/testy-javadocs.zip"
