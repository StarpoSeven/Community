package com.zxp.community.community.Controler;

import com.zxp.community.community.DTO.AccessTokenDTO;
import com.zxp.community.community.DTO.GithubUser;
import com.zxp.community.community.mapper.UserMapper;
import com.zxp.community.community.model.User;
import com.zxp.community.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String ClientID;

    @Value("${github.client.secret}")
    private String ClientSecret;

    @Value("${github.client.redirect_uri}")
    private String ClientRedirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state")String state,
                           HttpServletResponse response) throws IOException {



        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(ClientID);
        accessTokenDTO.setClient_secret(ClientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(ClientRedirectUri);
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = gitHubProvider.getUser(accessToken);

        if(githubUser != null) {
            //存放入数据库
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.Insert(user);//插入数据库之后，以token作为依据，相当于手动写入cookie
            response.addCookie(new Cookie("token",token));

//            //登录成功显示Cookie和Session
//            request.getSession().setAttribute("user",githubUser); 将这里注释之后，登录完以后，不会页面上有登录信息显示
            return "redirect:/";

        } else {
            //登录失败，重新登录
            return "redirect:/";
        }


    }
}
