package com.lkm.asking.controller;

import com.lkm.asking.dao.QuestionBoxDao;
import com.lkm.asking.entity.QuestionBox;
import com.lkm.asking.service.QuestionBoxService;
import com.lkm.asking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("questionbox")
public class QuestionBoxController {
    Map<String, Object> modelMap = new HashMap<>();
    @Autowired
    QuestionBoxService questionBoxService;

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/add")
    public Map<String, Object> addQuestionBox(@RequestBody Map<String, Object> data) {
        int min = 100000;
        int max = 999999;
        int temp = 0;
        String password = new String();
        Random random = new Random();
        do {
            temp = random.nextInt(max) % (max - min + 1) + min;
        }
        while (questionBoxService.selectById(temp) == 1);
        String username = (String) data.get("username");
        QuestionBox questionBox = new QuestionBox();
        questionBox.setBoxId(temp);
        questionBox.setBoxContent((String) data.get("boxContent"));
        questionBox.setBoxPassword(password.valueOf(random.nextInt(max) % (max - min + 1) + min));
        questionBox.setBoxTitle((String) data.get("boxTitle"));
        questionBox.setUsername(username);
        questionBox.setNickname(userService.queryNickname(username));
        questionBox.setUserAvater(userService.queryAvater(username));
        int flag = questionBoxService.addQuestionBox(questionBox);
        if (flag > 0) {
            modelMap.put("errno", 0);
            modelMap.put("msg", "创建问题箱成功！");
        } else {
            modelMap.put("errno", -1);
            modelMap.put("msg", "创建问题失箱败！");
        }
        return modelMap;


    }

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> listBox(@RequestBody Map<String, Object> data){
        List<QuestionBox> list = questionBoxService.listMyBox((String) data.get("username"));
        modelMap.clear();
        modelMap.put("errno",0);
        modelMap.put("result",list);
        return modelMap;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map<String, Object> deleteBox(@RequestBody Map<String, Object> data){
        modelMap.clear();
        int flag = questionBoxService.deleteBox((Integer) data.get("boxId"));
        if(flag>0){
            List<QuestionBox> list = questionBoxService.listMyBox((String) data.get("username"));
            modelMap.put("errno",0);
            modelMap.put("msg","删除成功！");
            modelMap.put("result",list);
        }else{
            modelMap.put("errno",-1);
            modelMap.put("msg","删除失败！");
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping("/detail")
    public Map<String, Object> seeDetail(@RequestBody Map<String, Object> data){
        modelMap.clear();
        QuestionBox questionBox = questionBoxService.seeDetail((Integer) data.get("boxId"));
        modelMap.put("errno",0);
        modelMap.put("result",questionBox);
        return modelMap;

    }

    @ResponseBody
    @RequestMapping("/search")
    public Map<String, Object> searchBox(@RequestBody Map<String, Object> data){
        modelMap.clear();
        String index = (String) data.get("index");
        int id = Integer.valueOf(index);
        QuestionBox questionBox = questionBoxService.seeDetail(id);
        modelMap.put("errno",0);
        modelMap.put("result",questionBox);
        return modelMap;
    }
}
