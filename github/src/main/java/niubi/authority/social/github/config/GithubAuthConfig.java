package niubi.authority.social.github.config;

import niubi.authority.social.github.factory.GithubConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.AuthenticationNameUserIdSource;

/**
 * @author congcong
 * @className GithubAuthencationConfig
 * @date 2019/3/13 15:54
 * 类描述
 */
@Configuration
public class GithubAuthConfig extends SocialConfigurerAdapter {

    private String providerId="github";

    private String clientId="5ab1e08b2ca53453f67e";

    private String clientSecret="5af548126f35d6da622a17b61c1741d7ebcbc1c9";

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(createConnectionFactory());
    }

    public ConnectionFactory<?> createConnectionFactory(){
        return new GithubConnectionFactory(providerId,clientId,clientSecret);
    }

    // 不写这个方法会报 异常java.lang.IllegalArgumentException: One configuration class must implement getUserIdSource from SocialConfigurer.
    @Override
    public UserIdSource getUserIdSource() {
        // TODO Auto-generated method stub
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return null;
    }
}
