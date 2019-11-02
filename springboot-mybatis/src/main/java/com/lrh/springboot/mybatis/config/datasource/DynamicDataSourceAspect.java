package com.lrh.springboot.mybatis.config.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切换数据源Advice
 */
@Aspect
@Order(-10)//保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Pointcut("execution(* com.lrh.springboot.mybatis.service.impl.*.*(..))")
    public void service() {

    }

    @Before("service()")
    public void changeDataSource(JoinPoint point) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        TargetDataSource targetDataSource = methodSignature.getMethod().getAnnotation(TargetDataSource.class);
        if (targetDataSource == null) {
            targetDataSource = point.getTarget().getClass().getAnnotation(TargetDataSource.class);
        }
        if (targetDataSource == null) {
            System.out.println("使用默认数据源");
        } else {
            logger.info("Use DataSource : {} > {}", targetDataSource.value(), point.getSignature());
            if ( DynamicDataSourceContextHolder.getDataSourceType()!=null){
                logger.error("already has dataSource");
            }else {
                DynamicDataSourceContextHolder.setDataSourceType(targetDataSource.value());
            }
        }

    }

    @After("service()")
    public void restoreDataSource(JoinPoint point) {
        logger.debug("Revert DataSource : {} > {}");
        DynamicDataSourceContextHolder.clearDataSourceType();
    }

}
