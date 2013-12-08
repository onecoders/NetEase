@echo off

:main
cls
goto init
:initFin
goto prepare
:prepareFin
goto copyFile
:copyFin
goto modifyFile
:modifyFin
goto finish

:init
if not exist "%java_home%" (
    set /p javaHome=请输入JDK安装路径（如：C:\Java\jdk）：
    set modifier="%javaHome%\bin\java.exe" -jar modifier.jar
) else (
    set modifier="%java_home%\bin\java.exe" -jar modifier.jar
)
set /p projName=请输入您项目的名称（如：Sample）：
set /p addOKS=是否集成快捷分享（Y/N）：
set /p addWechatCallback=是否集成微信及其朋友圈（Y/N）：
if /i %addWechatCallback% == Y (
    set /p package=请输入您项目的包名（如：cn.sharesdk.demo）：
)
set packagePath=%package:.=\%
goto initFin

:prepare
if exist %projName% rd /s /q %projName%
mkdir %projName% > nul
set libDir=%projName%\libs
mkdir %libDir% > nul
set resDir=%projName%\res
mkdir %resDir% > nul
set assetsDir=%projName%\assets
mkdir %assetsDir% > nul
set srcDir=%projName%\src
mkdir %srcDir% > nul
goto prepareFin

:copyFile
xcopy /e Libs\MainLibs\libs %libDir% > nul
xcopy /e Libs\MainLibs\res %resDir% > nul
ren %resDir%\values\strings.xml ssdk_strings.xml > nul
ren %resDir%\values-en\strings.xml ssdk_strings.xml > nul
if /i %addOKS% == Y (
    xcopy /e Libs\OneKeyShare\libs %libDir% > nul
    xcopy /e Libs\OneKeyShare\res %resDir% > nul
    ren %resDir%\values\strings.xml oks_strings.xml > nul
    ren %resDir%\values-en\strings.xml oks_strings.xml > nul
    xcopy /e Libs\OneKeyShare\src %srcDir% > nul
)
xcopy /e Res %assetsDir% > nul
xcopy /e Src %srcDir% > nul
if /i %addWechatCallback% == Y (
    mkdir %srcDir%\%packagePath%\wxapi > nul
    xcopy /e %srcDir%\wxapi %srcDir%\%packagePath%\wxapi > nul
)
rd /s /q %srcDir%\wxapi > nul
goto copyFin

:modifyFile
if /i %addOKS% == Y (
    %modifier% -a "%~dp0%srcDir%\cn\sharesdk\onekeyshare" %package%
)
if /i %addWechatCallback% == Y (
    %modifier% -m "%~dp0%srcDir%\%packagePath%\wxapi" %package%
)
goto modifyFin

:finish
echo.
echo 集成Share SDK所需的文件已经复制到目录%projName%中，请复制其中的文件到您的项目中覆盖
echo.
pause
del modifier.jar
del 双击此批处理文件可以生成一个快速集成ShareSDK的目录.bat
