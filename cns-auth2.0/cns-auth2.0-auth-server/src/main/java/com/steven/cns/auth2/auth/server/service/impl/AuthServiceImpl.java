package com.steven.cns.auth2.auth.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthorizationServerTokenServices {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private ClientDetailsService clientDetailsService;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private TokenStore tokenStore;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
        ClientDetails client = clientDetailsService.loadClientByClientId(authentication.getOAuth2Request().getClientId());
        TokenRequest tokenRequest = new TokenRequest(authentication.getOAuth2Request().getRequestParameters(), client.getClientId(), client.getScope(), "password");

        // Authenticate user
//        Authentication userAuthentication = authenticationManager.authenticate(new OAuth2Authentication(tokenRequest.createOAuth2Request(), authentication.getUserAuthentication()));

        // Generate access token
        DefaultOAuth2AccessToken accessToken = new DefaultOAuth2AccessToken(UUID.randomUUID().toString());
        accessToken.setExpiration(new Date(System.currentTimeMillis() + (client.getAccessTokenValiditySeconds() * 1000L)));

        // Add custom info to token
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
//        enhancerChain.setTokenEnhancers(Arrays.asList(new CustomTokenEnhancer(), accessTokenConverter));
        OAuth2AccessToken enhancedAccessToken = enhancerChain.enhance(accessToken, authentication);
//        ((DefaultOAuth2AccessToken) enhancedAccessToken).setAdditionalInformation(userAuthentication.getDetails());

        // Save access token
//        tokenStore.storeAccessToken(enhancedAccessToken, userAuthentication);

        // Generate refresh token
        DefaultOAuth2AccessToken refreshToken = new DefaultOAuth2AccessToken(UUID.randomUUID().toString());
        refreshToken.setExpiration(new Date(System.currentTimeMillis() + (client.getRefreshTokenValiditySeconds() * 1000L)));
        ((DefaultOAuth2AccessToken) refreshToken).setAdditionalInformation(enhancedAccessToken.getAdditionalInformation());

        // Save refresh token
//        tokenStore.storeRefreshToken(refreshToken, userAuthentication);

        // Set response token
        DefaultOAuth2AccessToken responseToken = new DefaultOAuth2AccessToken(enhancedAccessToken);
//        responseToken.setRefreshToken(refreshToken);

        return responseToken;
    }

    @Override
    public OAuth2AccessToken refreshAccessToken(String refreshTokenValue, TokenRequest tokenRequest) throws AuthenticationException {
        if (StringUtils.isEmpty(refreshTokenValue)) {
            throw new InvalidGrantException("Refresh token not found");
        }

        OAuth2RefreshToken refreshToken = tokenStore.readRefreshToken(refreshTokenValue);
        if (refreshToken == null) {
            throw new InvalidGrantException("Invalid refresh token");
        }

        OAuth2Authentication authentication = tokenStore.readAuthenticationForRefreshToken(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
//        Authentication userAuthentication = new OAuth2Authentication(authentication.getOAuth2Request(), userDetails);

        OAuth2AccessToken accessToken = tokenStore.getAccessToken(authentication);
        DefaultOAuth2AccessToken newAccessToken = new DefaultOAuth2AccessToken(UUID.randomUUID().toString());
        newAccessToken.setExpiration(new Date(System.currentTimeMillis() + (accessToken.getExpiresIn() * 1000L)));
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
//        enhancerChain.setTokenEnhancers(Arrays.asList(new CustomTokenEnhancer(), accessTokenConverter));
//        OAuth2AccessToken enhancedAccessToken = enhancerChain.enhance(newAccessToken, userAuthentication);
        OAuth2AccessToken enhancedAccessToken = null;
//        ((DefaultOAuth2AccessToken) enhancedAccessToken).setAdditionalInformation(userDetails);

        tokenStore.removeAccessToken(accessToken);
        tokenStore.storeAccessToken(enhancedAccessToken, authentication);

        return enhancedAccessToken;
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        OAuth2AccessToken accessToken = tokenStore.getAccessToken(authentication);
        if (accessToken == null) {
            throw new OAuth2Exception("Access token not found for user '" + authentication.getName() + "'");
        }
        return accessToken;
    }

}
