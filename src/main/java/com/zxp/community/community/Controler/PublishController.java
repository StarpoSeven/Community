package com.zxp.community.community.Controler;

import com.zxp.community.community.mapper.QuestionMapper;
import com.zxp.community.community.mapper.UserMapper;
import com.zxp.community.community.model.Question;
import com.zxp.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    private String Publish() {

        return "publish";
    }


    @PostMapping("/publish")
    private String doPublish(
        @RequestParam("title") String title,
        @RequestParam("description") String description,
        @RequestParam("tag") String tag,
        HttpServletRequest request,
        Model model){

<<<<<<< HEAD
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title == null || title=="") {
            model.addAttribute("error","标题不能为空");
            return "publish";
        }

        if(description ==null || description == "") {
            model.addAttribute("error","内容不能为空");
            return "publish";
        }

        if(tag == null || tag == "") {
            model.addAttribute("error","标签不能为空");
            return "publish";
        }


        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length!=0)
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user =userMapper.findByToken(token);
                    if(user != null) {
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
=======
        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user =userMapper.findByToken(token);
                if(user != null) {
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
>>>>>>> bb7014c18066c4945d1a1495d0950e2e53c349f3
        if(user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        questionMapper.create(question);
        return "redirect:/";

    }
    //接收到这三个参数之后，自动去注入questionMapper






}
