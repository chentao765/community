package cn.ct.community.service;


import cn.ct.community.dto.PaginationDTO;
import cn.ct.community.dto.QuestionDTO;
import cn.ct.community.exception.CustomizeErroCode;
import cn.ct.community.exception.CustomizeException;
import cn.ct.community.mapper.QuestionExtMapper;
import cn.ct.community.mapper.QuestionMapper;
import cn.ct.community.mapper.UserMapper;
import cn.ct.community.model.Question;
import cn.ct.community.model.QuestionExample;
import cn.ct.community.model.User;
import cn.ct.community.model.UserExample;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionExtMapper questioneExtMapper;

    public PaginationDTO findAllQuestion(Integer page, Integer size) {
        PaginationDTO paginationDTO=new PaginationDTO();
        int totalCount=(int)questionMapper.countByExample(new QuestionExample());
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
        QuestionExample questionExample=new QuestionExample();
        questionExample.setOrderByClause("gtm_create desc");
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOS=new ArrayList<QuestionDTO>();
        paginationDTO.setPagination(totalPage,page,size);

        //根据creator获取user信息
        for (Question question :
                questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setData(questionDTOS);


        return paginationDTO;
    }


    public PaginationDTO findQuestionByUserId(int id, Integer page, Integer size) {
        PaginationDTO<QuestionDTO> paginationDTO=new PaginationDTO<QuestionDTO>();
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(id);
        questionExample.setOrderByClause("gtm_create desc");
        int totalCount=(int)questionMapper.countByExample(questionExample);
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
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOS=new ArrayList<QuestionDTO>();


        for (Question question :
                questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setData(questionDTOS);


        return paginationDTO;
    }

    public QuestionDTO findQuestionById(int id) {

        //根据creator获取user信息
            Question question=questionMapper.selectByPrimaryKey(id);
            if(question==null){
                throw new CustomizeException(CustomizeErroCode.QUESTION_NOT_FOUND);
            }
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            question.setViewCounts(question.getViewCounts()+1);
            questioneExtMapper.incrViewCount(question);
            return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            question.setGtmCreate(System.currentTimeMillis());
            question.setGtmUpdate(question.getGtmCreate());
            questionMapper.insert(question);
        }else{
            question.setGtmUpdate(System.currentTimeMillis());
             questionMapper.updateByPrimaryKeySelective(question);

        }
    }

    public List<QuestionDTO> findRelativeQuestion(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        String tag=question.getTag();
        if(StringUtils.isBlank(tag)){
            return new ArrayList<>();
        }

        String[] tags=tag.split(",");
        String regex=Arrays.stream(tags).collect(Collectors.joining("|"));
        question.setTag(regex);
        List<Question> questions=null;
        questions = questioneExtMapper.selectRelativeQuestion(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;

    }
}
