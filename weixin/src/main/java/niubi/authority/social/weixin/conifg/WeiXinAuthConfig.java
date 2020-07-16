package niubi.authority.social.weixin.conifg;

import niubi.authority.social.weixin.factory.WeiXinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author congcong
 * @className WeiXinAuthConfig
 * @date 2019/3/20 15:35
 * 类描述
 */
@Configuration
@EnableSocial
public class WeiXinAuthConfig extends SocialConfigurerAdapter {

    private String providerId="weixin";
    private String appid="wxfd6965ab1fc6adb2";
    private String appsecret="66bb4566de776ac699ec1dbed0cc3dd1";

    @Autowired
    private DataSource dataSource;

    protected ConnectionFactory<?> createConnectionFactory() {
        return new WeiXinConnectionFactory(providerId, appid,appsecret);
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(createConnectionFactory());
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        return repository;
    }

    // 不写这个方法会报 异常java.lang.IllegalArgumentException: One configuration class must implement getUserIdSource from SocialConfigurer.
    @Override
    public UserIdSource getUserIdSource() {
        // TODO Auto-generated method stub
        return new AuthenticationNameUserIdSource();
    }

    @Bean
    public SpringSocialConfigurer socialSecurityConfig() {
        // 默认配置类，进行组件的组装
        // 包括了过滤器SocialAuthenticationFilter 添加到security过滤链中
        return new SpringSocialConfigurer();
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        return new ProviderSignInUtils(connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator));
    }
}
