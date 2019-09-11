package cn.ct.community.mapper;

import cn.ct.community.model.Question;


import java.util.List;

public interface QuestionExtMapper {
    void incrViewCount(Question question);

    void incrCommentCount(Question question);

    List<Question> selectRelativeQuestion(Question question);
}

