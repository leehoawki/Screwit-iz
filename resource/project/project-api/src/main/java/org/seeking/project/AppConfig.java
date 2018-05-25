package org.seeking.{{ project }};

import org.seeking.{{ project }}.interceptor.LogInterceptor;
import org.seeking.{{ project }}.security.CurrentUserHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class AppConfig extends WebMvcConfigurerAdapter {
    @Bean
    public Docket docket() {
        ParameterBuilder builder = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        pars.add(builder.name("authorization").description("authorization").modelRef(new ModelRef("string")).parameterType("header").required(false).build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().title("{{ project }}").build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.seeking.{{ project }}"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("{{ project }}").build();
    }

    @Autowired
    CurrentUserHandlerMethodArgumentResolver currentUserHandlerMethodArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserHandlerMethodArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/*swagger-**")
                .excludePathPatterns("/*swagger-**/**")
                .excludePathPatterns("/**/api-docs");
    }
}
