package com.zxp.community.community.Controler;

import com.zxp.community.community.DTO.QuestionDTO;
import com.zxp.community.community.mapper.QuestionMapper;
import com.zxp.community.community.mapper.UserMapper;
import com.zxp.community.community.model.Question;
import com.zxp.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexControler {

    @Autowired
    private UserMapper userMapper;



    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length!=0)
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user =userMapper.findByToken(token);
                    if(user != null) {
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        List<QuestionDTO> questionList = questionMapper.list();
        model.addAttribute("question",questionList);
        return "index";
    }
}
