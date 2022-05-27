package com.nsu.habbitrabbit.provider;

import com.nsu.habbitrabbit.domain.Credentials;
import com.nsu.habbitrabbit.domain.Role;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import static org.springframework.util.StringUtils.hasText;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;

@Component
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION = "Authorization";
    private static final String ADMIN_HEADER = "Admin";

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null && jwtProvider.validateToken(token)) {
            String userEmail = jwtProvider.getEmail(token);

            var roles = new HashSet<Role>();
            if (isAdmin((HttpServletRequest) servletRequest)) {
                roles.add(new Role(Role.ROLE_ADMIN));
            }

            roles.add(new Role(Role.ROLE_USER));
            var cred = new Credentials(userEmail, "hui", roles);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(cred, null, cred.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

            System.out.println("Login success");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isAdmin(HttpServletRequest request) {
        String val = request.getHeader(ADMIN_HEADER);

        if (val == null) {
            return false;
        }

        return true;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }
}