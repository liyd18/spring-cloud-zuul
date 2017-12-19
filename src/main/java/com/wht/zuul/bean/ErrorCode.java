package com.wht.zuul.bean;

public enum ErrorCode {

       /*错误码-预留字段*/
        ACS_10001, //请求参数无效(空)
        ACS_10002, //请求参数错误
        ACS_10003, //校验超时
        ACS_10004, //校验失败
        ACS_10005, //登录超时
        ACS_10006, //操作过于频繁
        ACS_10007, //对象已存在
        ACS_10008, //无效的用户
        ACS_10009,  //系统故障
        ACS_10010,  //真实姓名错误
        ACS_10011,  //省份证号错误
        ACS_10012,  //手机号错误
        ACS_10013,  //springcloud调用失败

}
