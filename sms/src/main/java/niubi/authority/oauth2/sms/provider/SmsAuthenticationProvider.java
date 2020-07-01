package niubi.authority.oauth2.sms.provider;

import niubi.authority.oauth2.sms.token.SmsAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;

/**
 * @author congcong
 * @className SmsAuthenticationProvider
 * @date 2019/3/9 16:33
 * 类描述
 */
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("SmsAuthenticationProvider : "+new Date());

        //此时的SmsAuthenticationToken还没有进行认证
        SmsAuthenticationToken smsAuthenticationToken=(SmsAuthenticationToken)authentication;
        UserDetails userDetails=userDetailsService.loadUserByUsername((String) smsAuthenticationToken.getPrincipal());
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        //认证成功后创建一个认证成功SmsAuthenticationToken
        SmsAuthenticationToken smsAuthenticationToken1=new SmsAuthenticationToken(userDetails,userDetails.getAuthorities());
        smsAuthenticationToken1.setDetails(smsAuthenticationToken.getDetails());
        return smsAuthenticationToken1;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsAuthenticationToken.class.isAssignableFrom(aClass);
    }


    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
