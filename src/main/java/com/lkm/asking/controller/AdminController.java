package com.lkm.asking.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lkm.asking.entity.Admin;
import com.lkm.asking.entity.Answer;
import com.lkm.asking.entity.Question;
import com.lkm.asking.entity.User;
import com.lkm.asking.service.AdminService;
import com.lkm.asking.service.AnswerService;
import com.lkm.asking.service.QuestionService;
import com.lkm.asking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    Map<String,Object> modelMap = new HashMap<>();

    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;

    @ResponseBody
    @RequestMapping(value = "/login")
    public Map<String,Object> signin(@RequestBody Map<String,Object> data){
        modelMap.clear();
        String adminName = (String) data.get("adminName");
        String adminPassword = (String) data.get("adminPassword");

        Admin con = adminService.adminLogin(adminName,adminPassword);
        if(con != null){
            modelMap.put("errno",0);
            modelMap.put("result",con);
        }else{
            modelMap.put("errno",-1);
            modelMap.put("msg","用户名或密码错误");
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/listuser")
    public Map<String,Object> listUser(@RequestBody Map<String,Integer> data){
        int pageIndex = data.get("pageIndex");
        int pageSize = data.get("pageSize");
        modelMap.clear();
        PageHelper.startPage(pageIndex,pageSize);
        List<User> users = userService.listAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        modelMap.put("pageTotal",pageInfo.getTotal());
        modelMap.put("list",pageInfo.getList());
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteuser")
    public Map<String,Object> deleteUser(@RequestBody Map<String,Object> data){
        modelMap.clear();
        int flag = userService.deleteUser((String) data.get("username"));
        if(flag>0){
            modelMap.put("msg","删除成功！");
            modelMap.put("errno",0);
        }else{
            modelMap.put("msg","删除失败！");
            modelMap.put("errno",-1);
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/listquestion")
    public Map<String,Object> listQuestion(@RequestBody Map<String,Integer> data){
        int pageIndex = data.get("pageIndex");
        int pageSize = data.get("pageSize");
        modelMap.clear();
        PageHelper.startPage(pageIndex,pageSize);
        List<Question> questions = questionService.listAll();
        PageInfo<Question> pageInfo = new PageInfo<>(questions);
        modelMap.put("pageTotal",pageInfo.getTotal());
        modelMap.put("list",pageInfo.getList());
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/deletequestion")
    public Map<String,Object> deleteQuestion(@RequestBody Map<String,Object> data){
        modelMap.clear();
        int flag = questionService.deleteQuestion((Integer) data.get("questionId"));
        if(flag>0){
            modelMap.put("msg","删除成功！");
            modelMap.put("errno",0);
        }else{
            modelMap.put("msg","删除失败！");
            modelMap.put("errno",-1);
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/listanswer")
    public Map<String,Object> listAnswer(@RequestBody Map<String,Object> data){
        int pageIndex = (int) data.get("pageIndex");
        int pageSize = (int) data.get("pageSize");
        modelMap.clear();
        PageHelper.startPage(pageIndex,pageSize);
        List<Answer> list = answerService.listAnswer((Integer) data.get("questionId"));
        PageInfo<Answer> pageInfo = new PageInfo<>(list);
        modelMap.put("pageTotal",pageInfo.getTotal());
        modelMap.put("list",pageInfo.getList());
        return modelMap;

    }

    @ResponseBody
    @RequestMapping(value = "/deleteanswer")
    public Map<String,Object> deleteAnswer(@RequestBody Map<String,Object> data){
        modelMap.clear();
        int flag = answerService.deleteAnswer((Integer) data.get("answerId"));
        if(flag>0){
            modelMap.put("msg","删除成功！");
            modelMap.put("errno",0);
        }else{
            modelMap.put("msg","删除失败！");
            modelMap.put("errno",-1);
        }
        return modelMap;
    }


}
