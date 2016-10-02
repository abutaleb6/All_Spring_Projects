package com.bepza.mis.cp.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bepza.mis.cp.model.Employee;

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
      Employee employee = (Employee) target;

//       target.getClass();
      System.out.println("66767"+employee.getEmpName());
      if(employee.getEmpName().equals("")) {
    	  System.out.println("custom validateion: "+target.getClass());
//          errors.rejectValue("empName", "name.required", "Helloddd");
          errors.rejectValue("empName", "prop.fieldName.label",new String[] {"1234"}, null);
      }  
//      else
/*    	  if(employeeB.getName().length() < 2 || employeeB.getName().length() > 3){
          errors.rejectValue("name", "name[invalidLength]");
      }*/

      // do "complex" validation here

    }

}