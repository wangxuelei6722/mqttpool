package com.wangxl.mqttpool.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TableNameStrategy
 * @Description: 自定义表名转换，忽略JPA命名规则
 * @Author
 * @Date 2022/3/25
 * @Version 1.0
 */
@Component
public class TableNameStrategy extends SpringPhysicalNamingStrategy {

   /* @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        String tableName = name.getText();
        return Identifier.toIdentifier(tableName);
    }*/

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        String columName = name.getText();
        return Identifier.toIdentifier(columName);
    }
}
