package niubi.authority.oauth2.sms.filter;

import niubi.authority.oauth2.sms.token.SmsAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author congcong
 * @className SmsAuthenticationFilter
 * @date 2019/3/5 13:37
 * 类描述
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private boolean postOnly=true;

    public SmsAuthenticationFilter(){
        super(new AntPathRequestMatcher("/authentication/phone","POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        System.out.println("SmsAuthenticationFilter : "+new Date());
        if (postOnly && !httpServletRequest.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
        }
        // 根据请求参数名，获取请求value
        String phone = getPhone(httpServletRequest);
        if (phone == null) {
            phone = "";
        }
        phone = phone.trim();

        // 生成对应的AuthenticationToken
        SmsAuthenticationToken authRequest = new SmsAuthenticationToken(phone);

        setDetails(httpServletRequest, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private String getPhone(HttpServletRequest request){
        return request.getParameter("phone");
    }

    public void setDetails(HttpServletRequest request,SmsAuthenticationToken smsAuthenticationToken){
        smsAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));
    }

}
