package cn.ct.community.controller;

import cn.ct.community.mapper.UserMapper;
import cn.ct.community.model.User;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        User user=null;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies
        ) {
            if(cookie.getName().equals("token")){
               String token=cookie.getValue();
               user=userMapper.findByToken(token);
            }
        }
        //放入session
        request.getSession().setAttribute("user",user);

        return "index";
    }
}
