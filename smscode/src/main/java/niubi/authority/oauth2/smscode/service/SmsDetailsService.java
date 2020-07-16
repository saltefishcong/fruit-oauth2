package niubi.authority.oauth2.smscode.service;

import niubi.authority.oauth2.smscode.eity.User;
import niubi.authority.oauth2.smscode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author congcong
 * @className SmsService
 * @date 2019/3/10 13:57
 * 类描述
 */
@Service
public class SmsDetailsService implements UserDetailsService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        System.out.println("SmsService  "+phone +"  username");
        User user=userRepository.findByPhone(phone);
        List<GrantedAuthority> list=new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_TEST"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),passwordEncoder.encode(user.getPassword())
                , list);
    }

}
