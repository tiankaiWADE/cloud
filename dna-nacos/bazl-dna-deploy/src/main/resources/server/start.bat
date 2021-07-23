
@echo off
cd %~dp0
call bazl-dna-eureka/start.bat
timeout /T 15 /NOBREAK

cd %~dp0
call bazl-dna-config/start.bat
timeout /T 15 /NOBREAK