package com.lrh.praser;

import com.lrh.common.Constants;
import com.lrh.config.Strategy;
import com.lrh.entity.TableInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/7/21 10:55
 */
public class FreemarkerPrase {
    private static final Logger logger = LoggerFactory.getLogger(FreemarkerPrase.class);
    private static final String ENTITY_JAVA_FTL_PATH = "/template/Entity.java.ftl";
    private static final String CONTROLLER_JAVA_FTL_PATH = "/template/Controller.java.ftl";
    private static final String ISERVICE_JAVA_FTL_PATH = "/template/IService.java.ftl";
    private static final String SERVICEIMPL_JAVA_FTL_PATH = "/template/ServiceImpl.java.ftl";
    private static final String MAPPER_JAVA_FTL_PATH = "/template/Mapper.java.ftl";
    private static final String MAPPER_XML_FTL_PATH = "/template/mapper.xml.ftl";
    private static final String TABLEINFO_KEY = "tableInfo";
    private static final String STRATEGY_KEY = "strategy";
    private static final String JAVA_SUFFIX = ".java";
    private static final String XML_SUFFIX = ".xml";
    private static final String WHITE_SPACE = " ";
    private static Configuration configuration;

    static {
        configuration = new Configuration(Configuration.getVersion());
        try {
            configuration.setDirectoryForTemplateLoading(new File(FreemarkerPrase.class.getClassLoader().getResource("").getPath()));
            configuration.setDefaultEncoding("utf-8");
        } catch (IOException e) {
            logger.error("Freemarker init error", e);
            throw new RuntimeException("Freemarker init error", e);
        }
    }

    private static Template getTemplate(String path) {
        try {
            return configuration.getTemplate(path);
        } catch (IOException e) {
            logger.error("get Template error path={}", path, e);
            throw new RuntimeException("get Template error", e);
        }
    }

    public static void praseTemplate(List<TableInfo> tableInfoList, Strategy strategy) throws Exception {
        for (TableInfo tableInfo : tableInfoList) {
            tableInfo.init(strategy);
            praseEntity(tableInfo, strategy);
            praseMapper(tableInfo, strategy);
            praseMapperXml(tableInfo, strategy);
            praseService(tableInfo, strategy);
            praseServiceImpl(tableInfo, strategy);
            praseController(tableInfo, strategy);
        }
    }

    private static void praseController(TableInfo tableInfo, Strategy strategy) {
        String outPutFile = strategy.getPackageConfig().getControllerPath() + File.separator + tableInfo.getControllerName() + JAVA_SUFFIX;
        prase(tableInfo, strategy, CONTROLLER_JAVA_FTL_PATH, outPutFile);
    }

    private static void praseService(TableInfo tableInfo, Strategy strategy) {
        String outPutFile = strategy.getPackageConfig().getServicePath() + File.separator + tableInfo.getServiceName() + JAVA_SUFFIX;
        prase(tableInfo, strategy, ISERVICE_JAVA_FTL_PATH, outPutFile);
    }

    private static void praseServiceImpl(TableInfo tableInfo, Strategy strategy) {
        String outPutFile = strategy.getPackageConfig().getServiceImplPath() + File.separator + tableInfo.getServiceImplName() + JAVA_SUFFIX;
        prase(tableInfo, strategy, SERVICEIMPL_JAVA_FTL_PATH, outPutFile);
    }

    private static void praseMapper(TableInfo tableInfo, Strategy strategy) {
        String outPutFile = strategy.getPackageConfig().getMapperPath() + File.separator + tableInfo.getMapperName() + JAVA_SUFFIX;
        prase(tableInfo, strategy, MAPPER_JAVA_FTL_PATH, outPutFile);
    }

    private static void praseMapperXml(TableInfo tableInfo, Strategy strategy) {
        String outPutFile = strategy.getPackageConfig().getMapperXmlPath() + File.separator + tableInfo.getMapperXmlName() + XML_SUFFIX;
        prase(tableInfo, strategy, MAPPER_XML_FTL_PATH, outPutFile);
    }

    private static void praseEntity(TableInfo tableInfo, Strategy strategy) {
        String outPutFile = strategy.getPackageConfig().getEntityPath() + File.separator + tableInfo.getEntityName() + JAVA_SUFFIX;
        prase(tableInfo, strategy, ENTITY_JAVA_FTL_PATH, outPutFile);
    }

    private static void prase(TableInfo tableInfo, Strategy strategy, String templatePath, String outPutPath) {
        Template template = getTemplate(templatePath);
        Writer out = null;
        try {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put(TABLEINFO_KEY, tableInfo);
            dataMap.put(STRATEGY_KEY, strategy);
            File file = new File(outPutPath);
            file.createNewFile();
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPutPath, false)));
            template.process(dataMap, out);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }
}
