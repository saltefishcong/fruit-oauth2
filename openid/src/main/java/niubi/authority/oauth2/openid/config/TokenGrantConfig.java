package niubi.authority.oauth2.openid.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetailsService;

@Configuration
public class TokenGrantConfig {

    @Autowired
    private ClientDetailsService clientDetailsService;

}
