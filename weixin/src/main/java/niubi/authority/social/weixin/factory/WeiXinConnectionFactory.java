package niubi.authority.social.weixin.factory;

import niubi.authority.social.weixin.api.WeiXinApi;
import niubi.authority.social.weixin.eity.WeiXin;
import niubi.authority.social.weixin.grant.WeixinAccessGrant;
import niubi.authority.social.weixin.provider.WeiXinProvider;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @author congcong
 * @className WeiXinConnectionFactory
 * @date 2019/3/20 15:27
 * 类描述
 */
public class WeiXinConnectionFactory extends OAuth2ConnectionFactory<WeiXin> {

    public WeiXinConnectionFactory(String providerId, String appId, String appSecret){
        super(providerId,new WeiXinProvider(appId,appSecret),new WeiXinApi());
    }

    @Override
    public Connection<WeiXin> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<WeiXin>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
                accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
    }

    @Override
    public Connection<WeiXin> createConnection(ConnectionData data) {
        return new OAuth2Connection<WeiXin>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
    }

    private ApiAdapter<WeiXin> getApiAdapter(String providerUserId) {
        return new WeiXinApi(providerUserId);
    }

    private OAuth2ServiceProvider<WeiXin> getOAuth2ServiceProvider() {
        return (OAuth2ServiceProvider<WeiXin>) getServiceProvider();
    }

    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if(accessGrant instanceof WeixinAccessGrant) {
            return ((WeixinAccessGrant)accessGrant).getOpenId();
        }
        return null;
    }
}
