package br.com.mjv.projeto.configs.annotations.constraint_validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.mjv.projeto.configs.annotations.ListaNaoVazia;

public class ListaNaoVaziaValidator implements ConstraintValidator<ListaNaoVazia, List> {

	@Override
	public void initialize(ListaNaoVazia constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(List value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return value != null && !value.isEmpty();
	}
}
