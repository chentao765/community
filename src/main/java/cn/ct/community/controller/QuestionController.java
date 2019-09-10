package cn.ct.community.controller;

import cn.ct.community.dto.CommentCreateDTO;
import cn.ct.community.dto.CommentDTO;
import cn.ct.community.dto.QuestionDTO;
import cn.ct.community.service.CommentService;
import cn.ct.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(value = "id")Integer id, Model model){
        QuestionDTO questionDTO = questionService.findQuestionById(id);
        model.addAttribute("questionDTO",questionDTO);
        //返回问题评论
        List<CommentDTO> commentDTOS=commentService.findQuestionCommentById(id);
        model.addAttribute("commentDTOS",commentDTOS);

        return "question";
    }
}
