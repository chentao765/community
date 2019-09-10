package cn.ct.community.service;

import cn.ct.community.dto.CommentDTO;
import cn.ct.community.enums.CommentTypeEnum;
import cn.ct.community.exception.CustomizeErroCode;
import cn.ct.community.exception.CustomizeException;
import cn.ct.community.mapper.CommentMapper;
import cn.ct.community.mapper.QuestionExtMapper;
import cn.ct.community.mapper.QuestionMapper;
import cn.ct.community.mapper.UserMapper;
import cn.ct.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Transactional
    public void addComment(Comment comment){
        if(comment.getParentId()==null||comment.getParentId()==0){
            throw  new CustomizeException(CustomizeErroCode.TARGET_NOT_FOUND);
        }

        if(comment.getType()==null||!CommentTypeEnum.isExist(comment.getType())){
           throw new CustomizeException(CustomizeErroCode.TYPE_NOT_CORRECT);
        };

        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment parentComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(parentComment==null){
                throw new CustomizeException(CustomizeErroCode.COMMENT_NOT_FOUND);
            }else{
                commentMapper.insert(comment);
            }

        }else{
            //回复问题
            Question question=questionMapper.selectByPrimaryKey(comment.getParentId().intValue());
            if(question==null){
                throw new CustomizeException(CustomizeErroCode.QUESTION_NOT_FOUND);
            }
               commentMapper.insert(comment);
               question.setCommentCounts(1);
               questionExtMapper.incrCommentCount(question);
        }

    }

    public List<CommentDTO> findQuestionCommentById(Integer id) {
        if(id==null||id==0){
            throw new CustomizeException(CustomizeErroCode.QUESTION_NOT_FOUND);
        }else{
            List<CommentDTO> commentDTOS=new ArrayList<>();
            CommentExample commentExample=new CommentExample();
            commentExample.createCriteria().andParentIdEqualTo(id.longValue());
            commentExample.createCriteria().andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
            List<Comment> comments = commentMapper.selectByExample(commentExample);
            if(comments.size()==0){
                return  commentDTOS;
            }
            //获取去重的评论人
            Set<Integer> creators = comments.stream().map(comment -> comment.getCommentCreator()).collect(Collectors.toSet());
            List<Integer> userIds=new ArrayList<>();
            userIds.addAll(creators);

            UserExample userExample=new UserExample();
            userExample.createCriteria().andIdIn(userIds);
            List<User> users = userMapper.selectByExample(userExample);
            Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

            commentDTOS = comments.stream().map(comment -> {
                CommentDTO commentDTO = new CommentDTO();
                BeanUtils.copyProperties(comment, commentDTO);
                commentDTO.setUser(userMap.get(comment.getCommentCreator()));
                return commentDTO;
            }).collect(Collectors.toList());


            return  commentDTOS;
        }
    }
}
