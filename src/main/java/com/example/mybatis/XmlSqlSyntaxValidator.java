//package com.example.mybatis;
//
//import net.sf.jsqlparser.JSQLParserException;
//import net.sf.jsqlparser.parser.CCJSqlParserUtil;
//import net.sf.jsqlparser.statement.Statement;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Map;
//
///**
// * XML SQL 语法校验工具（基于 JSQLParser，语法错误直接抛出异常）
// */
//public class XmlSqlSyntaxValidator {
//    private static final Logger logger = LoggerFactory.getLogger(XmlSqlSyntaxValidator.class);
//
//    /**
//     * 校验所有 XML 中的 SQL 语法
//     * 核心逻辑：JSQLParser 解析 SQL，解析失败则说明语法错误
//     */
//    public static void validateAllXmlSql() {
//        // 1. 读取所有 XML 中的 SQL
//        Map<String, String> sqlMap = XmlSqlReader.readAllXmlSql();
//
//        // 2. 逐个校验 SQL 语法
//        for (Map.Entry<String, String> entry : sqlMap.entrySet()) {
//            String methodKey = entry.getKey(); // Mapper方法标识
//            String sql = entry.getValue();     // 待校验的 SQL
//
//            logger.info("开始校验 XML SQL - {}: {}", methodKey, sql);
//            try {
//                // 核心：JSQLParser 解析 SQL（语法错误会抛出 JSQLParserException）
//                Statement statement = CCJSqlParserUtil.parse(sql);
//                logger.info("XML SQL 语法校验通过 - {}", methodKey);
//            } catch (JSQLParserException e) {
//                // 语法错误：抛出运行时异常，阻断编译/提交
//                String errorMsg = String.format(
//                        "XML SQL 语法错误 - 方法：%s，SQL：%s，错误信息：%s",
//                        methodKey, sql, e.getMessage()
//                );
//                logger.error(errorMsg);
//                throw new RuntimeException(errorMsg);
//            }
//        }
//    }
//}