#!/bin/bash

cd ../src3

#echo ""
#echo "PACKAGE IHM"
#echo ""
#javac 	-d 	../build/classes 	InterfaceGraphique.java
echo ""
echo "PACKAGE BLAGUE"
echo ""
javac 	-d	../build/classes/bin3	blague/*.java
echo ""
echo "PACKAGE CODEBASE"
echo ""
javac 	-d 	../build/classes/bin3 	codebase/*.java
echo ""
echo "PACKAGE PROVIDER"
echo ""
javac 	-d	../build/classes/bin3	provider/*.java
echo ""
echo ""
echo "PACKAGE ANNUAIRE"
echo ""
javac 	-d	../build/classes/bin3	annuaire/*.java
echo ""
