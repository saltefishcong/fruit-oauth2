package niubi.authority.oauth2.openid.service;

import niubi.authority.oauth2.openid.eity.User;
import niubi.authority.oauth2.openid.eity.UserConnection;
import niubi.authority.oauth2.openid.eity.UserConnectionPk;
import niubi.authority.oauth2.openid.repository.UserConnectionRepository;
import niubi.authority.oauth2.openid.repository.UserRepository;
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
import java.util.Optional;

/**
 * @author congcong
 * @className SmsService
 * @date 2019/3/10 13:57
 * 类描述
 */
@Service
public class OpenIdDetailsService implements UserDetailsService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConnectionRepository userConnectionRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println("OpenIdDetailsService  "+userId +"  userId");
        User user=userRepository.findByUsername(userId);
        List<GrantedAuthority> list=new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_TEST"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),passwordEncoder.encode(user.getPassword())
                , list);
    }

    public UserDetails loadUserByUserConnectionPk(String providerId , String providerUserId ){
        UserConnection userConnection= userConnectionRepository.findByProviderIdAndProviderUserId(providerId,providerUserId);
        User user = userRepository.findByUsername(userConnection.getUserId());
        List<GrantedAuthority> list=new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_TEST"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),passwordEncoder.encode(user.getPassword())
                , list);
    }

}
