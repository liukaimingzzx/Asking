package com.lkm.asking.dao;

import com.lkm.asking.entity.QuestionBox;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionBoxDao {
    int addQuestionBox(QuestionBox questionBox);
    int selectById(Integer boxId);
    QuestionBox seeDetail(Integer boxId);
    List<QuestionBox> listMyBox(String username);
    int deleteBox(Integer boxId);
    void addCount(Integer boxId);



}
