package cn.ct.community.controller;

import cn.ct.community.dto.PaginationDTO;
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
        User user=null;

        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length>0) {
            for (Cookie cookie : cookies
            ) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    break;
                }

            }
        }
        //放入session
        request.getSession().setAttribute("user",user);

        //加载查询所有问题

        PaginationDTO paginationDTO = questionService.findAllQuestion(page, size);

        model.addAttribute("paginationDTO",paginationDTO);

        return "index";
    }
}
