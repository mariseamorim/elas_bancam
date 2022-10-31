package com.elasbancam;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Api de ElasBancam", version = "3.0.1", description = "API do  Projeto Integrador "),
		servers = {
				@Server(url = "http://localhost:8080")

		}
)

public class ElasbancamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasbancamApplication.class, args);
	}

}
