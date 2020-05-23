package com.xxx.project.entity;

import com.xxx.project.base.BaseEntity;

import javax.persistence.*;

@Table
@Entity
public class PlanFile extends BaseEntity {
    // 某个实体类的id，表明是哪一个实体类的文件
    private String extraId;

    // 文件名称
    private String name;

    //文件类型
    private String type;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] file;

    public String getExtraId() {
        return extraId;
    }

    public void setExtraId(String extraId) {
        this.extraId = extraId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
