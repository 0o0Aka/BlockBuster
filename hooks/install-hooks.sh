#!/bin/bash
set -e  # 遇到错误立即退出

# ===================== 第一步：定义路径 =====================
# 获取脚本所在目录（项目根目录）
PROJECT_ROOT=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)

# 共享钩子目录（项目根目录下的 hooks 文件夹）
SOURCE_HOOKS="${PROJECT_ROOT}/hooks"
# Git 钩子目标目录
TARGET_HOOKS="${PROJECT_ROOT}/.git/hooks"

# ===================== 第二步：前置检查 =====================
# 检查是否是 Git 仓库
if [ ! -d "${PROJECT_ROOT}/.git" ]; then
    echo -e "\033[31m【错误】当前目录不是 Git 仓库！请将此脚本放在项目根目录运行。\033[0m"
    exit 1
fi

# 检查共享钩子目录是否存在
if [ ! -d "${SOURCE_HOOKS}" ]; then
    echo -e "\033[31m【错误】未找到共享钩子目录：${SOURCE_HOOKS}\033[0m"
    echo -e "请确认项目根目录下有 \"hooks\" 文件夹，并包含 pre-commit 和 sql-check.jar 文件。"
    exit 1
fi

# 检查关键文件是否存在
if [ ! -f "${SOURCE_HOOKS}/pre-commit" ]; then
    echo -e "\033[31m【错误】缺少 pre-commit 钩子脚本：${SOURCE_HOOKS}/pre-commit\033[0m"
    exit 1
fi

if [ ! -f "${SOURCE_HOOKS}/sql-check.jar" ]; then
    echo -e "\033[31m【错误】缺少 SQL 检测 jar 包：${SOURCE_HOOKS}/sql-check.jar\033[0m"
    exit 1
fi

# 自动创建 Git 钩子目录（若不存在）
if [ ! -d "${TARGET_HOOKS}" ]; then
    echo -e "\033[33m【提示】Git 钩子目录不存在，正在创建：${TARGET_HOOKS}\033[0m"
    mkdir -p "${TARGET_HOOKS}"
fi

# ===================== 第三步：复制文件并配置权限 =====================
echo -e "\033[32m【进度】正在复制 pre-commit 钩子脚本...\033[0m"
cp -f "${SOURCE_HOOKS}/pre-commit" "${TARGET_HOOKS}/pre-commit"

echo -e "\033[32m【进度】正在复制 SQL 检测 jar 包...\033[0m"
cp -f "${SOURCE_HOOKS}/sql-check.jar" "${TARGET_HOOKS}/sql-check.jar"

# Linux 下必须给钩子添加可执行权限（核心！）
echo -e "\033[32m【进度】配置钩子执行权限...\033[0m"
chmod +x "${TARGET_HOOKS}/pre-commit"

# ===================== 第四步：完成提示 =====================
echo -e "\n=============================================="
echo -e "\033[32m✅ 钩子安装成功！\033[0m"
echo -e "后续执行 git commit 时会自动触发 SQL 检测。"
echo -e "==============================================\n"

exit 0