package com.example.mybatis;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 读取 MyBatis XML 中的所有 SQL 语句
 */
public class XmlSqlReader {
    private static final Logger logger = LoggerFactory.getLogger(XmlSqlReader.class);
    // 你的 XML Mapper 文件路径（根据项目调整）
    private static final String XML_MAPPER_PATH = "classpath:mabatis/mapper/**/*.xml";

    /**
     * 读取所有 XML 中的 SQL
     * @return key: Mapper方法唯一标识（namespace + id）, value: SQL 语句
     */
    public static Map<String, String> readAllXmlSql() {
        Map<String, String> sqlMap = new HashMap<>();
        Configuration configuration = new Configuration();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            // 扫描所有 XML Mapper 文件
            Resource[] resources = resolver.getResources(XML_MAPPER_PATH);
            for (Resource resource : resources) {
                try (InputStream is = resource.getInputStream()) {
                    // 解析 XML Mapper 到 MyBatis 配置中
                    XMLMapperBuilder mapperBuilder = new XMLMapperBuilder(is, configuration, resource.getFilename(), configuration.getSqlFragments());
                    mapperBuilder.parse();
                }
            }

            // 从 MyBatis 配置中提取所有 SQL 语句
            Set<String> statementNames = (Set<String>) configuration.getMappedStatementNames();
            for (String name : statementNames) {
                // 获取绑定后的完整 SQL（替换动态标签后）
                String sql = configuration.getMappedStatement(name).getBoundSql(new Object()).getSql();
                sqlMap.put(name, sql);
                logger.info("读取 XML SQL - {}: {}", name, sql);
            }
        } catch (IOException e) {
            logger.error("读取 XML SQL 失败", e);
            throw new RuntimeException("读取 XML SQL 失败", e);
        }
        return sqlMap;
    }
}