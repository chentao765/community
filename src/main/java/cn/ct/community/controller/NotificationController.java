package cn.ct.community.controller;

import cn.ct.community.enums.NotificationTypeEnum;
import cn.ct.community.model.Notification;
import cn.ct.community.model.User;
import cn.ct.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/notification/{id}")
    public String notification(HttpServletRequest request,@PathVariable("id") Integer id){
        User user=(User)request.getSession().getAttribute("user");
        if(user==null){
            return  "redirect:/";
        }
        Integer questionId = notificationService.read(user.getId(), id);
        return "redirect:/question/"+questionId;

    }
}
