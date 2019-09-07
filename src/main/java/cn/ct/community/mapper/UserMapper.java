package cn.ct.community.mapper;

import cn.ct.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into User (accoount_id,name,token,gtm_create,gtm_update)" +
            "values (#{accountId},#{name},#{token},#{gtmCreate},#{gtmUpdate})")
    public void addUser(User user);
}
