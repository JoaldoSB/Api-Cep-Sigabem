package br.com.sigabem.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	// url de acesso http://localhost:8080/swagger-ui/index.html#/

	@Bean
	public Docket detalheApi() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2);

		docket.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.sigabem"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(this.informacoesApi().build());

		return docket;
	}

	private ApiInfoBuilder informacoesApi() {
		 
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
 
		apiInfoBuilder.title("Teste de seleção para vaga de Desenvolverdor Java Jr");
		apiInfoBuilder.description("Api do endpoint para o cálculo do preço do frete para empresa de transporte de cargas SigaBem.");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.build();
 
		return apiInfoBuilder;
	}
	
}
