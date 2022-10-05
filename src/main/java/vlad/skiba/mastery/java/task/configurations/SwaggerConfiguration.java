package vlad.skiba.mastery.java.task.configurations;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright Vlad Skiba (c) 2022.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        final List<ResponseMessage> globalResponses = Arrays.asList(
                new ResponseMessageBuilder()
                        .code(400)
                        .message("Bad Request")
                        .build(),
                new ResponseMessageBuilder()
                        .code(409)
                        .message("Conflict")
                        .build(),
                new ResponseMessageBuilder()
                        .code(500)
                        .message("Internal Server Error")
                        .build()
        );
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, globalResponses)
                .globalResponseMessage(RequestMethod.POST, globalResponses)
                .globalResponseMessage(RequestMethod.PUT, globalResponses)
                .globalResponseMessage(RequestMethod.DELETE, globalResponses)
                .select()
                .apis(RequestHandlerSelectors.basePackage("vlad.skiba.mastery.java.task.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

}
