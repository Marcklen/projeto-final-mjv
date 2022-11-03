package br.com.mjv.projeto.configs.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.mjv.projeto.configs.annotations.constraint_validator.ListaNaoVaziaValidator;

@Retention(RetentionPolicy.RUNTIME) // Informo que é em tempo de execução
@Target(ElementType.FIELD) // Informo onde será usada a anotação
@Constraint(validatedBy = ListaNaoVaziaValidator.class) // Informo que é uma anotação de validação
public @interface ListaNaoVazia {
	
	//quando se cria anotações personalizadas tem que ser definido 3 metodos padroes (message, groups e o payload)
	String message() default "Não foi possível realizar um pedido, a lista de itens está vazia!";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}