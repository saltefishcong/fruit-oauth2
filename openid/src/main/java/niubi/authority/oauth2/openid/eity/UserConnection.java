package niubi.authority.oauth2.openid.eity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "userconnection")
@Entity
@IdClass(UserConnectionPk.class)
public class UserConnection {

    @Id
    @Column(name = "\"userId\"")
    private String userId;

    @Id
    @Column(name = "\"providerId\"")
    private String providerId;

    @Id
    @Column(name = "\"providerUserId\"")
    private String providerUserId;

    @Column(name = "rank")
    private int rank;

    @Column(name = "\"displayName\"")
    private String displayName;

    @Column(name = "\"accessToken\"")
    private String accessToken;

    @Column(name = "\"refreshToken\"")
    private String refreshToken;

    @Column(name = "\"expireTime\"")
    private Long expireTime;
}
