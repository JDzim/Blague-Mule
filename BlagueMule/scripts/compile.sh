#!/bin/bash

#javac 	-d 	../build/classes/blague 	../src/blague/*.java
#javac 	-d 	../build/classes/client 	../src/client/*.java
#javac 	-d 	../build/classes/codebase 	../src/codebase/*.java
#javac 	-d 	../build/classes/exceptions 	../src/exceptions/*.java
#javac 	-d 	../build/classes/serveur 	../src/serveur/*.java

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

