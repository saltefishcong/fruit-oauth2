package niubi.authority.social.weixin.provider;

import niubi.authority.social.weixin.eity.WeiXin;
import niubi.authority.social.weixin.service.WeiXinImp;
import niubi.authority.social.weixin.template.WeixinOAuth2Template;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author congcong
 * @className WeiXinProvider
 * @date 2019/3/20 15:07
 * 类描述
 */
public class WeiXinProvider extends AbstractOAuth2ServiceProvider<WeiXin> {

    private static final String WEIXIN_URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";

    private static final String WEIXIN_URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public WeiXinProvider(String appId, String appSecret){
        super(new WeixinOAuth2Template(appId, appSecret, WEIXIN_URL_AUTHORIZE, WEIXIN_URL_ACCESS_TOKEN));
    }

    @Override
    public WeiXin getApi(String accessToken) {
        return new WeiXinImp(accessToken);
    }
}
