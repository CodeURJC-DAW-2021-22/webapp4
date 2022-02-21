DROP TABLE IF EXISTS USER;
 
CREATE TABLE USER (
  ID_USER IDENTITY NOT NULL PRIMARY KEY,
  FULL_NAME VARCHAR(250) NOT NULL,
  PASSWORD VARCHAR(256) NOT NULL,
  EMAIL VARCHAR(250) NOT NULL,
  TEL VARCHAR(9) NOT NULL,
  N_SOLD INT DEFAULT 0,
  N_SELL INT DEFAULT 0,
  IS_ADMIN BIT DEFAULT 0
);

DROP TABLE IF EXISTS ARTICLE;

CREATE TABLE ARTICLE (
    ID_ARTICLE INT AUTO_INCREMENT PRIMARY KEY,
    ID_USER INT,
    TITLE VARCHAR(250) NOT NULL,
    DESCRIPTION VARCHAR(MAX),
    PRICE INT NOT NULL,
    RESERVED BIT DEFAULT 0,
    SOLD BIT DEFAULT 0,
    PHOTO BLOB,
    N_VISITED INT DEFAULT 0,
    FOREIGN KEY (ID_USER) REFERENCES USER(ID_USER)
);
 
