package com.lkm.asking.service;

import com.lkm.asking.entity.BoxAnswer;

import java.util.List;

public interface BoxAnswerService {
    int addBoxAnswer(BoxAnswer boxAnswer);
    List<BoxAnswer> listBoxAnswer(Integer boxID);
}
