package niubi.authority.social.github.factory;

import niubi.authority.social.github.api.GithubApi;
import niubi.authority.social.github.eity.Github;
import niubi.authority.social.github.provider.GithubProvider;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author congcong
 * @className GithubConnectionFactory
 * @date 2019/3/13 15:44
 * 类描述
 */
public class GithubConnectionFactory extends OAuth2ConnectionFactory<Github> {

    public GithubConnectionFactory(String providerId,String clientId,String clientSecret) {
        super(providerId, new GithubProvider(clientId,clientSecret), new GithubApi());
    }
}
