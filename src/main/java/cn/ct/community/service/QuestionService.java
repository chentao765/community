package cn.ct.community.service;

import cn.ct.community.dto.PaginationDTO;
import cn.ct.community.dto.QuestionDTO;
import cn.ct.community.mapper.QuestionMapper;
import cn.ct.community.mapper.UserMapper;
import cn.ct.community.model.Question;
import cn.ct.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO findAllQuestion(Integer page, Integer size) {
        PaginationDTO paginationDTO=new PaginationDTO();
        int totalCount=questionMapper.count();
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
        List<Question> questions = questionMapper.findAllQuestion(offset,size);
        List<QuestionDTO> questionDTOS=new ArrayList<QuestionDTO>();
        paginationDTO.setPagination(totalPage,page,size);
        //根据creator获取user信息
        for (Question question :
                questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);


        return paginationDTO;
    }


    public PaginationDTO findQuestionById(int id, Integer page, Integer size) {
        PaginationDTO paginationDTO=new PaginationDTO();
        int totalCount=questionMapper.countById(id);
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


        paginationDTO.setPagination(totalPage,page,size);
        int offset=(page-1)*size;
        List<Question> questions = questionMapper.findQuestionById(id,offset,size);
        List<QuestionDTO> questionDTOS=new ArrayList<QuestionDTO>();

        //根据creator获取user信息
        for (Question question :
                questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);


        return paginationDTO;
    }
}
