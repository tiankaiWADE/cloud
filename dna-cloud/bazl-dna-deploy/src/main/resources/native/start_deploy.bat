
@echo off
cd %~dp0
call bazl-dna-gateway/start.bat
timeout /T 15 /NOBREAK

cd %~dp0
call bazl-dna-system/start.bat
timeout /T 15 /NOBREAK

cd %~dp0
call bazl-dna-monitor/start.bat
timeout /T 15 /NOBREAK
