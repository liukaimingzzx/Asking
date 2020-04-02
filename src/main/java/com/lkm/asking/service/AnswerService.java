package com.lkm.asking.service;

import com.lkm.asking.entity.Answer;

import java.util.List;

public interface AnswerService {
    int addAnswer(Answer answer);
    List<Answer> listAnswer(Integer questionId);
    int deleteAnswer(Integer answerId);
    void thumb(Integer answerId);
    List<Answer> listMyAnswer(String username);
    int countAnswer(String username);
    Integer findQuestionId(Integer answerId);
}
