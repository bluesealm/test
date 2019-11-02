package com.lrh;

import com.lrh.config.EntityConfig;
import com.lrh.config.PackageConfig;
import com.lrh.config.Strategy;
import com.lrh.entity.TableInfo;
import com.lrh.praser.FreemarkerPrase;
import com.lrh.praser.TablePrase;
import com.lrh.rule.Lombok;
import com.lrh.rule.PropertyRule;
import com.lrh.rule.Swagger;

import javax.swing.text.html.parser.Entity;
import java.util.List;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/7/20 12:52
 */
public class GenCode {
    private static final String superMapperClass="com.lrh.mybatis.BaseMapper";
    private static final String superServiceClass="com.lrh.mybatis.IService";
    private static final String superServiceImplClass="com.lrh.mybatis.ServiceImpl";
    private static final String basePackage="com.lrh.springboot.mybatis";
    private static final String out_put_path="D:\\gen";
    public static void main(String[] args) {
        //类属性配置
        EntityConfig entityConfig=new EntityConfig();
        entityConfig.setSuperMapperClass(superMapperClass);
        entityConfig.setSuperServiceClass(superServiceClass);
        entityConfig.setSuperServiceImplClass(superServiceImplClass);
        //包名配置
        PackageConfig packageConfig=new PackageConfig();
        packageConfig.setBasePackage(basePackage);//生成java包名 默認*.entity *.service.. 可以分開設置 詳見Package配置
//        packageConfig.setControllerPackage("com.lrh.springboot.mybatis.controller");
//        packageConfig.setServicePackage("com.lrh.springboot.mybatis.service");
//        packageConfig.setServiceImplPackage("com.lrh.springboot.mybatis.service.Impl");
//        packageConfig.setEntityPackage("com.lrh.springboot.mybatis.entity");
//        packageConfig.setMapperPackage("com.lrh.springboot.mybatis.mapper");
        Strategy strategy=new Strategy();
        strategy.setPropertyRule(PropertyRule.under_line_to_camel);
        strategy.setSwagger(Swagger.CLOSE);
        strategy.setLombok(Lombok.CLOSE);
        strategy.setOutPutPath(out_put_path);
        strategy.setAuthor("lironghui");
        strategy.setEntityConfig(entityConfig);
        strategy.setPackageConfig(packageConfig);

        strategy.setTables(new String[]{"student_copy"});
        gen(strategy);
    }
    private static void gen(Strategy strategy){
        try {
            strategy.init();
            List<TableInfo> tableInfoList= TablePrase.prase(strategy);
            FreemarkerPrase.praseTemplate(tableInfoList,strategy);
            openDir(strategy.getOutPutPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void openDir(String out_put_path){
        final Runtime runtime = Runtime.getRuntime();
        Process process = null;
        final String cmd = "rundll32 url.dll FileProtocolHandler file://"+out_put_path;//要打开的文件路径。
        try {
            process = runtime.exec(cmd);
        } catch (final Exception e) {
            System.out.println("Error exec!");
        }
    }

}
