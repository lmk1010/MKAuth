package com.mk.auth.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author liumingkang
 * @Date 2020-02-16 10:26
 * @Destcription api接口生成配置
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config
{
    // swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.mk.auth.core.api")).paths(PathSelectors.any())
                .build();
    }
    // 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("MKAuth 授权 API 接口列表")
                // 创建人信息
                .contact(new Contact("liumingkang",  "https://github.com/lmk1010",  "lmkbnb0@gmail.com"))
                // 版本号
                .version("0.1")
                // 描述
                .description("API 描述")
                .build();
    }
}
