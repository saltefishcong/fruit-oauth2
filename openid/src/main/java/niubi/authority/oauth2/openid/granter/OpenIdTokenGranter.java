package niubi.authority.oauth2.openid.granter;

import niubi.authority.oauth2.openid.service.OpenIdDetailsService;
import niubi.authority.oauth2.openid.token.OpenIdAuthenticationToken;
import niubi.authority.oauth2.smscode.service.SmsDetailsService;
import niubi.authority.oauth2.smscode.token.SmsAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

public class SmsTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "open_id";

    private OpenIdDetailsService openIdDetailsService;


    public SmsTokenGranter(OpenIdDetailsService openIdDetailsService,AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.openIdDetailsService= openIdDetailsService;
    }


    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = tokenRequest.getRequestParameters();
        String providerId =parameters.get("providerId");
        String providerUserId =parameters.get("providerUserId");
        UserDetails userDetails = openIdDetailsService.loadUserByUserConnectionPk(providerId,providerUserId);
        if (userDetails == null) {
            throw new InvalidGrantException("无法获取用户信息");
        }
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        OpenIdAuthenticationToken authentication = new OpenIdAuthenticationToken(providerId,providerUserId,userDetails.getAuthorities());
        authentication.setDetails(userDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(storedOAuth2Request, authentication);
        return oAuth2Authentication;
    }
}
