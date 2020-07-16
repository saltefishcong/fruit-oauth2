package niubi.authority.social.weixin.service;

import niubi.authority.social.weixin.eity.WeiXin;
import niubi.authority.social.weixin.eity.WeiXinInfo;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @author congcong
 * @className WeiXinImp
 * @date 2019/3/20 14:50
 * 类描述
 */
public class WeiXinImp extends AbstractOAuth2ApiBinding implements WeiXin {

    private static String  WEIXIN_USERINFO="https://api.weixin.qq.com/sns/userinfo?openid=%s";

    public WeiXinImp(String accessToken){
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    @Override
    public WeiXinInfo getWeiXinInfo(String openId) {
        String result=this.getRestTemplate().getForObject(String.format(WEIXIN_USERINFO,openId),String.class);
        System.out.println("result: "+result);
        return new WeiXinInfo();
    }
}
