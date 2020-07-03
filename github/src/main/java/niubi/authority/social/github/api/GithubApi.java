package niubi.authority.social.github.api;

import niubi.authority.social.github.eity.Github;
import niubi.authority.social.github.eity.GithubInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author congcong
 * @className GithubApi
 * @date 2019/3/13 15:33
 * 类描述
 */
public class GithubApi implements ApiAdapter<Github> {
    @Override
    public boolean test(Github github) {
        System.out.println("测试Github接口");
        return true;
    }

    @Override
    public void setConnectionValues(Github github, ConnectionValues connectionValues) {
        GithubInfo githubInfo = github.getGithubInfo();
        connectionValues.setProviderUserId(githubInfo.getLogin());
        connectionValues.setProfileUrl(githubInfo.getAvatar_url());
        connectionValues.setImageUrl(null);
        connectionValues.setDisplayName(githubInfo.getId().toString());
    }

    @Override
    public UserProfile fetchUserProfile(Github github) {
        return null;
    }

    @Override
    public void updateStatus(Github github, String s) {

    }
}
