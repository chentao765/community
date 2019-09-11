package cn.ct.community.controller;

import cn.ct.community.dto.CommentCreateDTO;
import cn.ct.community.dto.CommentDTO;
import cn.ct.community.dto.QuestionDTO;
import cn.ct.community.enums.CommentTypeEnum;
import cn.ct.community.model.Question;
import cn.ct.community.model.User;
import cn.ct.community.service.CommentService;
import cn.ct.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(value = "id")Integer id, Model model, HttpServletRequest request) {
        QuestionDTO questionDTO = questionService.findQuestionById(id);
        Object user = request.getSession().getAttribute("user");


        model.addAttribute("questionDTO",questionDTO);
        //返回问题评论
        List<CommentDTO> commentDTOS=commentService.findCommentById(id, CommentTypeEnum.QUESTION);
        model.addAttribute("commentDTOS",commentDTOS);
        //返回相关问题 ,根据id
        List<QuestionDTO> relativeQuestionDTOS=questionService.findRelativeQuestion(id);
        model.addAttribute("relativeQuestionDTOS",relativeQuestionDTOS);

        return "question";
    }
}
