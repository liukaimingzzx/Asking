package com.lkm.asking.controller;

import com.lkm.asking.entity.BoxAnswer;
import com.lkm.asking.service.BoxAnswerService;
import com.lkm.asking.service.QuestionBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("boxanswer")
public class BoxAnswerController {
    Map<String, Object> modelMap = new HashMap<>();
    @Autowired
    BoxAnswerService boxAnswerService;

    @Autowired
    QuestionBoxService questionBoxService;


    @ResponseBody
    @RequestMapping(value = "/add")
    public Map<String, Object> addBoxAnswer(@RequestBody Map<String, Object> data) {
        BoxAnswer boxAnswer = new BoxAnswer();
        boxAnswer.setBoxAcontent((String) data.get("boxAcontent"));
        boxAnswer.setBoxId((Integer) data.get("boxId"));
        boxAnswer.setUsername((String) data.get("username"));
        int flag = boxAnswerService.addBoxAnswer(boxAnswer);
        if (flag > 0) {
            modelMap.put("errno", 0);
            modelMap.put("msg", "创建回答成功！");
            questionBoxService.addCount((Integer) data.get("boxId"));
        } else {
            modelMap.put("errno", -1);
            modelMap.put("msg", "创建回答失败！");
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public Map<String, Object> boxAnswerList(@RequestBody Map<String, Object> data) {
        modelMap.clear();
        List<BoxAnswer> list = boxAnswerService.listBoxAnswer((Integer) data.get("boxId"));
        modelMap.put("errno", 0);
        modelMap.put("result", list);
        return modelMap;
    }
}
