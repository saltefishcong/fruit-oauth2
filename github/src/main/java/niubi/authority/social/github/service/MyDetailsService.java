package niubi.authority.social.github.service;

import niubi.authority.social.github.eity.User;
import niubi.authority.social.github.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author congcong
 * @className MyDetailsService
 * @date 2019/3/11 19:05
 * 类描述
 */
@Service
public class MyDetailsService implements UserDetailsService, SocialUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserDetails  "+username);
        User user=userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> list=new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_TEST"));
        return new org.springframework.security.core.userdetails.User(username,passwordEncoder.encode(user.getPassword())
                , list);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        System.out.println("SocialUserDetails  "+userId +"  userId");
        List<GrantedAuthority> list=new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_TEST"));
        list.add(new SimpleGrantedAuthority("ADMIN"));
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new SocialUser(userId,passwordEncoder.encode("123456")
                , list);
    }
}
