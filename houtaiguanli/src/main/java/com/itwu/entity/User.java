package com.itwu.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String openId;
    private String wxUnionId;
    private String studentNum;
    private String gender;
    private String studentName;
    private String introduction;
    private String head;
    private Integer classNum;
    private Integer status;
}
