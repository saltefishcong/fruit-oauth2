package niubi.authority.oauth2.sms.config;

import niubi.authority.oauth2.sms.filter.SmsCodeFilter;
import niubi.authority.oauth2.sms.handler.SmsLoginFailureHandler;
import niubi.authority.oauth2.sms.handler.SmsLoginSuccessHandler;
import niubi.authority.oauth2.sms.repository.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author congcong
 * @className SecurityConfig
 * @date 2019/3/2 15:30
 * 类描述
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled=true)   //开启jsr250Enabled方法级认证
@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SmsLoginSuccessHandler successHandler;

    @Autowired
    private SmsLoginFailureHandler failureHandler;

    @Autowired
    private SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;

    @Autowired
    private SmsRepository smsRepository;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        SmsCodeFilter smsCodeFilter=new SmsCodeFilter();
        smsCodeFilter.setLoginFailureHandler(failureHandler);
        smsCodeFilter.setSmsRepository(smsRepository);

        http
                .addFilterBefore(smsCodeFilter,UsernamePasswordAuthenticationFilter.class)
                //设置短信认证方式
                .apply(smsAuthenticationSecurityConfig)
                .and()
                .formLogin()
                .loginPage("http://localhost:9007/")
                .and()
                //访问资源授权设置
                .authorizeRequests()
                .antMatchers("/SmsCode","/authentication/phone")
                .permitAll()
//                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest()
                .authenticated();//关闭cors攻击验证
            http.csrf().disable();//禁用CSRF保护
    }

    /**
     * AuthenticationManager
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
