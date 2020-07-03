package niubi.authority.social.github.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

/**
 * @author congcong
 * @className UserController
 * @date 2019/3/13 15:05
 * 类描述
 */
@Controller
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @GetMapping("/user")
    @RolesAllowed({"TEST"})
    @ResponseBody
    public Authentication getUser(Authentication authentication){
        return authentication;
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin(){
        return "admin";
    }

    @GetMapping("/login.html")
    public String login(){
        return "login.html";
    }

    //找不到用户的时候跳到这个默认的连接
    @RequestMapping(value = "/signup")
    public String singUp(){
        System.out.println("signup 注册");
        return "singUp.html";
    }

    @RequestMapping(value = "/register")
    public String register(String username, HttpServletRequest request){
        System.out.println("register ");
        providerSignInUtils.doPostSignUp(username,new ServletWebRequest(request));
        return "redirect:/user";
    }

    @RequestMapping(value = "/users")
    @ResponseBody
    public Authentication user(Authentication authentication){
        return authentication;
    }
}
