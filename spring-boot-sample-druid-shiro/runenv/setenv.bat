:: 关闭终端回显  
@echo off
setlocal enabledelayedexpansion
 
if exist "%BASEDIR%\jre" goto setenv
echo JAVA_HOME 	%JAVA_HOME%
echo CLASSPATH 	%CLASSPATH%
goto end

:: Set Java environment
:setenv
SET "JAVA_HOME=%BASEDIR%\jre"
SET "CLASSPATH=%JAVA_HOME%\lib\tools.jar;%JAVA_HOME%\lib\dt.jar;"
SET "Path=%JAVA_HOME%\bin;%path%"
echo JAVA_HOME 	%JAVA_HOME%
echo CLASSPATH 	%CLASSPATH%
goto end

:end
exit /b 0