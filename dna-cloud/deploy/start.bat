
@echo off
cd %~dp0

start java -jar ./bazl-dna-eureka/bazl-dna-eureka-1.0.0-SNAPSHOT.jar
timeout /T 15 /NOBREAK

start java -jar ./bazl-dna-zipkin/bazl-dna-zipkin-1.0.0-SNAPSHOT.jar
timeout /T 15 /NOBREAK

start java -jar ./bazl-dna-config/bazl-dna-config-1.0.0-SNAPSHOT.jar
timeout /T 15 /NOBREAK
