package niubi.authority.oauth2.clientcredentials.config;

import niubi.authority.oauth2.clientcredentials.handler.CredentialsLoginFailureHandler;
import niubi.authority.oauth2.clientcredentials.handler.CredentialsLoginSuccessHandler;
import niubi.authority.oauth2.clientcredentials.service.CredentialsUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class CredentialsSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CredentialsLoginSuccessHandler successHandler;

    @Autowired
    private CredentialsLoginFailureHandler failureHandler;

    @Autowired
    private CredentialsUserDetailsService credentialsUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin();
//        http.cors().disable();
        http.csrf().disable();//关闭cors攻击验证
//        http.sessionManagement().maximumSessions(1); //设置用户只能登录一次，下一次登录会把上一次登录去掉
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(credentialsUserDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * AuthenticationManager
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
