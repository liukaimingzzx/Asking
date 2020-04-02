package com.lkm.asking.service;

import com.lkm.asking.entity.QuestionBox;

import java.util.List;

public interface QuestionBoxService {
    int addQuestionBox(QuestionBox questionBox);
    int selectById(Integer boxId);
    QuestionBox seeDetail(Integer boxId);
    List<QuestionBox> listMyBox(String username);
    int deleteBox(Integer boxId);
    void addCount(Integer boxId);



}
