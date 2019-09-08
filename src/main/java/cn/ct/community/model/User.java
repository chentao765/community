package cn.ct.community.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String accountId;
    private String name;
    private String token;
    private Long gtmCreate;
    private Long gtmUpdate;
    private String avatarUrl;


}
