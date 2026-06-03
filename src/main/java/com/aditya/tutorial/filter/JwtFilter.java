package com.aditya.tutorial.filter;

import com.aditya.tutorial.entity.User;
import com.aditya.tutorial.service.JwtService;
import com.aditya.tutorial.service.UserDetailService;
import com.aditya.tutorial.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

       final String tokenHeader= request.getHeader("Authorization");
       if(tokenHeader==null || !tokenHeader.startsWith("Bearer")){
           filterChain.doFilter(request,response);
           return;
       }

       String token=tokenHeader.split("Bearer ")[1];

       Long userId= jwtService.getUserIdFromToken(token);

       if(userId!=null && SecurityContextHolder.getContext().getAuthentication()==null){
           User user=userDetailService.getUserById(userId);

           //need to set the authentication into security Context holeder
           UsernamePasswordAuthenticationToken authenticationToken
                   =new UsernamePasswordAuthenticationToken(user,null,null);

           authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

           SecurityContextHolder.getContext().setAuthentication(authenticationToken);

       }
       filterChain.doFilter(request,response);
    }
}
