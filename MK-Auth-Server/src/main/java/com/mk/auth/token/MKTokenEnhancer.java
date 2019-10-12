package com.mk.auth.token;

import com.google.common.collect.Maps;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import javax.xml.soap.Detail;
import java.util.Collection;
import java.util.Map;

/**
 * @Author liumingkang
 * @Date 2019-10-04 07:49
 * @Destcription token要增加的额外信息
 * @Version 1.0
 **/
public class MKTokenEnhancer implements TokenEnhancer
{

    private static final String TOKEN_DETAIL_IP = "remoteAddress";

    private static final String TOKEN_DETAIL_SESSION_ID = "sessionId";

    private static final String TOKEN_DETAIL_TYPE = "tokenType";

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication)
    {
        final Map<String,Object> additionInfo = Maps.newHashMap();

        // 要添加的额外信息
        //authentication.getUserAuthentication().getDetails();

        UserDetails user = (UserDetails) authentication.getUserAuthentication().getPrincipal();
        String username = user.getUsername();
        // Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // todo 目前只增加了用户名和权限 后期可以根据业务加
        additionInfo.put("username",username);
        // additionInfo.put("authority",authorities);

        DefaultOAuth2AccessToken oAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
        oAuth2AccessToken.setAdditionalInformation(additionInfo);
        return oAuth2AccessToken;
    }
}
