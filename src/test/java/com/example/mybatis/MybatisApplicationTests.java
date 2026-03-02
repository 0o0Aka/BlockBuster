package com.example.mybatis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import com.example.mybatis.utils.SqlProcessorUtil;

@SpringBootTest
class MybatisApplicationTests {
    @Test
    void contextLoads() throws SQLException { ;
    }

//    @Test
//    public void testXmlSqlSyntax() {
//        // 执行 XML SQL 语法校验，失败则测试不通过
//        XmlSqlSyntaxValidator.validateAllXmlSql();
//    }
// 测试用 SQL（模拟项目中的真实 SQL）
private static final String TEST_SQL1 = "SELECT * FROM t_user WHERE id = 1";
    private static final String TEST_SQL2 = "DELETE FROM t_order"; // 无 WHERE 条件
    private static final String TEST_SQL3 = "SELECT id, name FROM t_user"; // 合法 SQL

    @Test
    public void testParseSql() {
        // 测试解析功能
        SqlProcessorUtil.ParseResult result = SqlProcessorUtil.parseSql(TEST_SQL1);
        System.out.println("解析结果：" + result.isSuccess());
        System.out.println("提取的表名：" + result.getTableNames());
        System.out.println("SQL 类型：" + result.getSqlTypes());
    }

    @Test
    public void testValidateSql() {
        // 测试校验功能
        SqlProcessorUtil.ValidateResult result1 = SqlProcessorUtil.validateSql(TEST_SQL1);
        System.out.println("TEST_SQL1 校验结果：" + result1.isValid() + " | 错误：" + result1.getErrorMsg());

        SqlProcessorUtil.ValidateResult result2 = SqlProcessorUtil.validateSql(TEST_SQL2);
        System.out.println("TEST_SQL2 校验结果：" + result2.isValid() + " | 错误：" + result2.getErrorMsg());

        SqlProcessorUtil.ValidateResult result3 = SqlProcessorUtil.validateSql(TEST_SQL3);
        System.out.println("TEST_SQL3 校验结果：" + result3.isValid() + " | 错误：" + result3.getErrorMsg());
    }

//    @Test
//    public void testRewriteSql() {
//        // 测试改写功能
//        String rewrittenSql = SqlProcessorUtil.rewriteSql(TEST_SQL3);
//        System.out.println("原SQL：" + TEST_SQL3);
//        System.out.println("改写后：" + rewrittenSql);
//    }

}
