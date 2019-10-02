package com.mk.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import javax.annotation.Resource;

/**
 * @Author liumingkang
 * @Date 2019-10-02 19:41
 * @Destcription 授权服务配置
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

    @Qualifier("authenicationManager")
    private AuthenticationManager authenticationManager;

    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    /*
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
                .checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
    }

    /*
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
        // 暂时使用内存方式存储密码的方式
        clients.inMemory().withClient("MiscorService")  // 客户端的唯一ID
        .authorizedGrantTypes("authorization_code","refresh_token")  // 授权码模式
        .scopes("test")  // 授权范围 test
        .secret("123456") // 不需要加密了
        .redirectUris("http://www.baidu.com"); // 客户端与授权服务器的安全码
    }

    /*
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
        endpoints.authenticationManager(authenticationManager)  // 放置鉴权管理器
                .tokenStore(generateToken())    // 配置token的store
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .userDetailsService(userDetailsService);   // 必须配置user信息获取服务 目前使用默认生成的user 后期改为DB读取
    }


    /*
    *
     * @Author liumingkang
     * @Description 用于生成token 仅限于内存中时候使用 用于Demo
     * @Date 19:53 2019-10-02
     * @Param []
     * @return org.springframework.security.oauth2.provider.token.TokenStore
     **/
    @Bean
    public TokenStore generateToken()
    {
        return new InMemoryTokenStore();
    }
}
