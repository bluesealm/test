package com.lrh.springboot.mybatis.config;

import com.lrh.mybatis.pagehelper.PaginationInterceptor;
import com.lrh.springboot.mybatis.config.datasource.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/10/7 14:50
 */
@Configuration
@MapperScan(basePackages={"com.lrh.springboot.mybatis.mapper"})
public class MybatisConfig {
    private static final String MAPPER_LOCATION="classpath*:mapping/*.xml";
    //注册插件
    @Bean
    public PaginationInterceptor myPagePlugin() {
        PaginationInterceptor myPlugin = new PaginationInterceptor();
        //设置参数，比如阈值等，可以在配置文件中配置，这里直接写死便于测试
        Properties properties = new Properties();
        //这里设置慢查询阈值为1毫秒，便于测试
        properties.setProperty("dialect", "MYSQL");
        myPlugin.setProperties(properties);
        return myPlugin;
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return bean.getObject();
    }
}
