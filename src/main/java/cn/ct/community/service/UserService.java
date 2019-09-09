package cn.ct.community.service;

import cn.ct.community.mapper.UserMapper;
import cn.ct.community.model.User;
import cn.ct.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    public void createOrUpdateUser(User user) {
        //根据accountId查找是否有用户，没有创建，有则跟新token
        UserExample userExample=new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users =userMapper.selectByExample(userExample);
        if(users!=null&&users.size()>0){
            User u=users.get(0);
            u.setAvatarUrl(user.getAvatarUrl());
            u.setName(user.getName());
            u.setToken(user.getToken());
            u.setGtmUpdate(System.currentTimeMillis());
            userMapper.updateByPrimaryKeySelective(u);
        }else{
            userMapper.insert(user);
        }
    }
}
