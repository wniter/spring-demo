package com.example.springboot.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 在Springboot启动类的同级目录下面创建一个config的包，然后创建一个配置Swagger2 的配置类。
 * 通过bean的注解注入
 *
 * @create 2022-03-06 16:01
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //一定要这个，才能构建
                .select()
                // 指定要生成api接口的包路径
//                .apis(RequestHandlerSelectors.basePackage("com.example.spring.swagger.controller"))
                //使用了 @ApiOperation 注解的方法生成api接口文档
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                //可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build();


    }


    //apiInfo() 方法里面的参数可以自己设定，在第一个方法中，我们除了可以根据接口所在的包对应生成接口文档还可以根据项目中是否有方法使用了
    // @ApiOperation注解来判断是否生成api文档。
    //设置api文档信息，通过ApiInfoBuilder构建
    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                //标题
                .title("springboot 集成 swagger2")
                //接口描述
                .description("swagger")
                //联系方式
                .contact("联系方式")
                //版本信息
                .version("1.0")
                //构建 这个很重要
                .build();
    }
}
