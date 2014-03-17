#!/bin/bash
cd ..
REP=$(pwd)
BIN=bin1
SRC=src

mkdir -p $BIN

echo "Compilation de blagues.jar ..."
mkdir -p $BIN/lib
mkdir -p $BIN/blagues
javac $SRC/blague/* -d $BIN/blagues/
cd $BIN/blagues/
jar cvf ../lib/blagues.jar . #-C $BIN/lib blagues.jar
cd $REP
rm -R $BIN/blagues

echo "Compilation du Serveur ..."
mkdir -p $BIN/AppliServeur
mkdir -p $BIN/AppliServeur/serveur
javac $SRC/serveur/* -cp "$BIN/lib/blagues.jar" -d $BIN/AppliServeur/serveur/

echo "Compilation du Client ..."
mkdir -p $BIN/AppliClient
mkdir -p $BIN/AppliClient/client
javac $SRC/client/* -cp "$BIN/AppliServeur/serveur:$BIN/lib/blagues.jar" -d $BIN/AppliClient/client/
