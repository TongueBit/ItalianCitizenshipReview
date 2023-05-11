package com.italiancitizenshipreview.italiancitizenshipreviewbackend.service;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.SecurityUser;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class JpaUserDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;
    public JpaUserDetailsService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(user -> {
                    SecurityUser securityUser = new SecurityUser(user);

                    // Get the userId and add it to the UserDetails
                    Long userId = user.getUserId(); // Replace with the appropriate getter method for the user ID
                    securityUser.setUserId(userId);

                    // Create a cookie containing the user ID and set it in the response
                    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                    HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
                    Cookie userIdCookie = new Cookie("userId", String.valueOf(userId));
                    userIdCookie.setPath("/");
                    response.addCookie(userIdCookie);

                    return securityUser;
                })
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }



}