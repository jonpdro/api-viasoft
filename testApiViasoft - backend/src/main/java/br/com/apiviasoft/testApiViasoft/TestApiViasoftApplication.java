package br.com.apiviasoft.testApiViasoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class TestApiViasoftApplication {

	// Redirecionamento do "localhost:8080/" para o Swagger
	@GetMapping
	public ModelAndView swaggerUi() {
		return new ModelAndView("redirect:/swagger-ui/");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TestApiViasoftApplication.class, args);
	}

}
