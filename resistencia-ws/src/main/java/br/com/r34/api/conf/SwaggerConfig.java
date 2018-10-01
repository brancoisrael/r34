package br.com.r34.api.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	static final String DESCRIPTION = "Resistencia WS - arquitetura de microservicos";

	@Bean
	public Docket apiLOC() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.r34.api.controller"))
				.paths(PathSelectors.any())
				.build().apiInfo(apiLOCInfo());
	}

	private ApiInfo apiLOCInfo() {
		return new ApiInfoBuilder().title("Visão Geral").description(DESCRIPTION).build();
	}

}
