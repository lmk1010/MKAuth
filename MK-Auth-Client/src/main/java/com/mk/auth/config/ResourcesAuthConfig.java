package com.mk.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Author liumingkang
 * @Date 2019-10-03 12:05
 * @Destcription 资源配置
 * @Version 1.0
 **/
@Configuration
@EnableResourceServer
public class ResourcesAuthConfig extends ResourceServerConfigurerAdapter
{
    public ResourcesAuthConfig() {
        super();
    }

    /**
    *
     * @Author liumingkang
     * @Description 配置同样的jwt token key
     * @Date 12:07 2019-10-03
     * @Param []
     * @return org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
     **/
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter()
    {
        final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("testkey");
        return jwtAccessTokenConverter;
    }

    /**
    *
     * @Author liumingkang
     * @Description 设置生成token
     * @Date 12:07 2019-10-03
     * @Param []
     * @return org.springframework.security.oauth2.provider.token.TokenStore
     **/
    @Bean
    public TokenStore generateToken()
    {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
    *
     * @Author liumingkang
     * @Description 修改默认token的策略
     * @Date 12:08 2019-10-03
     * @Param [resources]
     * @return void
     **/
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception
    {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(generateToken());

        resources.tokenServices(defaultTokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
