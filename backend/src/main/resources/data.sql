INSERT INTO USER ( FULL_NAME , PASSWORD , EMAIL , TEL , IS_ADMIN ) VALUES ('ADMIN', '1234', 'ADMIN@ADMIN.COM', '666666666', 1);
INSERT INTO USER ( FULL_NAME , PASSWORD , EMAIL , TEL ) VALUES ('JESUS', '1234', 'J@J.COM', '666666466');
INSERT INTO ARTICLE ( ID_USER , TITLE , DESCRIPTION , PRICE ) VALUES (2, 'Moto como nueva ', 'Vendo moto utilizada solo fines de semana. Mantenimiento al día', 3500);
INSERT INTO CATEGORY ( TITLE , DESCRIPTION ) VALUES ('MOTOS', 'MOTOCICLETAS');
INSERT INTO CATEGORY ( TITLE , DESCRIPTION ) VALUES ('OCASIÓN', 'POCO USO, MENOS DE 1000KM');
INSERT INTO ARTICLE_CATEGORY ( ID_ARTICLE , ID_CATEGORY ) VALUES (1, 1);
INSERT INTO ARTICLE_CATEGORY ( ID_ARTICLE , ID_CATEGORY ) VALUES (1, 2);
INSERT INTO REPORT ( DESCRIPTION , EMAIL , ID_ARTICLE ) VALUES ('Esa moto me la robaron hace un mes. Adjunto la denuncia', 'j@j.com', 1);
INSERT INTO FAVORITES ( ID_ARTICLE , ID_USER ) VALUES (1, 1);