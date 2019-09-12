package cn.ct.community.service;

import cn.ct.community.dto.NotificationDTO;
import cn.ct.community.dto.PaginationDTO;
import cn.ct.community.enums.NotificationStatusEnum;
import cn.ct.community.enums.NotificationTypeEnum;
import cn.ct.community.exception.CustomizeErroCode;
import cn.ct.community.exception.CustomizeException;
import cn.ct.community.mapper.CommentMapper;
import cn.ct.community.mapper.NotificationMapper;
import cn.ct.community.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private CommentMapper commentMapper;


    public PaginationDTO<NotificationDTO> findNotificationByReceiver(Integer id, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO=new PaginationDTO();
        NotificationExample notificationExample=new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(id.longValue());
        int totalCount=(int)notificationMapper.countByExample(notificationExample);
        int totalPage;

        if(totalCount%size!=0){
            totalPage=totalCount/size+1;
        }else{
            totalPage=totalCount/size;
        }

        if(page<=0){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        int offset=(page-1)*size;
        notificationExample.setOrderByClause("gtm_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(notificationExample,new RowBounds(offset,size));
        List<NotificationDTO> notificationDTOS=new ArrayList<NotificationDTO>();
        paginationDTO.setPagination(totalPage,page,size);

        //根据creator获取user信息
        for (Notification n :
                notifications) {
            NotificationDTO notificationDTO=new NotificationDTO();
            BeanUtils.copyProperties(n,notificationDTO);
            notificationDTO.setType(NotificationTypeEnum.getTypeName(n.getType()));
            notificationDTO.setStatus(NotificationStatusEnum.getStatusName(n.getStatus()));
            notificationDTOS.add(notificationDTO);

        }
        paginationDTO.setData(notificationDTOS);


        return paginationDTO;
    }
    @Transactional
    public Integer read(Integer userId, Integer id) {
        Notification notification = notificationMapper.selectByPrimaryKey(id.longValue());

        if (notification == null) {
            throw new CustomizeException(CustomizeErroCode.NOTIFICATION_NOT_FOUND);
        }
        if (notification.getReceiver() != userId.longValue()) {
            throw new CustomizeException(CustomizeErroCode.NOTIFICATION_NOT_YOU);
        }

        if (notification.getType() == NotificationTypeEnum.COMMENT_REPLAY.getType()) {
            Comment comment = commentMapper.selectByPrimaryKey(notification.getOuterId());
            if (comment == null) {
                throw new CustomizeException(CustomizeErroCode.COMMENT_NOT_FOUND);
            }
            //消息改为已读
            notification.setStatus(NotificationStatusEnum.READ.getStatus());
            notificationMapper.updateByPrimaryKeySelective(notification);
            return comment.getParentId().intValue();
        } else if (notification.getType() == NotificationTypeEnum.QUESTION_REPLAY.getType()) {
            //消息改为已读
            notification.setStatus(NotificationStatusEnum.READ.getStatus());
            notificationMapper.updateByPrimaryKeySelective(notification);
            return notification.getOuterId().intValue();

        }
        return null;


    }
}
