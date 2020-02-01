package com.lovecode.system.logs.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "operationName")
    private String operationName;

    @Column(name = "request")
    private String request;

    @Column(name = "response")
    private String response;

    @Column(name = "error")
    private Boolean error;

    @Column(name = "takeTime")
    private Long takeTime;

    @Column(name = "createTime")
    private Date createTime;

    @Column(name = "stack")
    private String stack;

    public void setOperationName(String operationName) {
        this.operationName = operationName == null ? null : operationName.trim();
    }

    public void setRequest(String request) {
        this.request = request == null ? null : request.trim();
    }


    public void setResponse(String response) {
        this.response = response == null ? null : response.trim();
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public void setTakeTime(Long takeTime) {
        this.takeTime = takeTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setStack(String stack) {
        this.stack = stack == null ? null : stack.trim();
    }
}
