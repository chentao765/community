package cn.ct.community.advice;

import cn.ct.community.dto.ResultDTO;
import cn.ct.community.exception.CustomizeErroCode;
import cn.ct.community.exception.CustomizeException;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Throwable ex, HttpServletRequest request, HttpServletResponse response) {
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            //返回json
            ResultDTO resultDTO=null;
            if(ex instanceof CustomizeException){
                resultDTO= ResultDTO.errorOf((CustomizeException)ex);
            }else{
               //系统异常
                resultDTO= ResultDTO.errorOf(CustomizeErroCode.SYSTEM_ERROR);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setStatus(200);
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
                writer.print(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
               return null;

        }else{
            //返回错误页面
            ModelAndView modelAndView= new ModelAndView("error");
            if(ex instanceof CustomizeException){
                modelAndView.addObject("message",ex.getMessage());
            }else{
                modelAndView.addObject("message","服务器炸了");
            }
            return modelAndView ;
        }


    }


}
