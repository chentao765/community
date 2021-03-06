package cn.ct.community.service;

import cn.ct.community.dto.CommentDTO;
import cn.ct.community.enums.CommentTypeEnum;
import cn.ct.community.enums.NotificationStatusEnum;
import cn.ct.community.enums.NotificationTypeEnum;
import cn.ct.community.exception.CustomizeErroCode;
import cn.ct.community.exception.CustomizeException;
import cn.ct.community.mapper.*;
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
    @Autowired
    private CommentExMapper commentExMapper;
    @Autowired
    private NotificationMapper notificationMapper;
    @Transactional
    public void addComment(Comment comment){
        if(comment.getParentId()==null||comment.getParentId()==0){
            throw  new CustomizeException(CustomizeErroCode.TARGET_NOT_FOUND);
        }

        if(comment.getType()==null||!CommentTypeEnum.isExist(comment.getType())){
           throw new CustomizeException(CustomizeErroCode.TYPE_NOT_CORRECT);
        };

        if(comment.getType().equals(CommentTypeEnum.COMMENT.getType())){
            //回复评论
            Comment parentComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(parentComment==null){
                throw new CustomizeException(CustomizeErroCode.COMMENT_NOT_FOUND);
            }else{
                Comment pComment=new Comment();
                pComment.setId(comment.getParentId());
                pComment.setCommentCounts(1);
                commentMapper.insert(comment);
                commentExMapper.incrCommentCount(pComment);
                //创建通知
                CreatNotification(comment, parentComment.getCommentCreator(),parentComment.getParentId().intValue(),NotificationTypeEnum.COMMENT_REPLAY);

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
               //创建通知
               CreatNotification(comment,question.getCreator(),question.getId(),NotificationTypeEnum.QUESTION_REPLAY);

        }

    }

    //创建通知
    public void CreatNotification(Comment comment, long receiver,Integer questionId,NotificationTypeEnum type) {
        Notification notification=new Notification();
        notification.setGtmCreate(System.currentTimeMillis());
        notification.setType(type.getType());
        notification.setNotifier(comment.getCommentCreator().longValue());
        notification.setOuterId(comment.getParentId());
        notification.setReceiver(receiver);
        notification.setStatus(1);
        User Notifier = userMapper.selectByPrimaryKey(comment.getCommentCreator());
        notification.setNotifierName(Notifier.getName());
        Question question = questionMapper.selectByPrimaryKey(questionId);
        notification.setOuterTitle(question.getTitle());
        notificationMapper.insert(notification);
    }

    public List<CommentDTO> findCommentById(Integer id,CommentTypeEnum type) {
        if(id==null||id==0){
            throw new CustomizeException(CustomizeErroCode.QUESTION_NOT_FOUND);
        }else{
            List<CommentDTO> commentDTOS=new ArrayList<>();
            CommentExample commentExample=new CommentExample();
            commentExample.createCriteria().andParentIdEqualTo(id.longValue());
            commentExample.createCriteria().andTypeEqualTo(type.getType());
            commentExample.setOrderByClause("gtm_create desc");
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
