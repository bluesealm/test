package com.lrh.entity;

import com.lrh.common.Constants;
import com.lrh.config.Strategy;
import com.lrh.rule.PropertyRule;
import com.lrh.util.FieldUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/7/13 17:48
 */
public class TableInfo implements Serializable {
    private String tableName;
    private String entityName;
    private String mapperName;
    private String mapperXmlName;
    private String serviceName;
    private String serviceImplName;
    private String controllerName;
    private String entityClass;
    private String mapperClass;
    private String serviceClass;
    private String serviceImplClass;
    private String controllerClass;
    private String comment;
    private List<TableField> tableFieldList;
    private TableField primaryTableField;
    private List<String> importList = new ArrayList<>();
    private String baseComumnList;

    public void init(Strategy strategy) {
        this.initNames(strategy);
    }

    private void initNames(Strategy strategy) {
        if (strategy.getPropertyRule().equals(PropertyRule.under_line_to_camel)) {
            this.entityName= FieldUtils.upperFirstCase(FieldUtils.underLineToCamel(tableName));
        } else {
            this.entityName= FieldUtils.upperFirstCase(tableName);
        }
        mapperName = String.format(strategy.getEntityConfig().getMapperName(), entityName);
        mapperXmlName = String.format(strategy.getEntityConfig().getMapperXmlName(), entityName);
        serviceName = String.format(strategy.getEntityConfig().getServiceName(), entityName);
        serviceImplName =String.format(strategy.getEntityConfig().getServiceImplName(), entityName);
        controllerName = String.format(strategy.getEntityConfig().getControllerName(), entityName);

        entityClass = strategy.getPackageConfig().getEntityPackage()+"."+entityName;
        mapperClass = strategy.getPackageConfig().getMapperPackage()+"."+mapperName;
        serviceClass = strategy.getPackageConfig().getServicePackage()+"."+serviceName;
        serviceImplClass= strategy.getPackageConfig().getServiceImplPackage()+"."+serviceImplClass;
        controllerClass = strategy.getPackageConfig().getControllerPackage()+"."+controllerName;

    }

    public String getMapperXmlName() {
        return mapperXmlName;
    }

    public void setMapperXmlName(String mapperXmlName) {
        this.mapperXmlName = mapperXmlName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<TableField> getTableFieldList() {
        return tableFieldList;
    }

    public void setTableFieldList(List<TableField> tableFieldList) {
        this.tableFieldList = tableFieldList;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public List<String> getImportList() {
        return importList;
    }

    public void setImportList(List<String> importList) {
        this.importList = importList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPrimaryTableField(TableField tableField) {
       this.primaryTableField = tableField;
    }

    public TableField getPrimaryTableField() {
        return primaryTableField;
    }

    public void setImport(String importStr) {
        //string imteger ..不需要导入
        if (StringUtils.isEmpty(importStr)) {
            return;
        }
        if (!importList.contains(importStr)) {
            importList.add(importStr);
        }
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getBaseComumnList() {
        return baseComumnList;
    }

    public void setBaseComumnList(String baseComumnList) {
        this.baseComumnList = baseComumnList;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public String getMapperClass() {
        return mapperClass;
    }

    public void setMapperClass(String mapperClass) {
        this.mapperClass = mapperClass;
    }

    public String getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getServiceImplClass() {
        return serviceImplClass;
    }

    public void setServiceImplClass(String serviceImplClass) {
        this.serviceImplClass = serviceImplClass;
    }

    public String getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(String controllerClass) {
        this.controllerClass = controllerClass;
    }
}
