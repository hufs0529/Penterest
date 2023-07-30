package penterest.spring.global.security.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import penterest.spring.domain.member.service.CustomUserDetailService;

import java.util.Collection;

public class CustomUserDetails extends User{

    private String email;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String email) {
        super(username, password, authorities);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
