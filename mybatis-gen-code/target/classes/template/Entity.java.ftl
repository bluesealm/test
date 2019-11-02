package ${strategy.packageConfig.entityPackage};

import java.io.Serializable;
<#list tableInfo.importList as import>
import ${import};
</#list>

/**
 * @author ${strategy.author}
 * @version 1.0
 * @date ${strategy.date}
 */
<#if strategy.swagger.value == "open">
@ApiModel(description = ${tableInfo.comment})
</#if>
public class ${tableInfo.entityName} implements Serializable{
  private static final long serialVersionUID = -1L;
<#list tableInfo.tableFieldList as tableField>
  <#if strategy.swagger.value == "open">
  @ApiModelProperty(value="${tableField.comment}",dataType="${tableField.propertyType}")
  <#else>
  /**
   * ${tableField.comment}
   */
    </#if>
  private ${tableField.propertyType} ${tableField.propertyName};
</#list>

<#list tableInfo.tableFieldList as tableField>
  public ${tableField.propertyType} ${tableField.getMethodName}(){
         return this.${tableField.propertyName};
  }
  public void ${tableField.setMethodName}(${tableField.propertyType} ${tableField.propertyName}){
          this.${tableField.propertyName}=${tableField.propertyName};
  }

</#list>
}