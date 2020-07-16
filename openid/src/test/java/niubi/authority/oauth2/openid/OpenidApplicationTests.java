package niubi.authority.oauth2.openid;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OpenidApplicationTests {

//    @Test
    public void contextLoads() {
        System.out.println(new BCryptPasswordEncoder().encode("client_secret"));
    }

}
