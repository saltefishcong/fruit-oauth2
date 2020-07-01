package niubi.authority.oauth2.clientcredentials.eity;
import lombok.Data;

import javax.persistence.*;

/**
 * @author congcong
 * @className User
 * @date 2019/3/11 19:01
 * 类描述
 */
@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

}

