package cn.ct.community.controller;

import cn.ct.community.dto.NotificationDTO;
import cn.ct.community.dto.PaginationDTO;
import cn.ct.community.dto.QuestionDTO;
import cn.ct.community.mapper.NotificationMapper;
import cn.ct.community.mapper.UserMapper;
import cn.ct.community.model.Question;
import cn.ct.community.model.User;
import cn.ct.community.service.NotificationService;
import cn.ct.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(Model model, @PathVariable(value = "action") String action, HttpServletRequest request,
                          @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "5") Integer size) {



        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {

            return "redirect:/";
        }
        PaginationDTO paginationDTO=null;
        if ("questions".equals(action)) {
            paginationDTO= questionService.findQuestionByUserId(user.getId(), page, size);
            model.addAttribute("section", action);
            model.addAttribute("sectionName", "我的问题");
        } else if ("replays".equals(action)) {
            paginationDTO = notificationService.findNotificationByReceiver(user.getId(), page, size);
            model.addAttribute("section", action);
            model.addAttribute("sectionName", "我的回复");
        }
        model.addAttribute("paginationDTO" , paginationDTO);
        return "profile";



    }
}
