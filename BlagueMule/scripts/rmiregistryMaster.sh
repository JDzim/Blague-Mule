#!/bin/bash

cd ../src2

echo ""
echo "PACKAGE BLAGUE"
echo ""
javac 	-d	../build/classes	blague/*.java
echo ""
echo "PACKAGE CODEBASE"
echo ""
javac 	-d 	../build/classes 	codebase/*.java
echo ""
echo "PACKAGE PROVIDER"
echo ""
javac 	-d 	../build/classes 	provider/*.java
echo ""


cd ../build/classes
rmiregistry

