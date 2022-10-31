package br.com.mjv.projeto;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProjetoMjvApplication {

//	@Autowired
//	@Qualifier("applicationName") // anotação para identificar que ele injete essa String nessa variavel abaixo
	@Value("${application.name}")
	private String applicationName;

	@GetMapping("/ola")
	public String ola() {
		return applicationName;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoMjvApplication.class, args);
	}
}
