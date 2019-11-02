package com.lrh.springboot.mybatis.entity;

import java.io.Serializable;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019-11-02 16:38:59
 */
public class StudentCopy implements Serializable{
  private static final long serialVersionUID = -1L;
  /**
   * 主键
   */
  private Integer iId;
  /**
   * 密码
   */
  private String sPwd2;
  /**
   * 姓名
   */
  private String sName2;
  /**
   * 年龄
   */
  private Integer iAge2;

  public Integer getIId(){
         return this.iId;
  }
  public void setIId(Integer iId){
          this.iId=iId;
  }

  public String getSPwd2(){
         return this.sPwd2;
  }
  public void setSPwd2(String sPwd2){
          this.sPwd2=sPwd2;
  }

  public String getSName2(){
         return this.sName2;
  }
  public void setSName2(String sName2){
          this.sName2=sName2;
  }

  public Integer getIAge2(){
         return this.iAge2;
  }
  public void setIAge2(Integer iAge2){
          this.iAge2=iAge2;
  }

}