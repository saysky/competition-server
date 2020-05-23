package com.xxx.project.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    // id 生成策略，生成uuid
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    private String id;
    // 创建时间，插入数据是自动生成
    @CreatedDate
    // 将String转换成Date，一般前台给后台传值时用
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    // 将Date转换成String  一般后台传值给前台时
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date modifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
