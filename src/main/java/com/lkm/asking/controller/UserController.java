package com.lkm.asking.controller;

import com.lkm.asking.entity.Answer;
import com.lkm.asking.entity.User;
import com.lkm.asking.service.AnswerService;
import com.lkm.asking.service.QuestionService;
import com.lkm.asking.service.UserService;
import com.lkm.asking.util.AvaterUtil;
import com.lkm.asking.util.DefaultAvater;
import com.lkm.asking.util.MD5Util;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Value("${IP}")
    String ip;

    Map<String,Object> modelMap = new HashMap<>();
    AvaterUtil avaterUtil = new AvaterUtil();
    DefaultAvater defaultAvater = new DefaultAvater();

    @Autowired
    UserService userService;

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;



    @ResponseBody
    @RequestMapping(value = "/register")
    public Map<String,Object>  insertUser(@RequestBody Map<String,Object> data){
        modelMap.clear();
        String username = (String) data.get("username");
        User user = new User();
        user.setNickname((String) data.get("nickname"));
        user.setUsername(username);
        user.setPassword(MD5Util.MD5((String) data.get("password")));
        File file = new File("/personal/files/asking/avaters/default.jpg");
        defaultAvater.avaterUpload(file,username);
        String url = ip + "/user/avaters/" + username + "/" + username + ".jpg";
        user.setAvater(url);
        User judge = userService.queryByUsername(user.getUsername());
        if(judge == null){
            int flag = userService.insertUser(user);
            modelMap.put("errno", 0);
            modelMap.put("msg", "注册成功！");
        }else{
            modelMap.put("errno", 1);
            modelMap.put("msg", "用户名已被占用！");
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/updateavater")
    public Map<String,Object> updateAvater(@RequestParam("avater") MultipartFile file,
                                           @RequestParam("username") String username){
        modelMap.clear();
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if(suffix.equals(".jpg")||suffix.equals(".JPG")) {
            avaterUtil.avaterUpload(file, username);
            String webUrl = ip + "/user/avaters/" + username + "/" + username + suffix.toLowerCase();
            int flag = userService.updateAvater(username, webUrl);
            if (flag > 0) {
                modelMap.put("errno", 0);
                modelMap.put("url", webUrl);
            } else {
                modelMap.put("errno", -1);
                modelMap.put("msg", "头像更新失败！");
            }
        }else{
            modelMap.put("errno",1);
            modelMap.put("msg","请上传以.jpg结尾的图片文件");
        }
        return modelMap;

    }

    @ResponseBody
    @RequestMapping(value = "/login")
    public Map<String,Object> signin(@RequestBody Map<String,Object> data){
        modelMap.clear();
        String username = (String) data.get("username");
        String password = MD5Util.MD5((String) data.get("password"));
        Integer queCount = questionService.countQuestion(username);
        Integer ansCount = answerService.countAnswer(username);
        userService.updateInfo(queCount,ansCount,username);
        User con = userService.userLogin(username,password);
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
    @RequestMapping(value = "/update")
    public Map<String,Object> update(@RequestBody Map<String,Object> data){
        modelMap.clear();
        User user = new User();
        String pass = (String) data.get("password");
        user.setNickname((String) data.get("nickname"));
        user.setGender((Integer) data.get("gender"));
        user.setProfile((String) data.get("profile"));
        if(pass.equals(userService.queryPassword((String) data.get("username")))){
            user.setPassword(pass);
        }else{
            user.setPassword(MD5Util.MD5(pass));
        }
        user.setUsername((String) data.get("username"));

        User flag = userService.updateUser(user);
        if(flag!=null){
            modelMap.put("errno",0);
            modelMap.put("msg","更新成功！");
        }else{
            modelMap.put("errno", -1);
            modelMap.put("msg","更新失败！");
        }
        return modelMap;

    }

    @ResponseBody
    @RequestMapping(value = "/checkin")
    public Map<String,Object> checkin(@RequestBody Map<String,Object> data) throws ParseException {
        modelMap.clear();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        int flag = 0;
        String lastTime = userService.queryTime((String) data.get("username"));
        if(lastTime==null){
            flag = 0;
        }else{
            Date last = simpleDateFormat.parse(lastTime);
            long now1 = now.getTime();
            long last1 = last.getTime();
            int days = (int) ((last1 - now1) / (1000 * 60 * 60 * 24));
            if(days!=0){
                flag = 0;
            }else{
                flag = 1;
            }
        }
        if(flag==0){
            userService.checkin((String) data.get("username"),simpleDateFormat.format(now));
            modelMap.put("errno",0);
            modelMap.put("msg","签到成功！硬币+2");
        }else{
            modelMap.put("errno",-1);
            modelMap.put("msg","您今天已签到过，请明天再来！");
        }
        return modelMap;

    }

    @ResponseBody
    @RequestMapping(value = "/seeinfo")
    public Map<String, Object> seeinfo(@RequestBody Map<String,Object> data){
        modelMap.clear();
        String username = (String) data.get("username");
        Integer queCount = questionService.countQuestion(username);
        Integer ansCount = answerService.countAnswer(username);
        userService.updateInfo(queCount,ansCount,username);
        User user = userService.queryByUsername(username);
        if(user != null){
            modelMap.put("errno",0);
            modelMap.put("result",user);
        }else{
            modelMap.put("errno",-1);
            modelMap.put("msg","查看失败！");
        }
        return modelMap;
    }





}
