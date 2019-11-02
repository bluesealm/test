package com.lrh.praser;

import com.lrh.config.Strategy;
import com.lrh.db.DBUtils;
import com.lrh.db.SqlQuery;
import com.lrh.entity.TableField;
import com.lrh.entity.TableInfo;
import com.lrh.rule.PropertyRule;
import com.lrh.util.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/7/13 15:50
 */
public class TablePrase {
    private static final Logger logger = LoggerFactory.getLogger(TablePrase.class);

    private static final String FIELD = "Field";
    private static final String TYPE = "Type";
    private static final String COMMENT = "Comment";
    private static final String PRIMARY = "PRI";
    private static final String KEY = "KEY";
    private static final String EXTRA = "EXTRA";
    private static final String TABLE_NAME = "NAME";
    private static final String AUTO_INCREMENT = "auto_increment";

    public static List<TableInfo> prase(Strategy strategy) {
        String[] tableNames = strategy.getTables();
        Objects.requireNonNull(tableNames, "tables must not be null");
        List<TableInfo> tableInfoList = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.QUERY_TABLE.getSql());
            ResultSet resultSet = preparedStatement.executeQuery();
            Map<String, String> tableMap = new HashMap<>();
            while (resultSet.next()) {
                String tableName = resultSet.getString(TABLE_NAME);
                String tableComment = resultSet.getString(COMMENT);
                tableMap.put(tableName, tableComment);
            }
            for (String tableName : tableNames) {
                if (!tableMap.containsKey(tableName)) {
                    logger.error("table {} not exist", tableName);
                    continue;
                }
                TableInfo tableInfo = new TableInfo();
                tableInfo.setTableName(tableName);
                tableInfo.setComment(tableMap.get(tableName));
                tableInfo.setTableFieldList(praseTable(connection, tableName, tableInfo, strategy));
                tableInfoList.add(tableInfo);
            }

        } catch (Exception e) {
            logger.error("", e);
        }
        return tableInfoList;
    }

    /**
     * 解析表到字段
     *
     * @param connection
     * @param tableName
     * @param tableInfo
     * @param strategy
     * @return
     * @throws SQLException
     */
    private static List<TableField> praseTable(Connection connection, String tableName, TableInfo tableInfo, Strategy strategy) throws SQLException {
        List<TableField> tableFieldList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(String.format(SqlQuery.QUERY_FIELD.getSql(), tableName));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            TableField tableField = new TableField();
            tableField.setFiledName(resultSet.getString(FIELD));
            tableField.setFiledType(resultSet.getString(TYPE));
            tableField.setComment(resultSet.getString(COMMENT));
            tableField.setPrimary(PRIMARY.equals(resultSet.getString(KEY)));
            tableField.setAutoIncrement(AUTO_INCREMENT.equals((resultSet.getString(EXTRA))));
            //设置主键
            if (tableField.isPrimary()) {
                tableInfo.setPrimaryTableField(tableField);
            }
            //设置导入
            tableInfo.setImport(FieldUtils.sqlTypeToJava(tableField.getFiledType()).getDesc());
            //初始化
            tableField.init(strategy);
            tableFieldList.add(tableField);
        }
        return tableFieldList;
    }
}
