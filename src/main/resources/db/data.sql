INSERT INTO AUTHORITY(id,version,authority) VALUES (1,0,'ADMIN');
INSERT INTO USER(username,password) VALUES ('admin','$2a$10$i20jFJvEBpTaNXO36Th1pO5WHoc8nBSGcanDVueTPUwapMlXnqvnW');
INSERT INTO AUTHORITIES_USERS(user_id,authority_id) VALUES ('admin',1);
INSERT INTO ADMIN(id,version,avatar,email,name,phone_number,surname,username) VALUES (2,0,'https://vignette.wikia.nocookie.net/cute-doge/images/a/af/Fat_Doge.jpg/revision/latest/top-crop/width/450/height/450?cb=20160220123037','admin@tabernassevilla.com','Elba','123456789','Sado','admin');