package com.bootcamp.rule_engine.security;


import com.bootcamp.rule_engine.constant.ErrorCode;
import com.bootcamp.rule_engine.utils.JWTParser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.SneakyThrows;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@Order(1)
public class JWTAuthorizationTokenFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String USER_ID_CLAIM_NAME = "userId";
    private static final String USER_ROLE_CLAIM_NAME = "userRole";
    private static final String[] EXCLUDE_PATHS = {"POST /login", "POST /users/create-user"};

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if(containsToken(request)){
                String jwtToken = request.getHeader(AUTHORIZATION_HEADER).replace(TOKEN_PREFIX, StringUtils.EMPTY);

                Claims claims = JWTParser.decodeJWT(jwtToken);

                SecurityContext context = parseClaims(jwtToken, claims);
                SecurityContextHolder.setUserContext(context);

                if(getMyUserFilter(request, response)){
                    filterChain.doFilter(request, response);
                }
            }
            else{
                //Since this section it's executed before the handler exception, the program crashes, so we decided to establish the response by code.
                response.setStatus(401);
                response.setHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
                response.getWriter().write(ErrorCode.CODE_08.getMessage());
                response.getWriter().flush();
            }

        } catch ( JwtException e){
            System.out.println("Error verifying jwt token: " + e.getMessage());
        } finally {
            SecurityContextHolder.clearContext();
        }
    }


    private boolean containsToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(AUTHORIZATION_HEADER);
        return authenticationHeader != null && authenticationHeader.startsWith(TOKEN_PREFIX);
    }


    private String claimKey(Claims claims, String key) {
        String value = (String)claims.get(key);
        return Optional.ofNullable(value).orElseThrow();
    }

    public SecurityContext parseClaims(String jwtToken, Claims claims) {
        String userId = claimKey(claims, USER_ID_CLAIM_NAME);
        String roleName = claimKey(claims, USER_ROLE_CLAIM_NAME);
        SecurityContext context = new SecurityContext();
        try{
            context.setUserId(UUID.fromString(userId));
            context.setRoleName(roleName);
            context.setToken(jwtToken);
        }catch (IllegalArgumentException e){
            throw new MalformedJwtException("Error parsing jwt");
        }

        return context;
    }

    @SneakyThrows
    private boolean getMyUserFilter(HttpServletRequest request, HttpServletResponse response) {
        boolean authorized = true;
        String method = request.getMethod();
        String path = request.getRequestURI();
        //Hashmap o if

        if (method.equals("GET") && path.startsWith("/users/") && !path.endsWith(SecurityContextHolder.getContext().getUserId().toString())) {
            response.setStatus(401);
            response.setHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
            response.getWriter().write(ErrorCode.CODE_09.getMessage());
            response.getWriter().flush();
            authorized = false;
        }

        return authorized;
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        String methodPlusPaths = request.getMethod() + " " + request.getRequestURI();
        return Arrays.stream(EXCLUDE_PATHS).anyMatch(path -> path.equalsIgnoreCase(methodPlusPaths));
    }
}