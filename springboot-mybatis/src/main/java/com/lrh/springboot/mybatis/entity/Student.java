package com.lrh.springboot.mybatis.entity;

import java.io.Serializable;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019-11-02 16:25:43
 */
public class Student implements Serializable{
  private static final long serialVersionUID = -1L;
  /**
   * 主键
   */
  private Integer iId;
  /**
   * 密码
   */
  private String sPwd;
  /**
   * 姓名
   */
  private String sName;
  /**
   * 年龄
   */
  private Integer iAge;

  public Integer getIId(){
         return this.iId;
  }
  public void setIId(Integer iId){
          this.iId=iId;
  }

  public String getSPwd(){
         return this.sPwd;
  }
  public void setSPwd(String sPwd){
          this.sPwd=sPwd;
  }

  public String getSName(){
         return this.sName;
  }
  public void setSName(String sName){
          this.sName=sName;
  }

  public Integer getIAge(){
         return this.iAge;
  }
  public void setIAge(Integer iAge){
          this.iAge=iAge;
  }

}