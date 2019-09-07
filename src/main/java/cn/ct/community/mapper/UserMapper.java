package cn.ct.community.mapper;

import cn.ct.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    @Insert("insert into User (account_id,name,token,gtm_create,gtm_update)" +
            "values (#{accountId},#{name},#{token},#{gtmCreate},#{gtmUpdate})")
    public void addUser(User user);

    @Select("select * from user where token=#{token}")
    public User findByToken(@Param("token") String token);
}
