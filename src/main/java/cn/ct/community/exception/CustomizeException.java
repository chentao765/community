package cn.ct.community.exception;




public class CustomizeException extends  RuntimeException {
    private Integer code;
    private String message;

    public CustomizeException(CustomizeErroCode erroCode) {
        this.code=erroCode.getCode();
        this.message = erroCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode(){
        return code;
    }
}
