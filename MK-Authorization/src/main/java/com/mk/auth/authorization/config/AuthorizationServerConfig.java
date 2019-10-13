package com.mk.auth.authorization.config;

import com.mk.auth.authorization.custom.AuthClientDetailsService;
import com.mk.auth.authorization.custom.AuthUserDetailsService;
import com.mk.auth.authorization.token.MKTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @Author liumingkang
 * @Date 2019-10-02 19:41
 * @Destcription 授权服务配置 JWT改造
 * @Version 1.0
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter
{
    @Bean
    public BCryptPasswordEncoder encodeHelper()
    {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public AuthenticationManager authenticationManager;

    @Resource(name = "authUserDetailService")
    private AuthUserDetailsService authUserDetailsService;

    @Resource(name = "authClientDetailsService")
    private AuthClientDetailsService authClientDetailsService;

    /**
    *
     * @Author liumingkang
     * @Description 配置令牌的安全约束
     * @Date 20:23 2019-10-02
     * @Param [security]
     * @return void
     **/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception
    {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    /**
    *
     * @Author liumingkang
     * @Description 配置客户端的信息 主要用于和授权服务器的交互配置  包括密码等
     * @Date 19:54 2019-10-02
     * @Param [clients]
     * @return void
     **/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception
    {
        clients.withClientDetails(authClientDetailsService);
    }

    /**
    *
     * @Author liumingkang
     * @Description 配置令牌的访问服务 和 令牌服务
     * @Date 20:20 2019-10-02
     * @Param [endpoints]
     * @return void
     **/
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
    {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(mkTokenEnhancer(),jwtAccessTokenConverter()));

        endpoints.authenticationManager(authenticationManager)  // 放置鉴权管理器
                .tokenStore(generateToken())    // 配置token的store
                .accessTokenConverter(jwtAccessTokenConverter())    // 配置JWT的转换器
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//                .tokenEnhancer(tokenEnhancerChain)   // 添加token的额外的信息
                .userDetailsService(authUserDetailsService)
                .setClientDetailsService(authClientDetailsService);   // 必须配置user信息获取服务 目前使用默认生成的user 后期改为DB读取
    }


    /**
    *
     * @Author liumingkang
     * @Description //TODO JWT生成token
     * @Date 11:53 2019-10-03
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
     * @Description token增加信息
     * @Date 08:10 2019-10-04
     * @Param []
     * @return org.springframework.security.oauth2.provider.token.TokenEnhancer
     **/
    @Bean
    public TokenEnhancer mkTokenEnhancer()
    {
        return new MKTokenEnhancer();
    }


    /**
    *
     * @Author liumingkang
     * @Description JWT的转换器
     * @Date 11:51 2019-10-03
     * @Param []
     * @return org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
     **/
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter()
    {
        final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // 之前使用对称加密的方式
        jwtAccessTokenConverter.setSigningKey("testkey");
        // 现在使用非对称加密 读取jks文件 找到密码
        // KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"),"mypass".toCharArray());
        // 放置公钥私钥
        // jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
        // fixme 修改默认的转换器
        // jwtAccessTokenConverter.setAccessTokenConverter(new MKTokenConvert());
        return jwtAccessTokenConverter;
    }


}
