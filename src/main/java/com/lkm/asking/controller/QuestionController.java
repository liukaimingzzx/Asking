package com.lkm.asking.controller;

import com.lkm.asking.entity.Question;
import com.lkm.asking.service.QuestionService;
import com.lkm.asking.service.UserService;
import com.lkm.asking.util.ImgUtil;
import com.sun.java.swing.plaf.windows.WindowsDesktopIconUI;
import javafx.scene.chart.ValueAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("question")
public class QuestionController  {
    @Value("${IP}")
    String ip;
    ImgUtil imgUtil = new ImgUtil();
    Map<String,Object> modelMap = new HashMap<>();
    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/add")
    public Map<String,Object> addQuestion(@RequestParam("username") String username,
                                          @RequestParam("questionContent") String questionContent,
                                          @RequestParam("questionTitle") String questionTitle,
                                          @RequestParam("tag") String tag,
                                          @RequestParam("img")MultipartFile img) {
        modelMap.clear();
        Question question = new Question();
        String userAvater = userService.queryAvater(username);
        String nickname = userService.queryNickname(username);
        String fileName = img.getOriginalFilename();
        String suffix = fileName.substring((fileName.lastIndexOf(".")));
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String time = df.format(date);
        imgUtil.imgUpload(img,time);
        String imgUrl = ip + "/question/img/" + time +"/"+time + suffix;


        question.setNickname(nickname);
        question.setQuestionContent(questionContent);
        question.setQuestionTitle(questionTitle);
        question.setTag(tag);
        question.setUsername(username);
        question.setUserAvater(userAvater);
        question.setImg(imgUrl);

        int flag = questionService.addQuestion(question);
        if(flag>0){
            modelMap.put("errno",0);
            modelMap.put("msg","创建问题成功！");
        }else{
            modelMap.put("errno",-1);
            modelMap.put("msg","创建问题失败！");
        }
        return modelMap;

    }

    @ResponseBody
    @GetMapping(value = "/list")
    public Map<String,Object> listQuestion(){
        List<Question> questions = questionService.listAll();
        modelMap.clear();
        modelMap.put("msg","0");
        modelMap.put("result",questions);
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map<String,Object> deleteQuestion(@RequestBody Map<String,Object> data){
        modelMap.clear();
        int flag = questionService.deleteQuestion((Integer) data.get("questionId"));
        if(flag>0){
            List<Question> list = questionService.listByUsername((String) data.get("username"));
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
    @RequestMapping(value = "/detail")
    public Map<String,Object> detail (@RequestBody Map<String,Object> data) {
        modelMap.clear();
        questionService.addView((Integer) data.get("questionId"));
        Question question = questionService.queryQuestion((Integer) data.get("questionId"));
        modelMap.put("errno",0);
        modelMap.put("result",question);
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/search")
    public Map<String,Object> search (@RequestBody Map<String,Object> data){
        modelMap.clear();
        String index = (String) data.get("index");
        List<Question> list = questionService.searchQuestion(index);
        if(list.size()==0){
            modelMap.put("errno",-1);
            modelMap.put("msg","无相关内容！");
        }else{
            modelMap.put("errno",0);
            modelMap.put("result",list);
        }
        return modelMap;

    }

    @ResponseBody
    @RequestMapping(value = "/myquestion")
    public Map<String,Object> myQuestion(@RequestBody Map<String,Object> data){
        modelMap.clear();
        List<Question> list = questionService.listByUsername((String) data.get("username"));
        modelMap.put("errno",0);
        modelMap.put("result",list);
        return modelMap;
    }


}
