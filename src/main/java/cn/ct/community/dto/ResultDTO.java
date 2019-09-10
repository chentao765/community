package cn.ct.community.dto;

import cn.ct.community.exception.CustomizeErroCode;
import cn.ct.community.exception.CustomizeException;

public class ResultDTO {
    private Integer code;
    private String message;
    public ResultDTO(){};

    public ResultDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ResultDTO errorOf(CustomizeErroCode erroCode){

        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(erroCode.getCode());
        resultDTO.setMessage(erroCode.getMessage());
        return  resultDTO;
    }
    public static ResultDTO errorOf(Integer code,String message){

        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return  resultDTO;
    }

    public static ResultDTO errorOf(CustomizeException e){
        return errorOf(e.getCode(),e.getMessage());
    }

    public static ResultDTO okOf() {

        return new ResultDTO(200,"ok");
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
