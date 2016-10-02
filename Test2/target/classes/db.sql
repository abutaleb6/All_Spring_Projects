 create table employee_mst (
 employee_mst_id number(10)  PRIMARY KEY,
  emp_name varchar2(50),
  address varchar2(50),
  gender varchar2(50),
  dob varchar2(50),
  languages varchar2(50),
  designation varchar2(50)
  ) 
  create sequence employee_mst_seq;
  
  CREATE OR REPLACE TRIGGER  employee_mst_trig
  before insert on employee_mst              
  for each row  
begin   
  if :NEW.employee_mst_id is null then 
    select employee_mst_seq.nextval into :NEW.employee_mst_id from dual; 
  end if; 
end;

create table employee_edu_dtl (
  employee_edu_dtl_id number(10)  PRIMARY KEY,
  employee_mst_id number(10),
  edu_name varchar2(50),
  passing_year varchar2(50),
  sort_order varchar2(50),
  CONSTRAINT fk_column
    FOREIGN KEY (employee_mst_id)
    REFERENCES employee_mst (employee_mst_id)
  )            
  
    create sequence employee_edu_dtl_seq;
  
  CREATE OR REPLACE TRIGGER  employee_edu_dtl_trig
  before insert on employee_edu_dtl              
  for each row
begin   
  if :NEW.employee_edu_dtl_id is null then 
    select employee_edu_dtl_seq.nextval into :NEW.employee_edu_dtl_id from dual; 
  end if; 
end;


INSERT INTO employee_mst (emp_name, address, gender, dob, languages, designation) 
values('abu', 'dhk', 'male','01-09-14', 'en' , 'CEO')