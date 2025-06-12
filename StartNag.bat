@echo off
chcp 65001 >nul
echo.
echo  ___                              ___
echo ( _ )                            ( _ )
echo  ^|   ^|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~^|   ^|
echo  ^|   ^|                              ^|   ^|
echo  ^|   ^|    _   _                     ^|   ^|
echo  ^|   ^|   ^| \ ^| ^|  _ ___   __ _    ^|   ^|
echo  ^|   ^|   ^|  \^| ^| / _  _ \ / _ ^|   ^|   ^|
echo  ^|   ^|   ^| ^|\  ^|^| (_^|  __/^| (_^| ^|   ^|   ^|
echo  ^|   ^|   ^|_^| \_^| \__,____^| \__, ^|   ^|   ^|
echo  ^|   ^|                     ^|___/    ^|   ^|
echo  ^|   ^|                              ^|   ^|
echo  ^|___^|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~^|___^|
echo (___)                            (___)
echo.
echo Starting Næg...
echo.

:: Navigate to backend directory (Maven project root).
echo Starting Maven backend...
cd /d "%~dp0backend"
if errorlevel 1 (
    echo Error: Could not navigate to backend directory.
    echo Expected path: %~dp0backend
    pause
    exit /b 1
)

echo Current backend directory: %cd%

:: Check if pom.xml exists
if not exist "pom.xml" (
    echo Error: pom.xml not found. Make sure you're in the Maven project root.
    echo Current directory: %cd%
    pause
    exit /b 1
)

:: Start Maven Spring Boot application in background
echo Running Maven Spring Boot application...
start "Backend Server" mvn spring-boot:run

:: Wait for backend to start up
echo Backend starting... waiting 10 seconds for Spring Boot initialization
timeout /t 10 /nobreak >nul

:: Navigate to frontend directory
echo Starting frontend...
cd /d "%~dp0frontend"
if errorlevel 1 (
    echo Error: Could not navigate to frontend directory.
    echo Expected path: %~dp0frontend
    pause
    exit /b 1
)

echo Current frontend directory: %cd%

:: Check if package.json exists
if not exist "package.json" (
    echo Error: package.json not found. Make sure you're in the React project root.
    echo Current directory: %cd%
    pause
    exit /b 1
)

echo Running npm start...
npm start

:: Keep terminal open if npm start exits
echo.
echo Frontend process ended.
pause