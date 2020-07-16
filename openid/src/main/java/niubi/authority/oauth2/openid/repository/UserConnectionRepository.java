package niubi.authority.oauth2.openid.repository;

import niubi.authority.oauth2.openid.eity.UserConnection;
import niubi.authority.oauth2.openid.eity.UserConnectionPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConnectionRepository extends JpaRepository<UserConnection, UserConnectionPk> {

    UserConnection findByUserId(String userId);

    UserConnection findByProviderIdAndProviderUserId(String providerId , String providerUserId);
}
