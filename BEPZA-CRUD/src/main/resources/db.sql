CREATE SEQUENCE company_seq start with 1 increment by 1 nocache;
CREATE TABLE company (
  id NUMBER(10) PRIMARY KEY,
  name VARCHAR2(100),
  zone VARCHAR2(100),
  investment VARCHAR2(100),
  employment VARCHAR2(100)  
);