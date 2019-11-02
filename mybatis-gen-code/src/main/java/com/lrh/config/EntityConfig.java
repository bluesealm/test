package com.lrh.config;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/10/6 12:46
 * 实体类相关配置
 */
public class EntityConfig {
    /**
     * 文件名称规则  StudentEntity.java
     * 文件名称规则  StudentMapper.java
     */
    private String entityName = "%sEntity";
    private String mapperName = "%sMapper";
    private String mapperXmlName = "%sMapper";
    private String serviceName = "I%sService";
    private String serviceImplName = "%sServiceImpl";
    private String controllerName = "%sController";


    private static final String defaultSuperMapperClass="com.lrh.mybatis.BaseMapper";
    private static final String defaultSuperServiceClass="com.lrh.mybatis.IService";
    private static final String defaultSuperServiceImplClass="com.lrh.mybatis.ServiceImpl";
    /**
     * 继承的父类 包名.类名
     * "com.lrh.mybatis.IService"
     */
    private String superMapperClass=defaultSuperMapperClass;
    private String superServiceClass=defaultSuperServiceClass;
    private String superServiceImplClass=defaultSuperServiceImplClass;
    /**
     * 继承的父类 类名
     * IService
     */
    private String superMapperSimpleName;
    private String superServiceSimpleName;
    private String superServiceImplSimpleName;

    public void init() {
        //父类生成 类名 ftl中使用
        if (superMapperClass != null) {
            this.superMapperSimpleName = superMapperClass.substring(superMapperClass.lastIndexOf(".") + 1);
        }
        if (superServiceImplClass != null) {
            this.superServiceImplSimpleName = superServiceImplClass.substring(superServiceImplClass.lastIndexOf(".") + 1);
        }
        if (superServiceClass != null) {
            this.superServiceSimpleName = superServiceClass.substring(superServiceClass.lastIndexOf(".") + 1);
        }
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getMapperXmlName() {
        return mapperXmlName;
    }

    public void setMapperXmlName(String mapperXmlName) {
        this.mapperXmlName = mapperXmlName;
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

    public String getSuperMapperClass() {
        return superMapperClass;
    }

    public void setSuperMapperClass(String superMapperClass) {
        this.superMapperClass = superMapperClass;
    }

    public String getSuperServiceClass() {
        return superServiceClass;
    }

    public void setSuperServiceClass(String superServiceClass) {
        this.superServiceClass = superServiceClass;
    }

    public String getSuperServiceImplClass() {
        return superServiceImplClass;
    }

    public void setSuperServiceImplClass(String superServiceImplClass) {
        this.superServiceImplClass = superServiceImplClass;
    }

    public String getSuperMapperSimpleName() {
        return superMapperSimpleName;
    }

    public void setSuperMapperSimpleName(String superMapperSimpleName) {
        this.superMapperSimpleName = superMapperSimpleName;
    }

    public String getSuperServiceSimpleName() {
        return superServiceSimpleName;
    }

    public void setSuperServiceSimpleName(String superServiceSimpleName) {
        this.superServiceSimpleName = superServiceSimpleName;
    }

    public String getSuperServiceImplSimpleName() {
        return superServiceImplSimpleName;
    }

    public void setSuperServiceImplSimpleName(String superServiceImplSimpleName) {
        this.superServiceImplSimpleName = superServiceImplSimpleName;
    }
}
