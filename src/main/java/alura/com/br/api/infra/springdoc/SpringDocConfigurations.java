package alura.com.br.api.infra.springdoc;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()

                .info(new Info()
                        .title("Alura's Api")
                        .description("The project is a technical system for the Alura platform, with the objective of managing users, courses and enrollments, in addition to implementing a course evaluation system.")
                        .contact(new Contact()
                                .name("Time Backend")));

    }


}

