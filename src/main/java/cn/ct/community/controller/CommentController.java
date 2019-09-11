package cn.ct.community.controller;

import cn.ct.community.dto.CommentCreateDTO;
import cn.ct.community.dto.CommentDTO;
import cn.ct.community.dto.ResultDTO;
import cn.ct.community.enums.CommentTypeEnum;
import cn.ct.community.exception.CustomizeErroCode;
import cn.ct.community.model.Comment;
import cn.ct.community.model.User;
import cn.ct.community.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public  @ResponseBody ResultDTO post(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request){
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
        comment.setCommentCounts(0);
        commentService.addComment(comment);
        return ResultDTO.okOf();
    }

    @GetMapping("/comment/{id}")
    @ResponseBody
    public ResultDTO get(@PathVariable(value = "id") Integer id){
        List<CommentDTO> commentDTOS = commentService.findCommentById(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
