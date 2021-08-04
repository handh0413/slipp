package net.slipp.user;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserValidationTest {
	private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Test
    public void manufacturerIsNull() {
        User user = new User(null, "1234", "한동희", "");

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertEquals(1, constraintViolations.size());
        // assertEquals("must not be null", constraintViolations.iterator().next().getMessage() );
        System.out.println(constraintViolations.iterator().next().getMessage());
    }
    
    @Test
	public void userIdLength() throws Exception {
    	User user = new User("donghee.han", "12", "한동희", "");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals(1, constraintViolations.size());
        System.out.println(constraintViolations.iterator().next().getMessage());
        
        user = new User("donghee.han12345", "12345", "한동희", "");
        constraintViolations = validator.validate(user);
        assertEquals(1, constraintViolations.size());
        System.out.println(constraintViolations.iterator().next().getMessage());
	}
    
    @Test
	public void email() throws Exception {
    	User user = new User("donghee.han", "1234", "한동희", "email");
    	Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals(1, constraintViolations.size());
        System.out.println(constraintViolations.iterator().next().getMessage());
	}
    
    @Test
	public void invalidateUser() throws Exception {
    	User user = new User("do", "1234", "name", "email");
    	Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals(2, constraintViolations.size());
        
        Iterator<ConstraintViolation<User>> violations = constraintViolations.iterator();
        while (violations.hasNext()) {
        	ConstraintViolation<User> each = violations.next();
        	System.out.println(each.getPropertyPath() + " : " + each.getMessage());
        }
	}
}
