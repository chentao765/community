package cn.ct.community.exception;

public enum CustomizeErroCode implements  ICustomizeErrorCode {
    USER_NOT_LOGIN(2001,"用户未登录"),
    QUESTION_NOT_FOUND(2002,"你的问题找不到，要不换个试试"),
    TARGET_NOT_FOUND(2003,"目标问题消失"),
    TYPE_NOT_CORRECT(2004,"评论类型不正确"),
    SYSTEM_ERROR(2005,"系统异常"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在"),
    CONTENT_NOT_EMPTY(2007,"内容不能为空"),
    NOTIFICATION_NOT_FOUND(2008,"通知消息不见了"),
    NOTIFICATION_NOT_YOU(2009,"不能查看别人的通知");


    private String message;
    private Integer code;

    CustomizeErroCode(Integer code,String message) {

        this.code=code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
