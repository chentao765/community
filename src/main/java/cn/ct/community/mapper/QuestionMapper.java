package cn.ct.community.mapper;

import cn.ct.community.dto.QuestionDTO;
import cn.ct.community.model.Question;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Mapper
public interface QuestionMapper {

    @Insert("Insert into question (title,descrption,tag,creator,like_counts,comment_counts,view_counts,gtm_create,gtm_update)"+
    "values (#{title},#{descrption},#{tag},#{creator},#{likeCounts},#{commentCounts},#{viewCounts},#{gtmCreate},#{gtmUpdate})" )
    void addQuestion(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> findAllQuestion(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);


    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question  where creator =#{id} limit #{offset},#{size}")
    List<Question> findQuestionById(@Param(value = "id") int id, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question where creator = #{id}")
    Integer countById(@Param(value = "id") Integer id);
}


