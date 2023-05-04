package com.steven.cns.auth.resource.server.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author steven.cao
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${security.jwt.key-uri}")
    private String keyUri;

    @Value("${security.jwt.token-header}")
    private String tokenHeader;

    private JwtAccessTokenConverter accessTokenConverter;
    private TokenStore tokenStore;
    private PublicKey publicKey;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtAccessTokenConverter accessTokenConverter, TokenStore tokenStore, PublicKey publicKey) {
//        super(authenticationManager);
        this.accessTokenConverter = accessTokenConverter;
        this.tokenStore = tokenStore;
        this.publicKey = publicKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            String accessTokenValue = header.substring(7);
            if (StringUtils.isEmpty(accessTokenValue)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            OAuth2Authentication authentication = tokenStore.readAuthentication(accessTokenValue);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
                return;
            }

            try {
//                Map<String, ?> tokenClaims = accessTokenConverter.decode(accessTokenValue);
                Map<String, ?> tokenClaims = new HashMap<>();
                DefaultAccessTokenConverter converter = new DefaultAccessTokenConverter();
                OAuth2Authentication oauthAuthentication = accessTokenConverter.extractAuthentication(tokenClaims);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(oauthAuthentication.getPrincipal(), null, oauthAuthentication.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
                return;
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}