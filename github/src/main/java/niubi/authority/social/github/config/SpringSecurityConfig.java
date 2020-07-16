package niubi.authority.social.github.config;

import niubi.authority.social.github.handler.FailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author congcong
 * @className SpringSecurityConfig
 * @date 2019/3/13 15:09
 * 类描述
 */
@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FailureHandler failureHandler;

    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                //自定义登录页
                .loginPage("/login.html")
                .failureHandler(failureHandler)
                .and()
                //退出登录设置
                .logout()
                .logoutSuccessUrl("/login.html")
                .and()
                //设置第三方登录
                .apply(springSocialConfigurer)
                .and()
                //访问资源授权设置
                .authorizeRequests()
                .antMatchers("/login.html","/authentication/form","/auth/*","/signup","/register","/index")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .cors().disable()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter= new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("123");
        return  jwtAccessTokenConverter;
    }
}
