package cn.ct.community.controller;

import cn.ct.community.dto.AccessTokenDTO;
import cn.ct.community.dto.GithubUser;
import cn.ct.community.mapper.UserMapper;
import cn.ct.community.model.User;
import cn.ct.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private AccessTokenDTO accessTokenDTO;

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/callback")
    public String callback(@RequestParam(value = "code")String code, @RequestParam(value = "state") String state,
                           HttpServletRequest request, HttpServletResponse response){
        //发送post请求获取access token，这里采用okhttp发送post请求

        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        //System.out.println(accessTokenDTO);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);

        //获得accessToken之后通过get请求获取user信息
        GithubUser githubUser = githubProvider.getGithubUser(accessToken);
        if(githubUser==null){
            return "redirect:/";
        }else{

            User user=new User();
            user.setAccountId(githubUser.getId()+"");
            user.setName(githubUser.getName());
            String token=UUID.randomUUID()+"";
            user.setToken(token);
            user.setGtmCreate(System.currentTimeMillis());
            user.setGtmUpdate(user.getGtmCreate());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userMapper.addUser(user);
            //添加到cookie/
            Cookie cookie=new Cookie("token",token);
            response.addCookie(cookie);
            return "redirect:/";
        }


    }
}
