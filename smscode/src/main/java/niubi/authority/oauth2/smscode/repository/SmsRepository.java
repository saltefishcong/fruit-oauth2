package niubi.authority.oauth2.smscode.repository;

import niubi.authority.oauth2.smscode.eity.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<Sms,Integer> {

    Sms findByPhone(String phone);
}
