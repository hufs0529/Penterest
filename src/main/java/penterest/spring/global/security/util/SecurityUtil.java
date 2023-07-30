package penterest.spring.global.security.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Slf4j
public class SecurityUtil {

    private SecurityUtil() { }

    // SecurityContext 에 유저 정보가 저장되는 시점
    // Request 가 들어올 때 JwtFilter 의 doFilter 에서 저장

//    public static String getLoingUserEmail() {
//        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        return userDetails.getEmail();
//    }

    public static String getLoingUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            return null; // Or throw an exception, depending on your requirement.
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        System.out.println(userDetails.getEmail());
        return userDetails.getEmail();
    }


    public static Collection<? extends GrantedAuthority> getAuthorities() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userDetails.getAuthorities();
    }

}