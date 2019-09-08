package cn.ct.community.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
//@ConfigurationProperties(prefix = "github") 去找github.属性注入
//@PropertySource("classpath:application.properties") //获取属性文件 application.properties 可以不使用
@Data
public class AccessTokenDTO {
    @Value("${github.client_id}")
    private String client_id;
    @Value("${github.client_secret}")
    private String client_secret;
    private String code;
    @Value("${github.redirect_uri}")
    private String redirect_uri;
    private String state;


}
