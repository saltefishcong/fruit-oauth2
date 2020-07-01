package niubi.authority.oauth2.sms.config;

import niubi.authority.oauth2.sms.filter.SmsAuthenticationFilter;
import niubi.authority.oauth2.sms.handler.SmsLoginFailureHandler;
import niubi.authority.oauth2.sms.handler.SmsLoginSuccessHandler;
import niubi.authority.oauth2.sms.provider.SmsAuthenticationProvider;
import niubi.authority.oauth2.sms.service.SmsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author congcong
 * @className SmsAuthenticationSecurityConfig
 * @date 2019/3/9 17:09
 * 类描述
 */
@Component
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private SmsLoginSuccessHandler loginSuccessHandler;

    @Autowired
    private SmsLoginFailureHandler loginFailureHandler;

    @Autowired
    private SmsDetailsService smsService;


    @Override
    public void configure(HttpSecurity builder) throws Exception {
        SmsAuthenticationFilter smsAuthenticationFilter=new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        smsAuthenticationFilter.setAuthenticationSuccessHandler(loginSuccessHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(loginFailureHandler);

        SmsAuthenticationProvider smsCodeAuthenticationProvider = new SmsAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(smsService);


        builder.authenticationProvider(smsCodeAuthenticationProvider)
                //设置短信过滤器位置
                .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

    public SmsLoginSuccessHandler getLoginSuccessHandler() {
        return loginSuccessHandler;
    }

    public SmsLoginFailureHandler getLoginFailureHandler() {
        return loginFailureHandler;
    }

}
