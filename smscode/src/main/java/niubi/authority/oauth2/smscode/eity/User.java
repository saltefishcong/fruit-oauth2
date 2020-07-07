package niubi.authority.oauth2.sms.eity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author congcong
 * @className User
 * @date 2019/3/2 18:08
 * 类描述
 */
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;
}
