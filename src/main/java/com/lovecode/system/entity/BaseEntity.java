package com.lovecode.system.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Data
public class BaseEntity implements Serializable {
    @Column(
            name = "created_at"
    )
    protected Timestamp createdAt;
    @Column(
            name = "updated_at"
    )
    protected Timestamp updatedAt;
    @Column(
            name = "created_by"
    )
    protected String createdBy;
    @Column(
            name = "updated_by"
    )
    protected String updatedBy;


    BaseEntity() {
    }

    public BaseEntity(Timestamp createdAt, Timestamp updatedAt, String createdBy, String updatedBy) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
