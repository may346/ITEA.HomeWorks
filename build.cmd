rem --- to compile        
del /Q bin
javac -source 8 -target 8 -d bin src\ua\itea\FilePrinter.java src\ua\itea\Main.java
rem --- to build
cd bin
jar -cfm ../out.jar ../Manifest.txt ua/itea/*.class ../macros.txt
rem --- to run
cd ..
java -jar out.jar