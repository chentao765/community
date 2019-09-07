package cn.ct.community.controller;

import cn.ct.community.dto.AccessTokenDTO;
import cn.ct.community.dto.GithubUser;
import cn.ct.community.model.User;
import cn.ct.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private AccessTokenDTO accessTokenDTO;
    @GetMapping("/callback")
    public String callback(@RequestParam(value = "code")String code, @RequestParam(value = "state") String state, HttpServletRequest request){
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
            //放入session
            request.getSession().setAttribute("user",githubUser);
            User user=new User();
            user.setAccountId(githubUser.getId()+"");
            user.setName(githubUser.getName());
            user.setToken(accessToken);

            user.setGtmCreate(new Date().getTime()+"");
            user.setGtmUpdate(new Date().getTime()+"");
            return "redirect:/";
        }


    }
}
