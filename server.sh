#!/bin/bash
find -name "*.java" > sources.txt
javac -d bin/ @sources.txt

PACKAGE_ROOT=$(echo "$dirs")

rm -f Manifest.mf
echo "Manifest-Version: 1.0" >> Manifest.mf
echo "Main-Class: com.hvadoda1.server.starter.ServerStarter" > Manifest.mf

cd bin/
jar cmf ../Manifest.mf ../server.jar com/

cd ..
rm -f Manifest.mf
rm -f sources.txt
rm -rf bin