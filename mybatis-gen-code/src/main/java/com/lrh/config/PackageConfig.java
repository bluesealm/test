package com.lrh.config;

import com.lrh.util.FileUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/10/6 10:48
 */
public class PackageConfig {
    //默认的包名名称
    private static final String ENTITY_PACKAGE = ".entity";
    private static final String MAPPER_PACKAGE = ".mapper";
    private static final String SERVICE_PACKAGE = ".service";
    private static final String SERVICE_IMPL_PACKAGE = ".service.impl";
    private static final String CONTROLLER_PACKAGE = ".controller";
    //父路径
    private String basePackage;

    /**
     * entity mapper 等package路径
     */
    private String entityPackage;
    private String mapperPackage;
    private String servicePackage;
    private String serviceImplPackage;
    private String controllerPackage;

    /**
     * 包名转路径
     */
    private String entityPath;
    private String mapperPath;
    private String mapperXmlPath;
    private String servicePath;
    private String serviceImplPath;
    private String controllerPath;

    /**
     * 初始化
     *
     * @param outPutPath 文件输出父路径
     */
    public void init(String outPutPath) {
        //要不统一包名前缀 要不全部自定义 包名不能为空
        if (basePackage == null && (entityPackage == null || mapperPackage == null || serviceImplPackage == null || servicePackage == null || controllerPackage == null)) {
            throw new RuntimeException("basePackage is null and one of other package is null");
        }
        //初始化包名
        if (entityPackage == null) {
            this.setEntityPackage(basePackage + ENTITY_PACKAGE);
        }
        if (mapperPackage == null) {
            this.setMapperPackage(basePackage + MAPPER_PACKAGE);
        }
        if (serviceImplPackage == null) {
            this.setServiceImplPackage(basePackage + SERVICE_IMPL_PACKAGE);
        }
        if (servicePackage == null) {
            this.setServicePackage(basePackage + SERVICE_PACKAGE);
        }
        if (controllerPackage == null) {
            this.setControllerPackage(basePackage + CONTROLLER_PACKAGE);
        }
        //包名转输出路径
        if (entityPath == null) {
            this.setEntityPath(outPutPath + FileUtil.packageToPath(entityPackage));
        }
        if (mapperPath == null) {
            this.setMapperPath(outPutPath + FileUtil.packageToPath(mapperPackage));
        }
        if (mapperXmlPath == null) {
            this.setMapperXmlPath(mapperPath + File.separator + "xml");
        }
        if (serviceImplPath == null) {
            this.setServiceImplPath(outPutPath + FileUtil.packageToPath(serviceImplPackage));
        }
        if (servicePath == null) {
            this.setServicePath(outPutPath + FileUtil.packageToPath(servicePackage));
        }
        if (controllerPath == null) {
            this.setControllerPath(outPutPath + FileUtil.packageToPath(controllerPackage));
        }
        this.initDirectory();
    }

    /**
     * 输出路径清空
     */
    private void initDirectory() {
        try {
            //先清空文件
            FileUtils.deleteDirectory(new File(this.getEntityPath()));
            FileUtils.deleteDirectory(new File(this.getServicePath()));
            FileUtils.deleteDirectory(new File(this.getServiceImplPath()));
            FileUtils.deleteDirectory(new File(this.getMapperPath()));
            FileUtils.deleteDirectory(new File(this.getMapperXmlPath()));
            FileUtils.deleteDirectory(new File(this.getControllerPath()));

            FileUtils.forceMkdir(new File(this.getEntityPath()));
            FileUtils.forceMkdir(new File(this.getServicePath()));
            FileUtils.forceMkdir(new File(this.getServiceImplPath()));
            FileUtils.forceMkdir(new File(this.getMapperPath()));
            FileUtils.forceMkdir(new File(this.getMapperXmlPath()));
            FileUtils.forceMkdir(new File(this.getControllerPath()));
        } catch (Exception e) {
            throw new RuntimeException("initDirectory error ", e);
        }
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getServiceImplPackage() {
        return serviceImplPackage;
    }

    public void setServiceImplPackage(String serviceImplPackage) {
        this.serviceImplPackage = serviceImplPackage;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public String getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public String getMapperPath() {
        return mapperPath;
    }

    public void setMapperPath(String mapperPath) {
        this.mapperPath = mapperPath;
    }

    public String getMapperXmlPath() {
        return mapperXmlPath;
    }

    public void setMapperXmlPath(String mapperXmlPath) {
        this.mapperXmlPath = mapperXmlPath;
    }

    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    public String getServiceImplPath() {
        return serviceImplPath;
    }

    public void setServiceImplPath(String serviceImplPath) {
        this.serviceImplPath = serviceImplPath;
    }

    public String getControllerPath() {
        return controllerPath;
    }

    public void setControllerPath(String controllerPath) {
        this.controllerPath = controllerPath;
    }
}
