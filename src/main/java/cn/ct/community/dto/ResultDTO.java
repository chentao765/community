package cn.ct.community.dto;

import cn.ct.community.exception.CustomizeErroCode;
import cn.ct.community.exception.CustomizeException;

public class ResultDTO<T> {
    private Integer code;
    private String message;
    private  T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultDTO(){};

    public ResultDTO(Integer code, String messaged) {
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

    public static<T>  ResultDTO okOf(T data){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("ok");
        resultDTO.setData(data);
        return  resultDTO;
    }
}
