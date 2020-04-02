package com.lkm.asking.dao;

import com.lkm.asking.entity.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionDao {
    List<Question> listAll();
    int addQuestion(Question question);
    int deleteQuestion(Integer questionId);
    String findTitle(Integer questionId);
    Question queryQuestion(Integer questionId);
    List<Question> listByUsername(String username);
    List<Question> searchQuestion(String index);
    void addView(Integer questionId);
    void addCommentNum(Integer questionId);
    void decCommentNum(Integer questionId);
    int countQuestion(String username);


}
