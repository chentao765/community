package cn.ct.community.advice;

import cn.ct.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException( Throwable ex) {
        ModelAndView modelAndView= new ModelAndView("error");
        if(ex instanceof CustomizeException){
            modelAndView.addObject("message",ex.getMessage());
        }else{
            modelAndView.addObject("message","服务器炸了");
        }
        return modelAndView ;
    }


}
