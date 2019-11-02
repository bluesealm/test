package com.lrh.mybatis.pagehelper;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/10/7 11:34
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class PaginationInterceptor implements Interceptor {
    private Dialect dialect;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        if (!StatementHandler.class.isAssignableFrom(target.getClass())) {
            return invocation.proceed();
        }
        StatementHandler statementHandler = (StatementHandler) target;
        MetaObject metaObject = getMetaObject(statementHandler);
        //获取查询接口映射的相关信息
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // 不是查询语句
        if (!mappedStatement.getSqlCommandType().equals(SqlCommandType.SELECT)) {
            return invocation.proceed();
        }
        Object paramObj = statementHandler.getParameterHandler().getParameterObject();
        RowBounds rowBounds = (RowBounds) metaObject.getValue("delegate.rowBounds");
        if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
            return invocation.proceed();
        }
        PageVO pageVO = null;
        if (PageVO.class.isAssignableFrom(rowBounds.getClass())) {
            pageVO = (PageVO) rowBounds;
        }
        if (pageVO == null) {
            return invocation.proceed();
        }
        BoundSql boundSql = statementHandler.getBoundSql();
        String originalSQL = boundSql.getSql();
        // 获取总记录数
        Connection connection = (Connection) invocation.getArgs()[0];
        String countSQL = dialect.buildCountSQL(originalSQL);
        this.queryTotal(countSQL, mappedStatement, paramObj, boundSql, connection, pageVO);
        int limit = (pageVO.getCurrentPage() - 1) * pageVO.getPageSize();
        int offset = pageVO.getCurrentPage() * pageVO.getPageSize();
        // 构建分页SQL
        String pageSQL = dialect.buildPageSQL(originalSQL, limit, offset);
        /*
         * <p> 禁用内存分页 </p>
         * <p> 内存分页会查询所有结果出来处理（这个很吓人的），如果结果变化频繁这个数据还会不准。</p>
         */
        metaObject.setValue("delegate.boundSql.sql", pageSQL);
        metaObject.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
        metaObject.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
        return invocation.proceed();

    }

    /**
     * 获取目标对象
     *
     * @param statementHandler
     * @return
     */
    private MetaObject getMetaObject(StatementHandler statementHandler) {
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环
        // 可以分离出最原始的的目标类)
        while (metaObject.hasGetter("h")) {
            metaObject = SystemMetaObject.forObject(metaObject.getValue("h"));
        }
        // 分离最后一个代理对象的目标类
        while (metaObject.hasGetter("target")) {
            metaObject = SystemMetaObject.forObject(metaObject.getValue("target"));
        }
        return metaObject;
    }

    /**
     * 获取总记录数
     */
    private void queryTotal(String countSQL, MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql, Connection connection, PageVO pageVO) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(countSQL)) {
            DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
            parameterHandler.setParameters(preparedStatement);
            int totalRecord = 0;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    totalRecord = resultSet.getInt(1);
                }
            }
            pageVO.setTotalCount(totalRecord);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object plugin(Object target) {
        if (StatementHandler.class.isAssignableFrom(target.getClass())) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        // 获取方言的参数
        this.dialect = DialectFactory.buildDialect(properties.getProperty("dialect"));
    }
}
