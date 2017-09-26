package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
	
	private final String NAME = "Michal Mikla";
	private final String URL = "https://github.com/misiom94";
	private final String EMAIL = "misiom94@gmail.com";
	
	@Bean
	public Docket api() {                
    		return new Docket(DocumentationType.SWAGGER_2)          
      			.select()
      			.apis(RequestHandlerSelectors.basePackage("hello"))
      			.build()
      			.apiInfo(apiInfo());
	}
	
	private Contact contact = new Contact(NAME, URL, EMAIL);
	
	private ApiInfo apiInfo() {
    		ApiInfo apiInfo = new ApiInfo(
      			"Michal Mikla's REST API",
      			"",
      			"API TOS",
      			"Terms of service",
      			contact,
      			"License of API",
      			"API license URL");
    		return apiInfo;
	}
	
	

}
