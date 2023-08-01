package penterest.spring.global.security.util;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor

public class CustomUserDetails implements UserDetails {

    private String id;
    private String pw;
    private String authority;
    private String email;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        // Ensure that 'authority' field is not null or empty
        if (authority != null && !authority.isEmpty()) {
            auth.add(new SimpleGrantedAuthority(authority));
        }
        return auth;
    }

    @Override
    public String getPassword() {
        return pw;
    }

    @Override
    public String getUsername() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


    public void setEmail(String email) {
        this.email = email;
    }
}
