@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion

echo ========================================
echo   Spring Boot Project Package Script
echo ========================================
echo.

REM Set project root directory
set PROJECT_ROOT=%~dp0..

echo [1/4] Cleaning previous build...
cd /d %PROJECT_ROOT%
call mvn clean
if errorlevel 1 (
    echo Clean failed!
    pause
    exit /b 1
)
echo Clean completed!
echo.

echo [2/4] Downloading dependencies...
call mvn dependency:resolve
if errorlevel 1 (
    echo Dependency download failed!
    pause
    exit /b 1
)
echo Dependencies downloaded!
echo.

echo [3/4] Packaging with prod profile...
call mvn package -DskipTests -Pprod
if errorlevel 1 (
    echo Package failed!
    pause
    exit /b 1
)
echo Package completed!
echo.

echo [4/4] Searching for generated jar file...
for /f "delims=" %%i in ('dir /b /od "%PROJECT_ROOT%\target\*.jar"') do (
    set JAR_FILE=%%i
)

if defined JAR_FILE (
    echo ========================================
    echo Package Success!
    echo Jar file location: %PROJECT_ROOT%\target\%JAR_FILE%
    echo ========================================
) else (
    echo Jar file not found!
)

echo.
pause
