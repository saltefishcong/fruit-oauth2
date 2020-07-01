package niubi.authority.oauth2.sms.controller;

import niubi.authority.oauth2.sms.eity.Sms;
import niubi.authority.oauth2.sms.repository.SmsRepository;
import niubi.authority.oauth2.sms.util.SmsCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SmsController {

    @Autowired
    SmsRepository smsRepository;

    @RequestMapping(value = "/SmsCode", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String smsCode(HttpServletRequest request){
        String code= SmsCodeUtils.getSmsCode();
//        request.getSession().setAttribute("smsCode",code);
        Sms sms = new Sms();
        sms.setCode(code);
        sms.setPhone(request.getParameter("phone"));
        smsRepository.save(sms);
        SmsCodeUtils.sendSmsCode(request.getParameter("phone"),code);
        return "发送成功";
    }

    @RequestMapping("/login.html")
    public String login() {
        return "login.html";
    }

    @RequestMapping("/helloTest")
    @ResponseBody
    public String test()
    {
        return "test , sms";
    }
}
