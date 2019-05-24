package pos.Pinterest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pos.Pinterest.model.User;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(final String username) {

        User user = userService.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());


        return buildUserForAuthentication(user, authorities);
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities){

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true, true, authorities);
    }

    public List<GrantedAuthority> buildUserAuthority(List<String> userRoles){
        Set<GrantedAuthority> setAuths = new HashSet<>();

        for(String userRole: userRoles){
            setAuths.add(new SimpleGrantedAuthority(userRole));
        }
        List<GrantedAuthority> Result = new ArrayList<>(setAuths);

        return Result;
    }
}
