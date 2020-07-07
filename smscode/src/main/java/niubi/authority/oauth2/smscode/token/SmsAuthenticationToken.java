package niubi.authority.oauth2.sms.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author congcong
 * @className SmsAuthenticationToken
 * @date 2019/3/9 16:25
 * 类描述
 */
public class SmsAuthenticationToken extends AbstractAuthenticationToken {

    //手机号
    private final Object phone;

    private static final long serialVersionUID = 1533092775910246006L;

    //SmsAuthenticationFilter中构建的未认证的Authentication
    public SmsAuthenticationToken(String phone){
        super(null);
        this.phone = phone;
        setAuthenticated(false);
    }

    //SmsAuthenticationProvider中构建已认证的Authentication
    public SmsAuthenticationToken(Object phone, Collection<? extends GrantedAuthority> authorities){
        super(authorities);
        this.phone = phone;
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
        return this.phone;
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
