package cn.ct.community.mapper;

import cn.ct.community.model.Comment;
import cn.ct.community.model.CommentExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExMapper {
    void incrCommentCount(Comment comment);
}