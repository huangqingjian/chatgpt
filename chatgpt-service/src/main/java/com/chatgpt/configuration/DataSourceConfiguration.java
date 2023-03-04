package com.chatgpt.configuration;

import com.chatgpt.configuration.interceptor.MybatisInterceptor;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 数据源配置
 */
@MapperScan(value = "com.chatgpt.**.mapper")
@EnableTransactionManagement
@Configuration
public class DataSourceConfiguration {
    /**
     * 数据库连接路径
     */
    @Value(value = "${jdbc.url}")
    private String url;

    /**
     * 数据库连接账号名
     */
    @Value(value = "${jdbc.username}")
    private String username;

    /**
     * 数据库连接账号密码
     */
    @Value(value = "${jdbc.password}")
    private String password;

    /**
     * 配置数据源
     */
    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(60000);
        dataSource.setMaxIdle(20);
        dataSource.setMinIdle(3);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(180);
        dataSource.setValidationQuery("select 1");
        dataSource.setValidationQueryTimeout(1);
        dataSource.setNumTestsPerEvictionRun(20);
        return dataSource;
    }

    /**
     * 数据库操作事务配置
     */
    @Primary
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 数据库操作框架mybatis配置文件解析配置
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:/mapper/**.xml");
        if (resources != null) {
            sessionFactory.setMapperLocations(resources);
        }
        sessionFactory.setPlugins(new Interceptor[]{mybatisInterceptor()});
        return sessionFactory.getObject();
    }

    /**
     * 自定义拦截器
     *
     * @return
     */
    @Bean
    public MybatisInterceptor mybatisInterceptor(){
        return new MybatisInterceptor();
    }


}
