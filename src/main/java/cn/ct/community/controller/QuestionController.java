package cn.ct.community.controller;

import cn.ct.community.dto.QuestionDTO;
import cn.ct.community.mapper.QuestionMapper;
import cn.ct.community.model.Question;
import cn.ct.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(value = "id")Integer id, Model model){
        QuestionDTO questionDTO = questionService.findQuestionById(id);
        model.addAttribute("questionDTO",questionDTO);
        return "question";
    }
}
