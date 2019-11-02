package com.lrh.springboot.mybatis.config;

import com.lrh.springboot.mybatis.config.datasource.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/10/7 17:42
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "dataSource1")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

//    @Bean(name = "dataSource2")
//    @ConfigurationProperties(prefix = "spring.datasource.ds2")
//    public DataSource dataSource2() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "dataSource3")
//    @ConfigurationProperties(prefix = "spring.datasource.ds3")
//    public DataSource dataSource3() {
//        return DataSourceBuilder.create().build();
//    }

    @Bean(name = "dynamicDataSource")
    public DynamicDataSource DataSource() {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceType.DS1, dataSource());
//        targetDataSource.put(DataSourceType.DS2, dataSource2());
//        targetDataSource.put(DataSourceType.DS3, dataSource3());
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(dataSource());
        return dataSource;
    }
}
