package cn.ct.community.exception;

public enum CustomizeErroCode implements  ICustomizeErrorCode {

    QUESTION_NOT_FOUND ("你的问题找不到，要不换个试试");
    private String message;

    CustomizeErroCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
