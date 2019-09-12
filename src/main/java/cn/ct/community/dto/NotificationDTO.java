package cn.ct.community.dto;

import cn.ct.community.model.User;
import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private Long outerId;
    private Long notifier;
    private String type;
    private Long gtmCreate;
    private String status;
    private User user;
    private String notifierName;
    private String outerTitle;
}
