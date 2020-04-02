package com.lkm.asking.service.Impl;

import com.lkm.asking.dao.QuestionDao;
import com.lkm.asking.entity.Question;
import com.lkm.asking.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionDao questionDao;

    @Override
    public List<Question> listAll() {
        List<Question> list = questionDao.listAll();
        int num = list.size();
        if(num <= 10){
            return list;
        }else{
            List<Question> list1 = new LinkedList<>();
            for(int b=0;b<10;b++){
                Random random = new Random();
                list1.add(list.get(random.nextInt(num)));
            }
            return list1;
        }
    }

    @Override
    public int addQuestion(Question question) {
        int flag = questionDao.addQuestion(question);
        if(flag>0){
            return 1;
        }else{
            throw new RuntimeException("新增失败！");
        }
    }

    @Override
    public int deleteQuestion(Integer questionId) {
        int flag = questionDao.deleteQuestion(questionId);
        System.out.println(flag);
        if(flag>0){
            return 1;
        }else{
            throw new RuntimeException("删除失败！");
        }
    }

    @Override
    public Question queryQuestion(Integer questionId) {
        return questionDao.queryQuestion(questionId);
    }

    @Override
    public List<Question> listByUsername(String username) {
        return questionDao.listByUsername(username);
    }

    @Override
    public List<Question> searchQuestion(String index) {
        return questionDao.searchQuestion(index);
    }

    @Override
    public void addView(Integer questionId) {
        questionDao.addView(questionId);

    }

    @Override
    public void addCommentNum(Integer questionId) {
        questionDao.addCommentNum(questionId);
    }

    @Override
    public int countQuestion(String username) {
        return questionDao.countQuestion(username);
    }

    @Override
    public void decCommentNum(Integer questionId) {
        questionDao.decCommentNum(questionId);
    }

    @Override
    public String findTitle(Integer questionId) {
        return questionDao.findTitle(questionId);
    }
}
