create database bloodbank;

use bloodbank;

create table bloodbank_reg_table
(
S_NO INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(20) NOT NULL,
  bloodgroup VARCHAR(20) NOT NULL,
  gender VARCHAR(20) NOT NULL,
  dob date NOT NULL,
  age int NOT NULL,
  phone_number VARCHAR(20) NOT NULL,
  address text,
  state VARCHAR(100) NOT NULL,
  city VARCHAR(100) NOT NULL,
  PRIMARY KEY (S_NO)
  );