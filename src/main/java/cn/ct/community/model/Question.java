package cn.ct.community.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String descrption;
    private String tag;
    private Integer viewCounts;
    private Integer commentCounts;
    private Integer likeCounts;
    private String creator;
    private Long gtmCreate;
    private Long gtmUpdate;

}
