package com.lkm.asking.service.Impl;

import com.lkm.asking.dao.BoxAnswerDao;
import com.lkm.asking.entity.BoxAnswer;
import com.lkm.asking.service.BoxAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoxAnswerServiceImpl implements BoxAnswerService {
    @Autowired
    BoxAnswerDao boxAnswerDao;
    @Override
    public int addBoxAnswer(BoxAnswer boxAnswer) {
        int flag = boxAnswerDao.addBoxAnswer(boxAnswer);
        if(flag>0){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public List<BoxAnswer> listBoxAnswer(Integer boxID) {
        return boxAnswerDao.listBoxAnswer(boxID);
    }
}
