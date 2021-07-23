
@echo off
cd %~dp0

start java -jar ./bazl-dna-zuul/bazl-dna-zuul-1.0.0-SNAPSHOT.jar
timeout /T 15 /NOBREAK

start java -jar ./bazl-dna-system/bazl-dna-system-1.0.0-SNAPSHOT.jar
timeout /T 15 /NOBREAK

start java -jar ./bazl-dna-turbine/bazl-dna-turbine-1.0.0-SNAPSHOT.jar
timeout /T 15 /NOBREAK

start java -jar ./bazl-dna-files/bazl-dna-files-1.0.0-SNAPSHOT.jar
timeout /T 15 /NOBREAK

