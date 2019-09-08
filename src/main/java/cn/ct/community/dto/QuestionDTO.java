package cn.ct.community.dto;

import cn.ct.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String descrption;
    private String tag;
    private Integer viewCounts;
    private Integer commentCounts;
    private Integer likeCounts;
    private Integer creator;
    private Long gtmCreate;
    private Long gtmUpdate;
    private User user;

}
