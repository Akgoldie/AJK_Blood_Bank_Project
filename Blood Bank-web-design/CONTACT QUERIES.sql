
use bloodbank;

CREATE table CONTACT_TABLE(
S_NO INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  subject VARCHAR(20) NOT NULL,
  message text,
  PRIMARY KEY (S_NO)

);

SELECT * FROM bloodbank.contact_table;