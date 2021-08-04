package net.slipp.util;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class MyValidatorFactory {
	private static Validator validator;
	
	public static Validator createValidator() {
		if (validator == null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			validator = factory.getValidator();
		}
		return validator;
	}

}
