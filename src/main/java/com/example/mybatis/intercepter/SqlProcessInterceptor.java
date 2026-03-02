package com.example.mybatis.intercepter;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;
import com.example.mybatis.utils.SqlProcessorUtil;

import java.sql.Connection;
import java.util.Properties;

/**
 * MyBatis 拦截器：自动处理项目中所有执行的 SQL
 */
@Component // 交给 Spring 管理
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class SqlProcessInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 1. 获取 MyBatis 执行的原 SQL
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        String originalSql = (String) metaObject.getValue("delegate.boundSql.sql");

        // 2. 校验 SQL（如果不合规，可选择抛出异常或仅告警）
        SqlProcessorUtil.ValidateResult validateResult = SqlProcessorUtil.validateSql(originalSql);
        if (!validateResult.isValid()) {
            // 仅告警，不阻断项目运行（也可改为 throw new RuntimeException(...)）
            System.err.println("【SQL 校验失败】" + validateResult.getErrorMsg() + " | 原SQL：" + originalSql);
        }

//        // 3. 改写 SQL（自动加 LIMIT）
//        String rewrittenSql = SqlProcessorUtil.rewriteSql(originalSql);
//        // 替换 MyBatis 要执行的 SQL
//        metaObject.setValue("delegate.boundSql.sql", rewrittenSql);

        // 4. 继续执行原逻辑
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 可配置参数，暂无需实现
    }
}
