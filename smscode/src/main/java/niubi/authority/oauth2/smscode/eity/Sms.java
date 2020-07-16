package niubi.authority.oauth2.smscode.eity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author congcong
 * @className Sms
 * @date 2019/3/9 16:36
 * 类描述
 */
@Data
@Entity
@Table(name = "sms")
public class Sms {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "code")
    private String code;
}
