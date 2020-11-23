-- One admin user, named estsvq with passwor admin and authority admin
INSERT INTO users(username,password,enabled) VALUES ('estsvq','admin',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'estsvq','admin');