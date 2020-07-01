package niubi.authority.oauth2.clientcredentials.repository;

import niubi.authority.oauth2.clientcredentials.eity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);
}
