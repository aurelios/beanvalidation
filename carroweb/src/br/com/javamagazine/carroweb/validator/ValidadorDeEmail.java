package br.com.javamagazine.carroweb.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { ChecaEmail.class })
@Documented
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.PARAMETER,
		ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidadorDeEmail {

	String message() default "{erro.validacao.email.invalido}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
