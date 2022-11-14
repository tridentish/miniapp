package com.itwu.entity;

import lombok.Data;

@Data
public class R<T> {
    private boolean flag;//1为成功，0为失败
    private Object data;//数据
    private String msg;//错误信息

    public R(){}
    //只知道成功与否
    public R(boolean flag ){
        this.flag=flag;
    }
    //知道成功与否与数据
    public R(boolean flag,Object data ){
        this.flag=flag;
        this.data=data;
    }
    //知道成功与否与错误信息
    public R(boolean flag,String msg ){
        this.flag=flag;
        this.msg=msg;
    }
    //知道所有
    public R(boolean flag,Object data,String msg ){
        this.flag=flag;
        this.data=data;
        this.msg=msg;
    }
}
