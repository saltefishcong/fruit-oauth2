package niubi.authority.social.weixin.service;

import niubi.authority.social.weixin.eity.User;
import niubi.authority.social.weixin.repository.UserRepository;
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
public class WeiXinDetailsService implements UserDetailsService, SocialUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("UserDetails  "+username);
//        User user=userRepository.findByUsername(username);
//        if(user == null){
//            throw new UsernameNotFoundException("用户不存在");
//        }
//        List<GrantedAuthority> list=new ArrayList<>();
//        list.add(new SimpleGrantedAuthority("ROLE_TEST"));
//        return new org.springframework.security.core.userdetails.User(username,passwordEncoder.encode(user.getPassword())
//                , list);
        return  null;
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

    public void save(String username ,String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }
}
