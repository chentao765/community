package cn.ct.community.mapper;

import cn.ct.community.model.Question;

public interface QuestionExtMapper {
    void incrViewCount(Question question);
    void incrCommentCount(Question question);
}

