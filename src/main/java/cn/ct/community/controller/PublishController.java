package cn.ct.community.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import cn.ct.community.mapper.QuestionMapper;
import cn.ct.community.mapper.UserMapper;
import cn.ct.community.model.Question;
import cn.ct.community.model.User;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;

@Controller
public class PublishController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title, @RequestParam("descrption") String descrption,
                            @RequestParam("tag") String tag, Model model, HttpServletRequest request
                            ) {

        //后台校验不能为空
        model.addAttribute("title",title);
        model.addAttribute("descrption",descrption);
        model.addAttribute("tag",tag);


        if(title.equals("")&title==""){
            model.addAttribute("errorMsg","标题不能为空");
            return "publish";
        }
        if(descrption.equals("")&descrption==""){
            model.addAttribute("errorMsg","问题描述不能为空");
            return "publish";
        }

        if(tag.equals("")&tag==""){
            model.addAttribute("errorMsg","标签不能为空");
            return "publish";
        }

        User user=null;
        //获取用户
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies
        ) {
            if(cookie.getName().equals("token")){
                String token=cookie.getValue();
                user=userMapper.findByToken(token);
            }
        }
        if(user==null){
            model.addAttribute("errorMsg","用户未登录");
            return "publish";
        }
        Question question=new Question();
        question.setTitle(title);
        question.setCommentCounts(0);
        question.setLikeCounts(0);
        question.setViewCounts(0);
        question.setCreator(user.getName());
        question.setDescrption(descrption);
        question.setTag(tag);
        System.out.println(System.currentTimeMillis());
        question.setGtmCreate(System.currentTimeMillis());
        question.setGtmUpdate(question.getGtmUpdate());
        questionMapper.addQuestion(question);
        //成功之后



        return "redirect:/";
    }
}
