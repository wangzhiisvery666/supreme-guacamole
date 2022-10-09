package ccut.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

 @Configuration
 @EnableWebMvc
 public class SwaggerConfig implements WebMvcConfigurer
 {


   @Bean
   public Docket admin_api() {
     return (new Docket(DocumentationType.OAS_30))
       .apiInfo(apiInfo2())
       .select()
       .apis(RequestHandlerSelectors.basePackage("ccut.controllerManage"))

       .build()
       .groupName("官方管理系统接口");
   }

     @Bean
     public Docket store_api() {
         return (new Docket(DocumentationType.OAS_30))
                 .apiInfo(apiInfo3())
                 .select()
                 .apis(RequestHandlerSelectors.basePackage("ccut.controllerStore"))

                 .build()
                 .groupName("商家管理系统接口");
     }

   @Bean
   public Docket user_api() {
     return (new Docket(DocumentationType.OAS_30))
       .select()
       .apis(RequestHandlerSelectors.basePackage("ccut.controllerUser"))

       .build()
       .groupName("app接口")
       .apiInfo(apiInfo1());
   }

   private ApiInfo apiInfo1() {
     return (new ApiInfoBuilder())
       .title("app网站")
       .description("app网站api")
       .version("1.0")

       .build();
   }
   private ApiInfo apiInfo2() {
     return (new ApiInfoBuilder())
       .title("官方管理系统")
       .description("官方管理系统api")
       .version("1.0")
       .build();
   }

   private ApiInfo apiInfo3() {
         return (new ApiInfoBuilder())
                 .title("商家管理系统")
                 .description("商家管理系统api")
                 .version("1.0")
                 .build();
   }
 }

