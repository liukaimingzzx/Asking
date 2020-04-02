package com.lkm.asking.dao;

import com.lkm.asking.entity.Answer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AnswerDao {
    int addAnswer(Answer answer);
    List<Answer> listAnswer(Integer questionId);
    int deleteAnswer(Integer answerId);
    void thumb(Integer answerId);
    Integer findQuestionId(Integer answerId);
    List<Answer> listMyAnswer(String username);
    int countAnswer(String username);


}
