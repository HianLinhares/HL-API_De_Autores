package Hian.Linhares.HL_API_De_Autores.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Autores",
                version = "v1",
                contact = @Contact(
                        name = "Hian Linnhares",
                        email = "hianfernando@hotmail.com",
                        url = "https://www.linkedin.com/in/hian-linhares-3567241b3/"
                )
        )
)
public class OpenApiConfiguration {
}
