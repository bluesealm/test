package ${strategy.packageConfig.serviceImplPackage};

import ${tableInfo.entityClass};
import ${strategy.entityConfig.superServiceImplClass};
import ${tableInfo.mapperClass};
import ${tableInfo.serviceClass};
import org.springframework.stereotype.Service;
/**
 * @author ${strategy.author}
 * @version 1.0
 * @date ${strategy.date}
 */
@Service
public class ${tableInfo.serviceImplName} extends ${strategy.entityConfig.superServiceImplSimpleName}<${tableInfo.mapperName},${tableInfo.entityName}>  implements ${tableInfo.serviceName}<${tableInfo.entityName}>{

}