@echo off
echo ========================================
echo    BUILDING FRONTEND (BEFORE BACKEND)
echo ========================================

echo [1/5] Cleaning ALL previous builds...
if exist dist rmdir /s /q dist
if exist node_modules rmdir /s /q node_modules
if exist ..\backend\src\main\resources\static rmdir /s /q ..\backend\src\main\resources\static
echo Clean completed.

echo [2/5] Creating fresh directories...
mkdir ..\backend\src\main\resources\static
echo Directories created.

echo [3/5] Installing dependencies...
call npm install
if %errorlevel% neq 0 (
    echo ERROR: npm install failed!
    exit /b 1
)
echo Dependencies installed successfully.

echo [4/5] Building Angular application...
call npm run build
if %errorlevel% neq 0 (
    echo ERROR: npm run build failed!
    exit /b 1
)
echo Angular build completed successfully.

echo [5/5] COPYING FILES TO BACKEND STATIC...
if exist "dist\frontend\browser" (
    echo Copying from browser folder...
    xcopy "dist\frontend\browser\*" "..\backend\src\main\resources\static\" /Y /E /I
) else (
    echo ERROR: No browser folder found in dist!
    echo Current dist structure:
    dir dist /S
    exit /b 1
)

echo Files copied successfully.

echo ========================================
echo    VERIFICATION
echo ========================================
echo Files in backend static:
dir ..\backend\src\main\resources\static

echo ========================================
echo    FRONTEND BUILD FINISHED
echo ========================================