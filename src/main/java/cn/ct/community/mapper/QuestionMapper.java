package cn.ct.community.mapper;

import cn.ct.community.model.Question;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface QuestionMapper {
    @Insert("Insert into question (title,descrption,tag,creator,like_counts,comment_counts,view_counts,gtm_create,gtm_update)"+
    "values (#{title},#{descrption},#{tag},#{creator},#{likeCounts},#{commentCounts},#{viewCounts},#{gtmCreate},#{gtmUpdate})" )
    void addQuestion(Question question);
}
