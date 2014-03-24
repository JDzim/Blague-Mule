#!/bin/bash
cd ..
REP=$(pwd)
BIN=bin3
SRC=src3

mkdir -p $BIN

echo "Compilation de blagues.jar ..."
mkdir -p $BIN/lib
mkdir -p $BIN/blagues
javac $SRC/blague/* -d $BIN/blagues/
cd $BIN/blagues/
jar cvf ../lib/blagues.jar . #-C $BIN/lib blagues.jar
cd $REP
rm -R $BIN/blagues

echo "Compilation de codebase.jar ..."
mkdir -p $BIN/lib
mkdir -p $BIN/codebase
javac $SRC/codebase/* -cp "$BIN/lib/blagues.jar" -d $BIN/codebase/
cd $BIN/codebase/
jar cvf ../lib/codebase.jar . #-C $BIN/lib codebase.jar
cd $REP
rm -R $BIN/codebase

echo "Compilation du Provider ..."
#mkdir -p $BIN/Provider
javac $(find $SRC/provider/ -type f) -cp "$BIN/lib/codebase.jar:$BIN/lib/blagues.jar" -d $BIN/
