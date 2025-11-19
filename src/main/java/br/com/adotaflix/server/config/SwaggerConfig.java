package br.com.adotaflix.server.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;


@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI openApiConfig() {
	    return new OpenAPI()
	            .info(new Info()
	                    .title("Adotaflix API") 
	                    .description("Api referente ao Adotaflix") 
	                    .version("v1") 
	            )
	            .servers(Collections.singletonList(new Server().url("/api")))
	            .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("Authorization")
                                .description("JWT token")
                                .in(SecurityScheme.In.HEADER)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
	            .security(Collections.singletonList(new SecurityRequirement().addList("bearerAuth",  
	            		 Collections.emptyList())));
	}
	
}
