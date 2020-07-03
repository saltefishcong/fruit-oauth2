package niubi.authority.social.github.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import niubi.authority.social.github.eity.Github;
import niubi.authority.social.github.eity.GithubInfo;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @author congcong
 * @className GithubImp
 * @date 2019/3/13 15:11
 * 类描述
 */
public class GithubImp extends AbstractOAuth2ApiBinding implements Github {

    private static final String USERINFO_URL="https://api.github.com/user?";

    public GithubImp(String access_token){
        super(access_token, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    @Override
    public GithubInfo getGithubInfo() {
        String result=this.getRestTemplate().getForObject(USERINFO_URL,String.class);
        System.out.println(result);
        GithubInfo githubInfo=new GithubInfo();
        JSONObject object= JSON.parseObject(result);
        githubInfo.setLogin(object.getString("login"));
        githubInfo.setAvatar_url(object.getString("avatar_url"));
        githubInfo.setNode_id(object.getString("node_id"));
        githubInfo.setId(object.getInteger("id"));
        githubInfo.setGravatar_id(object.getString("gravatar_id"));
        githubInfo.setUrl(object.getString("url"));
        githubInfo.setHtml_url(object.getString("html_url"));
        githubInfo.setFollowers_url(object.getString("followers_url"));
        githubInfo.setFollowing_url(object.getString("following_url"));
        githubInfo.setGists_url(object.getString("gists_url"));
        githubInfo.setStarred_url(object.getString("starred_url"));
        githubInfo.setSubscriptions_url(object.getString("subscriptions_url"));
        githubInfo.setOrganizations_url(object.getString("organizations_url"));
        githubInfo.setRepos_url(object.getString("repos_url"));
        githubInfo.setReceived_events_url(object.getString("received_events_url"));
        githubInfo.setType(object.getString("type"));
        githubInfo.setSite_admin(object.getString("site_admin"));
        githubInfo.setName(object.getString("name"));
        githubInfo.setCompany(object.getString("company"));
        githubInfo.setBlog(object.getString("blog"));
        githubInfo.setLocation(object.getString("location"));
        githubInfo.setEmail(object.getString("email"));
        githubInfo.setHireable(object.getString("hireable"));
        githubInfo.setBio(object.getString("bio"));
        githubInfo.setPublic_repos(object.getInteger("public_repos"));
        githubInfo.setPublic_gists(object.getInteger("public_gists"));
        githubInfo.setFollowers(object.getInteger("followers"));
        githubInfo.setFollowing(object.getInteger("following"));
        githubInfo.setCreated_at(object.getString("created_at"));
        githubInfo.setUpdated_at(object.getString("updated_at"));
        return githubInfo;
    }
}
