package niubi.authority.oauth2.openid.eity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserConnectionPk implements Serializable {

    private String userId;

    private String providerId;

    private String providerUserId;
}
