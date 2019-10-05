package com.mk.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.*;

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
        // 对称加密时候 jwtAccessTokenConverter.setSigningKey("testkey");
        // 非对称加密 需要把公钥的存储读出
        String publicKey = "";
        Resource publicKeyRes = new ClassPathResource("public.txt");

        try {
            publicKey = inputStreamFromFileToString(publicKeyRes.getInputStream());
        } catch (IOException e) {
            // todo 改为logger 统一异常类
            e.printStackTrace();
        }
        // todo 校验publickey的


        jwtAccessTokenConverter.setVerifierKey(publicKey);
        return jwtAccessTokenConverter;
    }

    /**
    *
     * @Author liumingkang
     * @Description 将文件里的数据读出转为字符串
     * @Date 14:44 2019-10-03
     * @Param [io]
     * @return java.lang.String
     **/
    private String inputStreamFromFileToString(InputStream io) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(io));
        StringBuffer sb = new StringBuffer();
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
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
