package upeu.edu.pe.employees;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}

	@Bean
	public OpenAPI springEmployeeOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Employees API")
						.description("Esta es la Documentacion de la API de employees trabajada en la clase de LP2.")
						.version("v0.0.1")
						.license(new License().name("MIT").url("https://opensource.org/licenses/MIT")));
	}

}
