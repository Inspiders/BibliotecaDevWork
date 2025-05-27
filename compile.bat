@echo off
cd src
javac -d ../out *.java ClassAux/*.java ClassMother/*.java User/*.java Book/*.java Bank/*.java
cd ..
echo Compilação concluída!
pause 