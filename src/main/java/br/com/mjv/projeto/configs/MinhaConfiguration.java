package br.com.mjv.projeto.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// classe para especificação de configurações como banco de dados, email e customizações do tipo
@Configuration
public class MinhaConfiguration {

	// Aqui eu informo ao springboot que ele crie esse Bean (objeto String) no
	// contexto da aplicação
	@Bean(name = "applicationName")
	public String applicationName() {
		return "<h2><center>Sistema de Vendas MJV - Versão 1.0";
	}
}