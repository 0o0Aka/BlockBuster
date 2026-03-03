@echo off
setlocal enabledelayedexpansion

:: 定义源钩子目录和目标钩子目录
set "SOURCE_HOOKS=%~dp0hooks"
set "TARGET_HOOKS=%~dp0.git\hooks"

:: 复制 pre-commit 钩子到 .git/hooks
copy /Y "%SOURCE_HOOKS%\pre-commit" "%TARGET_HOOKS%\pre-commit" > nul
:: 给钩子添加可执行权限（Windows 下可省略，但 Linux/Mac 兼容需要）
git update-index --chmod=+x "%TARGET_HOOKS%\pre-commit"

:: 复制 jar 包到 .git/hooks（方便钩子调用）
copy /Y "%SOURCE_HOOKS%\sql-check.jar" "%TARGET_HOOKS%\sql-check.jar" > nul

echo 钩子安装成功！
pause