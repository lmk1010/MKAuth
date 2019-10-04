package com.mk.auth.token;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author liumingkang
 * @Date 2019-10-04 09:56
 * @Destcription 主要是负责对于token进行转换，添加一些额外的信息 (目前主要是加入用户的信息)
 *               虽然tokenEnhancer可以加入，但是默认的转换器是直接把额外信息put出去，而在转换器这边可以进去一些改进
 * @Version 1.0
 **/
public class MKTokenConvert extends DefaultAccessTokenConverter
{

    public MKTokenConvert()
    {
        super.setUserTokenConverter(new CustomerUserAuthenticationConverter());
    }

    private class CustomerUserAuthenticationConverter extends DefaultUserAuthenticationConverter {
        @Override
        public Map<String, ?> convertUserAuthentication(Authentication authentication) {
            LinkedHashMap <String, Object> response = new LinkedHashMap <>();
            response.put("details", authentication.getDetails());
            response.put("test","hello");
            if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
                response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
            }
            return response;
        }
    }
}
