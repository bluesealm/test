package com.lrh.config;

import com.lrh.rule.Lombok;
import com.lrh.rule.PropertyRule;
import com.lrh.rule.Swagger;
import com.lrh.util.DateUtils;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/7/20 12:57
 */
public class Strategy {
    //生成策略 驼峰
    private PropertyRule propertyRule = PropertyRule.under_line_to_camel;
    //是否开启swagger
    private Swagger swagger = Swagger.CLOSE;
    //是否开始lombok //TODO
    private Lombok lombok = Lombok.CLOSE;
    //包名配置
    private PackageConfig packageConfig;
    //实体类配置
    private EntityConfig entityConfig;

    private String[] tables = {};
    private String outPutPath;
    //注释
    private String author;
    private String date = DateUtils.currentTime();

    public void init() {
        if (outPutPath == null) {
            outPutPath = Strategy.class.getClassLoader().getResource("").getPath();
        }
        if (entityConfig==null||packageConfig==null){
            throw new NullPointerException("entityConfig or packageConfig must not be null");
        }
        //初始化类配置
        entityConfig.init();
        //初始化包属性配置
        packageConfig.init(outPutPath);
    }

    public PropertyRule getPropertyRule() {
        return propertyRule;
    }

    public void setPropertyRule(PropertyRule propertyRule) {
        this.propertyRule = propertyRule;
    }

    public Swagger getSwagger() {
        return swagger;
    }

    public void setSwagger(Swagger swagger) {
        this.swagger = swagger;
    }

    public Lombok getLombok() {
        return lombok;
    }

    public void setLombok(Lombok lombok) {
        this.lombok = lombok;
    }

    public String[] getTables() {
        return tables;
    }

    public void setTables(String[] tables) {
        this.tables = tables;
    }

    public String getOutPutPath() {
        return outPutPath;
    }

    public void setOutPutPath(String outPutPath) {
        this.outPutPath = outPutPath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PackageConfig getPackageConfig() {
        return packageConfig;
    }

    public void setPackageConfig(PackageConfig packageConfig) {
        this.packageConfig = packageConfig;
    }

    public EntityConfig getEntityConfig() {
        return entityConfig;
    }

    public void setEntityConfig(EntityConfig entityConfig) {
        this.entityConfig = entityConfig;
    }
}
