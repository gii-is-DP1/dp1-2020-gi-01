INSERT INTO AUTHORITY(id,version,authority) VALUES (1,0,'ADMIN');
INSERT INTO AUTHORITY(id,version,authority) VALUES (2,0,'MANAGER');
INSERT INTO AUTHORITY(id,version,authority) VALUES (3,0,'COOK');
INSERT INTO AUTHORITY(id,version,authority) VALUES (4,0,'WAITER');
INSERT INTO AUTHORITY(id,version,authority) VALUES (5,0,'CUSTOMER');

INSERT INTO USER(username,password) VALUES ('admin','$2a$10$i20jFJvEBpTaNXO36Th1pO5WHoc8nBSGcanDVueTPUwapMlXnqvnW');
INSERT INTO USER(username,password) VALUES ('guaito1','$2a$10$i20jFJvEBpTaNXO36Th1pO5WHoc8nBSGcanDVueTPUwapMlXnqvnW');
INSERT INTO USER(username,password) VALUES ('guaito2','$2a$10$i20jFJvEBpTaNXO36Th1pO5WHoc8nBSGcanDVueTPUwapMlXnqvnW');

INSERT INTO AUTHORITIES_USERS(user_id,authority_id) VALUES ('admin',1);
INSERT INTO AUTHORITIES_USERS(user_id,authority_id) VALUES ('guaito1',5);
INSERT INTO AUTHORITIES_USERS(user_id,authority_id) VALUES ('guaito2',5);

INSERT INTO ADMIN(id,version,avatar,email,name,phone_number,surname,username) VALUES (1,0,'https://vignette.wikia.nocookie.net/cute-doge/images/a/af/Fat_Doge.jpg/revision/latest/top-crop/width/450/height/450?cb=20160220123037','admin@tabernassevilla.com','Elba','123456789','Sado','admin');
INSERT INTO CUSTOMER(id,version,avatar,email,name,phone_number,surname,username) VALUES (2,0,'https://vignette.wikia.nocookie.net/cute-doge/images/a/af/Fat_Doge.jpg/revision/latest/top-crop/width/450/height/450?cb=20160220123037','admin@tabernassevilla.com','Elba','123456789','Sado','guaito1');
INSERT INTO CUSTOMER(id,version,avatar,email,name,phone_number,surname,username) VALUES (3,0,'https://vignette.wikia.nocookie.net/cute-doge/images/a/af/Fat_Doge.jpg/revision/latest/top-crop/width/450/height/450?cb=20160220123037','admin@tabernassevilla.com','Elba','123456789','Sado','guaito2');


INSERT INTO ESTABLISHMENT(id,version,title,description,picture,phone,opening_hours,score,address) VALUES (1,0,'Porvenir','','https://www.arengalia.es/images/porvenir/IMG_8301.jpg','954 622 164','10:00 - 23:59',5,'C/ Address Porvenir');
INSERT INTO ESTABLISHMENT(id,version,title,description,picture,phone,opening_hours,score,address) VALUES (2,0,'Arenal','','https://elcorreoweb.es/documents/10157/0/image_content_17122123_20150704213539.jpg','954 622 164','10:00 - 23:59',5,'C/ Address Arenal');
INSERT INTO ESTABLISHMENT(id,version,title,description,picture,phone,opening_hours,score,address) VALUES (3,0,'Nervion','','https://www.arengalia.es/images/nervion/IMG_6866.jpg','954 622 164','10:00 - 23:59',5,'C/ Address Nervion');

INSERT INTO RESTAURANT_TABLE(id,version,establishment_id,number,seating,occupied,hour_seated) VALUES (1,0,1,1,8,0,null);
INSERT INTO RESTAURANT_TABLE(id,version,establishment_id,number,seating,occupied,hour_seated) VALUES (2,0,1,2,9,0,null);
INSERT INTO RESTAURANT_TABLE(id,version,establishment_id,number,seating,occupied,hour_seated) VALUES (3,0,1,3,10,0,null);