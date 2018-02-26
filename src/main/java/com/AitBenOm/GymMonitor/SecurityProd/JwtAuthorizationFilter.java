package com.AitBenOm.GymMonitor.SecurityProd;

import com.AitBenOm.GymMonitor.Service.UserService;
import com.AitBenOm.GymMonitor.entities.User;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.AitBenOm.GymMonitor.SecurityProd.SecurityConstants.HEADER_STRING;
import static com.AitBenOm.GymMonitor.SecurityProd.SecurityConstants.SECRET;
import static com.AitBenOm.GymMonitor.SecurityProd.SecurityConstants.TOKEN_PREFIX;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

    private final UserService userService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        System.out.println("Header is "+header);
        System.out.println("Header is "+header);
        System.out.println("Header is "+header);
        System.out.println("Header is "+header);
        System.out.println("Header is "+header);
        System.out.println("Header is "+header);
        if(header == null || !header.startsWith(TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuth =getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuth);
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request){
        String token =request.getHeader(HEADER_STRING);
        System.out.println("Token is " +token);
        String email = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX,""))
                .getBody().getSubject();
        User user = userService.loadUserByEmail(email);
        UserDetails userDetails = userService.loadUserByUsername(email);
        return email != null ? new UsernamePasswordAuthenticationToken(user, null, userDetails.getAuthorities()): null ;
    }
}
