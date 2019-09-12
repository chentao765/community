package cn.ct.community.controller;

import cn.ct.community.dto.PaginationDTO;
import cn.ct.community.dto.QuestionDTO;
import cn.ct.community.mapper.UserMapper;
import cn.ct.community.model.User;
import cn.ct.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,Model model,@RequestParam(value = "page",defaultValue = "1") Integer page,
    @RequestParam(value = "size",defaultValue = "5") Integer size
    ) {

        //加载查询所有问题

        PaginationDTO<QuestionDTO> paginationDTO = questionService.findAllQuestion(page, size);

        model.addAttribute("paginationDTO",paginationDTO);

        return "index";
    }


    @GetMapping("/loginOut")
    public String loginOut(HttpServletRequest request, HttpServletResponse response){

        //移除session
        request.getSession().removeAttribute("user");
        //异常cookie
        Cookie newCookie=new Cookie("token",null);
        newCookie.setMaxAge(0);
        newCookie.setPath("/");
        response.addCookie(newCookie);

        return  "redirect:/";
    }
}
