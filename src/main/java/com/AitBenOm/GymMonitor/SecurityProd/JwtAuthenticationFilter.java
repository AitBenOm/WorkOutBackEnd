package com.AitBenOm.GymMonitor.SecurityProd;

import com.AitBenOm.GymMonitor.Service.UserService;
import com.AitBenOm.GymMonitor.entities.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static com.AitBenOm.GymMonitor.SecurityProd.SecurityConstants.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private UserService userService ;
    private User myUser= new User();

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService=userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User user =new ObjectMapper().readValue(request.getInputStream(), User.class);
            this.myUser=user;
            return  authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken( user.getEmail(), user.getPwd()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        ZonedDateTime expirationTimeUTC = ZonedDateTime.now(ZoneOffset.UTC).plus(EXPIRATION_TIME, ChronoUnit.MILLIS);
        String token = Jwts.builder().setSubject(
                ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername())
               // .setId(String.valueOf(this.userService.loadUserByEmailAndPwd(this.myUser.getEmail(),this.myUser.getPwd()).getIdUser()))
               // .claim("myUser",this.userService.loadUserByEmail(this.myUser.getEmail()))
                .claim("myUser",this.userService.loadUserByEmailAndPwd(this.myUser.getEmail(),this.myUser.getPwd()))
                .setExpiration(Date.from(expirationTimeUTC.toInstant()))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();

        JSONObject authToken = new JSONObject();
        authToken.put("token",token);
       response.getWriter().write(authToken.toJSONString());
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + authToken.toJSONString());
        //System.out.println(response.getHeader(HEADER_STRING));

    }
}
