package cn.ct.community.dto;

import cn.ct.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Integer commentCreator;
    private Long gtmCreate;
    private Long gtmUpdate;
    private Integer likeCounts;
    private String content;
    private Integer commentCounts;
    private User user;
}
