package com.shoppingapp.restservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingapp.restservice.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
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
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private String username;
    private int id ;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder().setIssuedAt(new Date())
                .setSubject((((User) authResult.getPrincipal()).getUsername()))
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
                .signWith(SignatureAlgorithm.HS512, "kodigo").compact();

        this.setUsername(((User) authResult.getPrincipal()).getUsername());
        this.setId(((User) authResult.getPrincipal()).getId());

        response.addHeader("Authorization", "Bearer " + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                "{\"data\":" +
                        "{" +
                        "\"username\":\""+ this.getUsername() +"\"," +
                        "\"id\":\""+ this.getId() +"\"," +
                        "\"sessionCookie\": {" +
                        "\"token\":\"Bearer " + token + "\"," +
                        "\"Expiration\":\"" + new Date(System.currentTimeMillis() + 864_000_000) + "\"" +
                        "}" +
                        "}" +
                     "}"
        );
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {

        super.unsuccessfulAuthentication(request, response, failed);
    }
}