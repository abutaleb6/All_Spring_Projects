package com.spring.hibernate.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.spring.hibernate.bean.EmployeeBean;
import com.spring.hibernate.model.Employee;


/**
 * @author msaifullah
 *
 */
/**
 * @author msaifullah
 *
 */
/**
 * @author msaifullah
 *
 */
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
      return Employee.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
      EmployeeBean employeeB = (EmployeeBean) target;

//       target.getClass();
      System.out.println("sss"+employeeB.getName());
      if(employeeB.getName() == "") {
    	  System.out.println(target.getClass());
          errors.rejectValue("name", "name.required", "Helloddd");
      }  
//      else
/*    	  if(employeeB.getName().length() < 2 || employeeB.getName().length() > 3){
          errors.rejectValue("name", "name[invalidLength]");
      }*/

      // do "complex" validation here

    }

}