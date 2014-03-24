#!/bin/bash

cd ../src2

#echo ""
#echo "PACKAGE IHM"
#echo ""
#javac 	-d 	../build/classes 	InterfaceGraphique.java
echo ""
echo "PACKAGE BLAGUE"
echo ""
javac 	-d	../build/classes/bin2	blague/*.java
echo ""
echo "PACKAGE CODEBASE"
echo ""
javac 	-d 	../build/classes/bin2 	codebase/*.java
echo ""
echo "PACKAGE PROVIDER"
echo ""
javac 	-d	../build/classes/bin2	provider/*.java
echo ""

