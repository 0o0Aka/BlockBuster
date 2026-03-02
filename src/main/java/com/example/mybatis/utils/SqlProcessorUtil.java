package com.example.mybatis.utils;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
//import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 单个项目专用 SQL 处理工具类
 * 功能：解析、校验、改写 SQL
 */
public class SqlProcessorUtil {
    // 数据库方言（根据项目实际修改：mysql/oracle/sqlserver）
    private static final DbType DB_TYPE = DbType.mysql;
    private static final Logger log = LoggerFactory.getLogger(SqlProcessorUtil.class);

    // ---------------------- 1. SQL 解析：提取核心信息 ----------------------
    /**
     * 解析 SQL，返回表名、SQL 类型等信息
     * @param sql 待解析的 SQL
     * @return 解析结果
     */
    public static ParseResult parseSql(String sql) {
        ParseResult result = new ParseResult();
        try {
            // 解析 SQL 为抽象语法树（AST）
            List<SQLStatement> statements = SQLUtils.parseStatements(sql, DB_TYPE);
            result.setSuccess(true);
            result.setStatements(statements);

            // 提取表名和 SQL 类型
            for (SQLStatement stmt : statements) {
                // 记录 SQL 类型（SELECT/INSERT/UPDATE/DELETE）
                result.getSqlTypes().add(stmt.getClass().getSimpleName());

                // 提取 SELECT 语句的表名
                if (stmt instanceof SQLSelectStatement) {
                    SQLSelect select = ((SQLSelectStatement) stmt).getSelect();
                    SQLSelectQueryBlock queryBlock = (SQLSelectQueryBlock) select.getQuery();
                    if (queryBlock.getFrom() instanceof SQLExprTableSource) {
                        SQLExprTableSource tableSource = (SQLExprTableSource) queryBlock.getFrom();
                        String tableName = tableSource.getExpr().toString();
                        result.getTableNames().add(tableName);
                    }
                }
            }
            log.info("SQL 解析成功 | 原SQL：{} | 表名：{} | SQL类型：{}", sql, result.getTableNames(), result.getSqlTypes());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrorMsg("解析失败：" + e.getMessage());
            log.error("SQL 解析失败 | 原SQL：{} | 错误：{}", sql, e.getMessage());
        }
        return result;
    }

    // ---------------------- 2. SQL 校验：检查是否符合规范 ----------------------
    /**
     * 校验 SQL 是否符合规范（可自定义规则）
     * @param sql 待校验的 SQL
     * @return 校验结果
     */
    public static ValidateResult validateSql(String sql) {
        ValidateResult result = new ValidateResult();
        try {
            ParseResult parseResult = parseSql(sql);
            if (!parseResult.isSuccess()) {
                result.setValid(false);
                result.setErrorMsg(parseResult.getErrorMsg());
                return result;
            }

            // 规则1：禁止 SELECT * 全表扫描
            boolean hasSelectAll = checkSelectAll(parseResult.getStatements());
            if (hasSelectAll) {
                result.setValid(false);
                result.addError("禁止使用 SELECT *，请指定具体字段");
            }

            // 规则2：禁止无 WHERE 条件的 DELETE（防止误删全表）
            boolean hasDangerousDelete = checkDangerousDelete(sql);
            if (hasDangerousDelete) {
                result.setValid(false);
                result.addError("DELETE 语句必须包含 WHERE 条件");
            }

            // 所有规则通过
            result.setValid(result.getErrors().isEmpty());
            if (result.isValid()) {
                log.info("SQL 校验通过 | 原SQL：{}", sql);
            } else {
                log.warn("SQL 校验失败 | 原SQL：{} | 错误：{}", sql, result.getErrors());
            }
        } catch (Exception e) {
            result.setValid(false);
            result.setErrorMsg("校验失败：" + e.getMessage());
            log.error("SQL 校验异常 | 原SQL：{} | 错误：{}", sql, e.getMessage());
        }
        return result;
    }

    // ---------------------- 私有辅助方法（规则实现） ----------------------
    // 检查是否包含 SELECT *
    private static boolean checkSelectAll(List<SQLStatement> statements) {
        for (SQLStatement stmt : statements) {
            if (stmt instanceof SQLSelectStatement) {
                SQLSelect select = ((SQLSelectStatement) stmt).getSelect();
                SQLSelectQueryBlock queryBlock = (SQLSelectQueryBlock) select.getQuery();
                return queryBlock.getSelectList().stream()
                        .anyMatch(item -> "*".equals(item.getExpr().toString()));
            }
        }
        return false;
    }

    // 检查是否是无 WHERE 的 DELETE
    private static boolean checkDangerousDelete(String sql) {
        return sql.trim().toUpperCase().startsWith("DELETE")
                && !sql.toUpperCase().contains("WHERE");
    }

    // ---------------------- 内部结果类（无需修改） ----------------------
    // 解析结果
    public static class ParseResult {
        private boolean success;
        private String errorMsg;
        private List<SQLStatement> statements = new ArrayList<>();
        private List<String> sqlTypes = new ArrayList<>();
        private List<String> tableNames = new ArrayList<>();

        // Getter & Setter（直接生成即可）
        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }
        public String getErrorMsg() { return errorMsg; }
        public void setErrorMsg(String errorMsg) { this.errorMsg = errorMsg; }
        public List<SQLStatement> getStatements() { return statements; }
        public void setStatements(List<SQLStatement> statements) { this.statements = statements; }
        public List<String> getSqlTypes() { return sqlTypes; }
        public List<String> getTableNames() { return tableNames; }
    }

    // 校验结果
    public static class ValidateResult {
        private boolean valid;
        private String errorMsg;
        private List<String> errors = new ArrayList<>();

        // Getter & Setter
        public boolean isValid() { return valid; }
        public void setValid(boolean valid) { this.valid = valid; }
        public String getErrorMsg() { return String.join("；", errors); }
        public void setErrorMsg(String errorMsg) { this.errors.add(errorMsg); }
        public List<String> getErrors() { return errors; }
        public void addError(String error) { this.errors.add(error); }
    }
}