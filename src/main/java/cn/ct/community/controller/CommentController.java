package cn.ct.community.controller;

import cn.ct.community.dto.CommentCreateDTO;
import cn.ct.community.dto.ResultDTO;
import cn.ct.community.exception.CustomizeErroCode;
import cn.ct.community.model.Comment;
import cn.ct.community.model.User;
import cn.ct.community.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public  @ResponseBody ResultDTO post(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request){
        System.out.println(commentCreateDTO);
        User user=(User) request.getSession().getAttribute("user");
       if(user==null){
            System.out.println(ResultDTO.errorOf(CustomizeErroCode.USER_NOT_LOGIN));
            return ResultDTO.errorOf(CustomizeErroCode.USER_NOT_LOGIN);
        }
        Comment comment=new Comment();
        BeanUtils.copyProperties(commentCreateDTO,comment);
        comment.setLikeCounts(0);
        comment.setGtmCreate(System.currentTimeMillis());
        comment.setGtmUpdate(comment.getGtmCreate());
        comment.setCommentCreator(user.getId());
        commentService.addComment(comment);
        return ResultDTO.okOf();
    }
}
