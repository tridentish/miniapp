package com.itwu.chenyidan.itheima.domain;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String open_id;
    private String wx_union_id;
    private String student_num;
    private String gender;
    private String student_name;
    private String introduction;
    private Integer class_num;
    private Integer status;
}
