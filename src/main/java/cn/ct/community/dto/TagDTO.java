package cn.ct.community.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagDTO {
    private String category;
    private List<String> tagName;
}
