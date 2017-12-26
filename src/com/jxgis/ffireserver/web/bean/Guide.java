package com.jxgis.ffireserver.web.bean;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 寻鉴定表
 * */
@Entity
@Table(name = "x_s_guide")
public class Guide extends IdEntity implements java.io.Serializable {

    private String Title;//标题
    private String content;//内容
    private String time;//时间
    private String Read;//阅读量
    private String imgl;//图片

}
