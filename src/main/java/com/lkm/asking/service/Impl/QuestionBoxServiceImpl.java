package com.lkm.asking.service.Impl;

import com.lkm.asking.dao.QuestionBoxDao;
import com.lkm.asking.entity.QuestionBox;
import com.lkm.asking.service.QuestionBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionBoxServiceImpl implements QuestionBoxService {

    @Autowired
    QuestionBoxDao questionBoxDao;
    @Override
    public int addQuestionBox(QuestionBox questionBox) {
        int flag = questionBoxDao.addQuestionBox(questionBox);
        if(flag>0){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public int selectById(Integer boxId) {
        return questionBoxDao.selectById(boxId);
    }

    @Override
    public QuestionBox seeDetail(Integer boxId) {
        return questionBoxDao.seeDetail(boxId);
    }

    @Override
    public List<QuestionBox> listMyBox(String username) {
        return questionBoxDao.listMyBox(username);
    }

    @Override
    public int deleteBox(Integer boxId) {
        int flag = questionBoxDao.deleteBox(boxId);
        if(flag>0){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public void addCount(Integer boxId) {
        questionBoxDao.addCount(boxId);
    }



}
