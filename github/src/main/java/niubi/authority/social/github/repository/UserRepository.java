package niubi.authority.social.github.repository;

import niubi.authority.social.github.eity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author congcong
 * @className UserRepository
 * @date 2019/3/8 12:52
 * 类描述
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);
}
