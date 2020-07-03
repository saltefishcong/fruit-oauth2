package niubi.authority.social.github.provider;

import niubi.authority.social.github.eity.Github;
import niubi.authority.social.github.service.GithubImp;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author congcong
 * @className GithubProvider
 * @date 2019/3/13 15:35
 * 类描述
 */
public class GithubProvider extends AbstractOAuth2ServiceProvider<Github> {

    private static final String authorizeUrl="https://github.com/login/oauth/authorize";

    private static final String accessTokenUrl="https://github.com/login/oauth/access_token";

    private String clientId;

    public GithubProvider(String clientId, String clientSecret){
        super(new OAuth2Template(clientId,clientSecret,authorizeUrl,accessTokenUrl));
        this.clientId=clientId;
    }

    @Override
    public Github getApi(String access_token) {
        return new GithubImp(access_token);
    }

}
