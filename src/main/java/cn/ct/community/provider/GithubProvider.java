package cn.ct.community.provider;

import cn.ct.community.dto.AccessTokenDTO;
import cn.ct.community.dto.GithubUser;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        String accessToken=null;
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
        try {
            try (Response response = client.newCall(request).execute()) {
                String string=response.body().string();
                accessToken=string.split("&")[0].split("=")[1];
                //System.out.println(accessToken);

            }
        } catch (IOException e) {
           e.printStackTrace();
        }

        return accessToken;
    }

    public GithubUser getGithubUser(String accessToken){
        GithubUser githubUser=null;
        OkHttpClient client = new OkHttpClient();


            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+accessToken)
                    .build();

        try {
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                githubUser= JSONObject.parseObject(string, GithubUser.class);
            }
        } catch (IOException e) {

        }
        return  githubUser;
    }

    }

