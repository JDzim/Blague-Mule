#!/bin/bash

#javac 	-d 	../build/classes/blague 	../src/blague/*.java
#javac 	-d 	../build/classes/client 	../src/client/*.java
#javac 	-d 	../build/classes/codebase 	../src/codebase/*.java
#javac 	-d 	../build/classes/exceptions 	../src/exceptions/*.java
#javac 	-d 	../build/classes/serveur 	../src/serveur/*.java

cd ../src

echo ""
echo "PACKAGE BLAGUE"
echo ""
javac 	-d	../build/classes	blague/*.java
echo ""
echo "PACKAGE CLIENT"
echo ""
javac 	-d 	../build/classes 	client/*.java
echo ""
echo "PACKAGE CODEBASE"
echo ""
javac 	-d 	../build/classes 	codebase/*.java
echo ""
echo "PACKAGE EXCEPTIONS"
echo ""
javac 	-d 	../build/classes 	exceptions/*.java
echo ""
echo "PACKAGE SERVEUR"
echo ""
javac 	-d 	../build/classes 	serveur/*.java
echo ""

