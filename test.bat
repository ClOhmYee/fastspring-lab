@echo off
chcp 65001 > nul
setlocal

echo Starting Spring Boot...
start /B cmd /c "cd /d "%~dp0servers\spring-boot\spring" && gradlew.bat bootRun > "%~dp0.spring-boot.log" 2>&1"

echo Starting FastAPI...
start /B cmd /c "cd /d "%~dp0servers\fastapi" && call venv\Scripts\activate.bat && python -m uvicorn app.main:app --host 127.0.0.1 --port 8000 > "%~dp0.fastapi.log" 2>&1"

echo Waiting for servers...
timeout /t 15 /nobreak > nul

echo Running tests...
cd /d "%~dp0servers\spring-boot\spring"
call gradlew.bat clean test

echo Done!
echo stopping servers...
pause

echo.
echo Shutting down...
for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8080" ^| findstr "LISTENING"') do taskkill /PID %%a /F /T > nul 2>&1
for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8000" ^| findstr "LISTENING"') do taskkill /PID %%a /F /T > nul 2>&1

echo Done!
pause
endlocal
