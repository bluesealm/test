<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${tableInfo.mapperClass}">

    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${tableInfo.entityClass}">
    <#list tableInfo.tableFieldList as field>
        <#if field.primary><#--生成主键排在第一位-->
            <id column="${field.filedName}" property="${field.propertyName}"/>
        </#if>
    </#list>
    <#list tableInfo.tableFieldList as field>
        <#if !field.primary><#--生成普通字段 -->
            <result column="${field.filedName}" property="${field.propertyName}"/>
        </#if>
    </#list>
    </resultMap>

    <sql id="Table_Name">
    ${tableInfo.tableName}
    </sql>

    <sql id="Base_Column_List">
     <#list tableInfo.tableFieldList as field>
        <#if field_has_next>
            ${field.filedName} AS ${field.propertyName},
        <#else>
            ${field.filedName} AS ${field.propertyName}
        </#if>
     </#list>
    </sql>

    <sql id="Base_Column_Condition">
        WHERE 1=1
      <#list tableInfo.tableFieldList as field>
            <if test="${field.propertyName} !=null">and ${field.filedName}=<#noparse>#{</#noparse>${field.propertyName}<#noparse>}</#noparse> </if>
      </#list>
    </sql>

    <insert id="insertSelective" parameterType="${tableInfo.entityClass}" <#if tableInfo.primaryTableField.autoIncrement=true > keyProperty="${tableInfo.primaryTableField.propertyName}" useGeneratedKeys="true" </#if> >
         insert into <include refid="Table_Name"/>
        <trim prefix="(" suffixOverrides="," suffix=")">
            <#list tableInfo.tableFieldList as field>
                <if test="${field.propertyName} !=null">${field.filedName},</if>
            </#list>
        </trim>
         values
        <trim prefix="(" suffixOverrides="," suffix=")">
            <#list tableInfo.tableFieldList as field>
                <if test="${field.propertyName} !=null"><#noparse>#{</#noparse>${field.propertyName}<#noparse>}</#noparse>,</if>
            </#list>
        </trim>
    </insert>

    <insert id="batchInsertAllColumn" parameterType="java.util.List" <#if tableInfo.primaryTableField.autoIncrement=true > keyProperty="${tableInfo.primaryTableField.propertyName}" useGeneratedKeys="true" </#if>>
        insert into <include refid="Table_Name"/>
        <trim prefix="(" suffixOverrides="," suffix=")">
            <#list tableInfo.tableFieldList as field>
                ${field.filedName},
            </#list>
        </trim>
        values
        <foreach collection="list" item="item" index="index" separator=",">
            <trim prefix="(" suffixOverrides="," suffix=")">
             <#list tableInfo.tableFieldList as field>
                 <#noparse>#{</#noparse>item.${field.propertyName}<#noparse>},</#noparse>
             </#list>
            </trim>
        </foreach>
    </insert>

    <update id="updateSelectiveById" parameterType="${tableInfo.entityClass}" >
        update <include refid="Table_Name"/>
        <trim prefix="set" suffixOverrides="," >
            <#list tableInfo.tableFieldList as field>
                <if test="${field.propertyName} !=null">${field.filedName}=<#noparse>#{</#noparse>${field.propertyName}<#noparse>}</#noparse>,</if>
            </#list>
        </trim>
        where
        ${tableInfo.primaryTableField.filedName}=<#noparse>#{</#noparse>${tableInfo.primaryTableField.propertyName}<#noparse>}</#noparse>
    </update>

    <update id="batchUpdateSelectiveById" parameterType="list" >
        <foreach collection="list" item="item" index="index" separator=";">
            update <include refid="Table_Name"/>
             <trim prefix="set" suffixOverrides="," suffix="">
             <#list tableInfo.tableFieldList as field>
                <if test="item.${field.propertyName} !=null">${field.filedName}=<#noparse>#{</#noparse>item.${field.propertyName}<#noparse>}</#noparse>,</if>
             </#list>
             </trim>
             where
             ${tableInfo.primaryTableField.filedName}=<#noparse>#{</#noparse>item.${tableInfo.primaryTableField.propertyName}<#noparse>}</#noparse>
        </foreach>
    </update>

     <delete id="deleteById">
         delete from <include refid="Table_Name"/> where ${tableInfo.primaryTableField.filedName}= <#noparse>#{</#noparse>${tableInfo.primaryTableField.propertyName}<#noparse>}</#noparse>
     </delete>

    <delete id="deleteByColumn">
        delete from <include refid="Table_Name"/> <include refid="Base_Column_Condition"/>
    </delete>

    <select id="selectById"  resultMap="BaseResultMap">
        select * from <include refid="Table_Name"/>  where ${tableInfo.primaryTableField.filedName}= <#noparse>#{</#noparse>${tableInfo.primaryTableField.propertyName}<#noparse>}</#noparse>
    </select>

    <select id="selectList" resultMap="BaseResultMap">
        select * from <include refid="Table_Name"/>  <include refid="Base_Column_Condition"/>
    </select>
    <!-- 数据库常规操作 end  -->
    <!-- 数据库自定义 end  -->
</mapper>
