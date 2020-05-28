package com.promad.msi.listaplacas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import feign.Client;
import feign.Logger;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
public class ListaplacasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListaplacasApplication.class, args);
	}
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).select()
				.apis(RequestHandlerSelectors.basePackage("com.promad.msi.listaplacas.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Lista de placas API")
				.description("Microservicio para dar de alta Placas en listas").version("1.0")
				.contact(new Contact("Victor Santana", "https://www.promad.com.mx/", "vsantana@promad.com.mx")).build();
	}


//	@Bean
//    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
//        return new BasicAuthRequestInterceptor("Pr0m4d", "zvsjiVUtuTOIAEQFHK7lkA==");
//    }
	@Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
	@Bean
	public Client feignClient() {
	    return new Client.Default(null, null);
	}
}
