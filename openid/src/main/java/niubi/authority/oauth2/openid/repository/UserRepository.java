package niubi.authority.oauth2.openid.repository;

import niubi.authority.oauth2.openid.eity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);

    User findByPhone(String phone);

}
