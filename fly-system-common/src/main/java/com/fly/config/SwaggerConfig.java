package com.fly.config;

import cn.hutool.core.collection.CollUtil;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * swagger配置类
 * </p>
 *
 * @author Fly
 * @since 2022-05-19
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static List<String> swaggerControllerPackage = CollUtil.newArrayList(
            "com.fly.admin.controller"
    );

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .apis(Predicates.or(swaggerControllerPackage.stream().map(z -> RequestHandlerSelectors.basePackage(z)).collect(Collectors.toList())))
                .build()
                .securityContexts(securityContexts()) // 开启swagger认证 token
                .securitySchemes(securitySchemes()); // 开启swagger认证 token;
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Fly System API Doc")
                .description("Fly System API文档")
                .contact(new Contact("Fly", "", "dzp961207@163.com"))
                .version("1.0")
                .build();
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth()).build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }

}