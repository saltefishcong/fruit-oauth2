package niubi.authority.oauth2.sms.repository;

import niubi.authority.oauth2.sms.eity.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<Sms,Integer> {

    Sms findByPhone(String phone);
}
