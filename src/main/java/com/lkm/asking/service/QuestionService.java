package com.lkm.asking.service;

import com.lkm.asking.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> listAll();
    int addQuestion(Question question);
    int deleteQuestion(Integer questionId);
    Question queryQuestion(Integer questionId);
    List<Question> listByUsername(String username);
    List<Question> searchQuestion(String index);
    void addView(Integer questionId);
    void addCommentNum(Integer questionId);
    int countQuestion(String username);
    void decCommentNum(Integer questionId);
    String findTitle(Integer questionId);
}
