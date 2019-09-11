package cn.ct.community.dto;

import cn.ct.community.model.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentCreateDTO {
    private long parentId;
    private String content;
    private int type;

}
