package niubi.authority.oauth2.authorizationcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    @GetMapping("/auth/login")
    public String loginPage(){
        return "code-login";
    }
}
