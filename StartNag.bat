@echo off
chcp 65001 >nul
echo.
echo  **##*            *##**                                                     
echo   *#####*         **###*                                                     
echo   *######*      *##*###*                                                     
echo   *#######*    *###*###*          **+         **++               +*+         
echo   *###**###*  *###**###*     +*#########* **########*+       *########**##*  
echo   *###* *###**###* *###*    *####**+**#######*****####*    **####*****#####  
echo   *###*  *####*#*  *###*   *###*      *#####*      *###*   *###*      *####  
echo   *###*   **###*   *###*               *###+        *##*  *###*        *###  
echo   *###*  *#**###*  *###*       ****####################*  *###*        *###  
echo   *###* *###**###* *###*    *##########################*  *###+        *###  
echo   *###**###*  *###**###*   *###*+      *###*              *###*        *###  
echo   *###*###*    *#######*   *###*       *###*              *###*        *###  
echo   *###*#**      *######*   *###*     **#####**      ***    *####*    **####  
echo   *###***        +*####*    *###########*+*############*    +##########*###  
echo   +****            *****      **####***     **#####**+        ***###** *##*  
echo                                                              *        +###*  
echo                                                            *##**     +####*  
echo                                                             *###########*+   
echo                                                               **********     
echo.
echo Starting Næg frontend...
echo.

:: Navigate to the directory where the .bat file is located
cd /d "%~dp0frontend"

:: Check if the directory change was successful
if errorlevel 1 (
    echo Error: Could not navigate to the frontend directory.
    echo Make sure the path is correct and the repository is cloned properly.
    pause
    exit /b 1
)

:: Debugging: Confirm current directory
echo Current directory: %cd%

:: Start the React app
echo Running npm start...
npm start
echo Ran npm start...

:: Check if npm start failed
if errorlevel 1 (
    echo Error: Failed to start the React app.
    echo Ensure Node.js and npm are installed and the dependencies are properly installed.
    pause
    exit /b 1
)


:: Keep the terminal open after successful execution
echo.
echo Næg frontend started successfully.
type "%~dp0frontend\pikachu.txt"
echo.




pause
