#!/bin/bash
set -e  # 遇到错误立即退出

# 1. 获取脚本所在目录（即 hooks/ 目录）
HOOKS_DIR="$(dirname "$0")"

# 2. 获取项目根目录（BlockBuster 目录）
# 由于 hooks/ 在 BlockBuster 下，所以向上一级就是项目根目录
PROJECT_ROOT="$(dirname "$HOOKS_DIR")"

# 共享钩子目录（项目根目录下的 hooks 文件夹）
SOURCE_HOOKS="${HOOKS_DIR}"
# Git 钩子目标目录
TARGET_HOOKS="${PROJECT_ROOT}/.git/hooks"

# ===================== 第三步：复制文件并配置权限 =====================
echo -e "\033[32m【进度】正在复制 pre-commit 钩子脚本...\033[0m"
cp -f "${SOURCE_HOOKS}/pre-commit" "${TARGET_HOOKS}/pre-commit"

# Linux 下必须给钩子添加可执行权限（核心！）
echo -e "\033[32m【进度】配置钩子执行权限...\033[0m"
chmod +x "${TARGET_HOOKS}/pre-commit"

# ===================== 第四步：完成提示 =====================
echo -e "\n=============================================="
echo -e "\033[32m✅ 钩子安装成功！\033[0m"
echo -e "后续执行 git commit 时会自动触发 SQL 检测。"
echo -e "==============================================\n"

exit 0