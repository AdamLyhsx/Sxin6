package com.adamly.xin6.response;

//统一的返回类型类
public class CommonReturnType {
//    状态是success/fail
    private String status;
//    返回json数据/统一的错误类型
    private Object content;

//    创建方法
    public static CommonReturnType create(Object content,String status){
        CommonReturnType type=new CommonReturnType();
        type.setContent(content);
        type.setStatus(status);
        return type;
    }
    public static CommonReturnType create(Object content){
        return create(content,"success");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
