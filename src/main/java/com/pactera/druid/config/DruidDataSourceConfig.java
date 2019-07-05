package com.pactera.druid.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = DruidDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory")
public class DruidDataSourceConfig {

    static final String PACKAGE = "com.pactera.mapper";
    static final String TYPE_ALIASES_PACKAGE = "com.pactera.entity";
    static final String MAPPER_LOCATION = "classpath:mybatis/mapper/*.xml";

    @Value("${spring.datasource.url}")
    private String dbUrl;
      
    @Value("${spring.datasource.username}")
    private String username;
      
    @Value("${spring.datasource.password}")
    private String password;
      
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
      
    @Value("${spring.datasource.initialSize}")
    private int initialSize;
      
    @Value("${spring.datasource.minIdle}")
    private int minIdle;
      
    @Value("${spring.datasource.maxActive}")
    private int maxActive;
      
    @Value("${spring.datasource.maxWait}")
    private int maxWait;
      
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
      
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
      
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
      
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;
      
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;
      
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;
      
    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
      
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
      
    @Value("${spring.datasource.filters}")
    private String filters;
      
    @Value("${spring.datasource.connectionProperties}")
    private String connectionProperties;

    //@Bean(name = "dataSource")
    @Bean(name = "dataSource", destroyMethod = "close",initMethod = "init")
    @Primary
    public DruidDataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        
        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        datasource.setConnectionProperties(connectionProperties);
        
        return datasource;
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DruidDataSourceConfig.MAPPER_LOCATION));
        sessionFactory.setTypeAliasesPackage(DruidDataSourceConfig.TYPE_ALIASES_PACKAGE);
        return sessionFactory.getObject();
    }

}
