package br.com.albert.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTful API")
						.version("v1")
						.description("A Spring RESTful API")
						.termsOfService("https://albert.com/termsofservice")
						.license(
								new License()
										.name("Apache 2.0")
										.url("albert.com//licence")
										)
						);
	}

}
