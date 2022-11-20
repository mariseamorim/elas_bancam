package com.elasbancam;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// A documentação feita pelo Swagger pode ser consultada pelo link a seguir, desde que o programa esteja
// rodando: http://localhost:8080/swagger-ui/index.html#

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Api de ElasBancam", version = "1.0", description = "API do  Projeto Integrador ElasTech - ElasBancam"),
		servers = {
				@Server(url = "http://localhost:8080")

		}
)

public class ElasbancamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasbancamApplication.class, args);
	}

}
