package cn.ct.community.exception;

import lombok.Data;


public class CustomizeException extends  RuntimeException {
    private String message;

    public CustomizeException(CustomizeErroCode erroCode) {
        this.message = erroCode.getMessage();
    }



    @Override
    public String getMessage() {
        return message;
    }
}
