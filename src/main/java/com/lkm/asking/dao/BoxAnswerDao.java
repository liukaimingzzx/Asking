package com.lkm.asking.dao;

import com.lkm.asking.entity.BoxAnswer;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface BoxAnswerDao {
    int addBoxAnswer(BoxAnswer boxAnswer);
    List<BoxAnswer> listBoxAnswer(Integer boxID);
}
