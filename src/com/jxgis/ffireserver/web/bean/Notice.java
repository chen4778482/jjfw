package com.jxgis.ffireserver.web.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "f_notice")
public class Notice extends IdEntity implements java.io.Serializable{

    private String msg;//内容
    private Date time;//时间


    @Column(columnDefinition = "LONGTEXT DEFAULT NULL")
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
