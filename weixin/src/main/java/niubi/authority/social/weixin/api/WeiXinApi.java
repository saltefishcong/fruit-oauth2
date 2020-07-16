package niubi.authority.social.weixin.api;

import niubi.authority.social.weixin.eity.WeiXin;
import niubi.authority.social.weixin.eity.WeiXinInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author congcong
 * @className WeiXinApi
 * @date 2019/3/20 15:13
 * 类描述
 */
public class WeiXinApi implements ApiAdapter<WeiXin> {

    private String openId;

    public WeiXinApi(String openId){
        this.openId=openId;
    }

    public WeiXinApi(){};

    @Override
    public boolean test(WeiXin weiXin) {
        System.out.println("微信登录测试");
        return true;
    }

    @Override
    public void setConnectionValues(WeiXin weiXin, ConnectionValues connectionValues) {
        WeiXinInfo info=weiXin.getWeiXinInfo(openId);
        connectionValues.setDisplayName(info.getNickname());
        connectionValues.setImageUrl(info.getHeadimgurl());
        connectionValues.setProviderUserId(info.getOpenid());
    }

    @Override
    public UserProfile fetchUserProfile(WeiXin weiXin) {
        return null;
    }

    @Override
    public void updateStatus(WeiXin weiXin, String s) {

    }
}
