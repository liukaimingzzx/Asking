package com.lkm.asking.service.Impl;

import com.lkm.asking.dao.AnswerDao;
import com.lkm.asking.entity.Answer;
import com.lkm.asking.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService{
    @Autowired
    AnswerDao answerDao;

    @Override
    public int addAnswer(Answer answer) {
        int flag = answerDao.addAnswer(answer);
        if(flag>0){
            return 1;
        }else{
            throw new RuntimeException("新增失败！");
        }
    }

    @Override
    public List<Answer> listAnswer(Integer questionId) {
        return answerDao.listAnswer(questionId);
    }

    @Override
    public int deleteAnswer(Integer answerId) {
        int flag = answerDao.deleteAnswer(answerId);
        if(flag>0){
            return 1;
        }else{
            throw new RuntimeException("删除失败！");
        }
    }

    @Override
    public void thumb(Integer answerId) {
        answerDao.thumb(answerId);

    }

    @Override
    public List<Answer> listMyAnswer(String username) {
        return answerDao.listMyAnswer(username);
    }

    @Override
    public int countAnswer(String username) {
        return answerDao.countAnswer(username);
    }

    @Override
    public Integer findQuestionId(Integer answerId) {
        return answerDao.findQuestionId(answerId);
    }
}
