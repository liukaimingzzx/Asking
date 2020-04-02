package com.lkm.asking.controller;

import com.lkm.asking.entity.Answer;
import com.lkm.asking.entity.Question;
import com.lkm.asking.service.AnswerService;
import com.lkm.asking.service.QuestionService;
import com.lkm.asking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("answer")
public class AnswerController {
    Map<String,Object> modelMap = new HashMap<>();
    @Autowired
    AnswerService answerService;
    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;

    @ResponseBody
    @RequestMapping(value = "/add")
    public Map<String,Object> addAnswer(@RequestBody Map<String,Object> data){
        modelMap.clear();
        Answer answer = new Answer();
        String username = (String) data.get(("username"));
        String userAvater = userService.queryAvater(username);
        String nickname = userService.queryNickname(username);
        answer.setAnswerContent((String) data.get("answerContent"));
        answer.setNickname(nickname);
        answer.setUserAvater(userAvater);
        answer.setUsername(username);
        answer.setQuestionId((Integer) data.get("questionId"));
        answer.setQuestionTitle(questionService.findTitle((Integer) data.get("questionId")));
        int flag = answerService.addAnswer(answer);
        List<Answer> list = answerService.listAnswer((Integer) data.get("questionId"));
        if(flag>0){
            questionService.addCommentNum((Integer) data.get("questionId"));
            modelMap.put("errno",0);
            modelMap.put("msg","创建回答成功！");
            modelMap.put("result",list);
        }else{
            modelMap.put("errno",-1);
            modelMap.put("msg","创建回答失败！");
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public Map<String,Object> answerList(@RequestBody Map<String,Object> data){
        modelMap.clear();
        List<Answer> list = answerService.listAnswer((Integer) data.get("questionId"));
        modelMap.put("errno",0);
        modelMap.put("result",list);
        return modelMap;

    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map<String,Object> deleteAnswer(@RequestBody Map<String,Object> data){
        modelMap.clear();
        Integer queId = answerService.findQuestionId((Integer) data.get("answerId"));
        int flag = answerService.deleteAnswer((Integer) data.get("answerId"));
        if(flag>0){
            List<Answer> list = answerService.listMyAnswer((String) data.get("username"));
            questionService.decCommentNum(queId);
            modelMap.put("msg","删除成功！");
            modelMap.put("errno",0);
            modelMap.put("result",list);
        }else{
            modelMap.put("msg","删除失败！");
            modelMap.put("errno",-1);
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/thumb")
    public Map<String,Object> thumb(@RequestBody Map<String,Object> data){
        modelMap.clear();
        int coin = userService.queryCoin((String) data.get("username1"));
        System.out.println(coin);
        if(coin-1<0){
            modelMap.put("errno",-1);
            modelMap.put("msg","硬币不足，无法点赞！");
        }
        else {
            answerService.thumb((Integer) data.get("answerId"));
            userService.addCoin((String) data.get("username2"));
            userService.decCoin((String) data.get("username1"));
            modelMap.put("errno",0);
            modelMap.put("msg","点赞成功，硬币-1！");
        }
        return modelMap;
    }


    @ResponseBody
    @RequestMapping(value ="/myanswer")
    public Map<String,Object> myAnswer(@RequestBody Map<String,Object> data){
        modelMap.clear();
        List<Answer> list = answerService.listMyAnswer((String) data.get("username"));
        modelMap.put("errno",0);
        modelMap.put("result",list);
        return modelMap;
    }
}
