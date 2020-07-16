package niubi.authority.oauth2.openid.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author congcong
 * @className SmsAuthenticationToken
 * @date 2019/3/9 16:25
 * 类描述
 */
public class OpenIdAuthenticationToken extends AbstractAuthenticationToken {

    private final Object providerId;

    private final Object providerUserId;

    private static final long serialVersionUID = 1533092775910246006L;

    //SmsAuthenticationFilter中构建的未认证的Authentication
    public OpenIdAuthenticationToken(String providerId,String providerUserId){
        super(null);
        this.providerId = providerId;
        this.providerUserId =providerUserId;
        setAuthenticated(false);
    }

    //SmsAuthenticationProvider中构建已认证的Authentication
    public OpenIdAuthenticationToken(Object providerId, String providerUserId, Collection<? extends GrantedAuthority> authorities){
        super(authorities);
        this.providerId = providerId;
        this.providerUserId =providerUserId;
        super.setAuthenticated(true);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    public Object getProviderUserId() {
        return providerUserId;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public void setDetails(Object details) {
        super.setDetails(details);
    }
}
