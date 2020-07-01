package niubi.authority.oauth2.sms.filter;

import niubi.authority.oauth2.sms.eity.Sms;
import niubi.authority.oauth2.sms.handler.SmsLoginFailureHandler;
import niubi.authority.oauth2.sms.repository.SmsRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author congcong
 * @className SmsCodeFilter
 * @date 2019/3/10 15:03
 * 类描述
 */
public class SmsCodeFilter extends OncePerRequestFilter {

    private SmsLoginFailureHandler loginFailureHandler;

    private SmsRepository smsRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("短信验证码filter   地址:" +httpServletRequest.getRequestURI()+"   方法: "+httpServletRequest.getMethod());
        if(httpServletRequest.getRequestURI().equals("/authentication/phone") && httpServletRequest.getMethod().equals("POST")){
            try{
                validate(httpServletRequest);
            }catch (Exception e){
//                loginFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                System.out.println("验证码有误");
                return;
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void validate(HttpServletRequest request){
//        String smsCodeInSession=(String) request.getSession().getAttribute("smsCode");
        String smsCodeInRequest=request.getParameter("smsCode");
        Sms sms = smsRepository.findByPhone(request.getParameter("phone"));
        if(smsCodeInRequest ==null || smsCodeInRequest.equals("")){
            throw new NullPointerException("验证码不能为空");
        }
        if (sms == null) {
            throw new NullPointerException("验证码不存在");
        }
        if(!smsCodeInRequest.equals(sms.getCode())){
            throw new NullPointerException("验证码不正确");
        }
//        request.getSession().removeAttribute("smsCode");
    }

    public void setLoginFailureHandler(SmsLoginFailureHandler loginFailureHandler) {
        this.loginFailureHandler = loginFailureHandler;
    }

    public void setSmsRepository(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }
}
