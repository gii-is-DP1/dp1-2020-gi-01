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


INSERT INTO ESTABLISHMENT(id,version,title,description,picture,capacity,current_capacity,phone,opening_hours,score,address) VALUES (1,0,'Ristaurante 1','This is a test','https://image.tmdb.org/t/p/w780/nRBGsgG68OmgsZWaRbS5vjjsOBu.jpg',10,10,'123456788','8:30 - 18:00',3,'C/ Address 1');
INSERT INTO ESTABLISHMENT(id,version,title,description,picture,capacity,current_capacity,phone,opening_hours,score,address) VALUES (2,0,'Arenal','Test full tavern','https://image.tmdb.org/t/p/w780/nRBGsgG68OmgsZWaRbS5vjjsOBu.jpg',10,0,'123456788','8:30 - 18:00',5,'C/ Address 1');
INSERT INTO ESTABLISHMENT(id,version,title,description,picture,capacity,current_capacity,phone,opening_hours,score,address) VALUES (3,0,'McDonalds','This is a test','https://image.tmdb.org/t/p/w780/nRBGsgG68OmgsZWaRbS5vjjsOBu.jpg',10,10,'123456788','8:30 - 18:00',4,'C/ Address 1');

INSERT INTO RESTAURANT_TABLE(id,version,establishment_id,number,seating,occupied,hour_seated) VALUES (1,0,1,1,8,0,null);
INSERT INTO RESTAURANT_TABLE(id,version,establishment_id,number,seating,occupied,hour_seated) VALUES (2,0,1,2,9,0,null);
INSERT INTO RESTAURANT_TABLE(id,version,establishment_id,number,seating,occupied,hour_seated) VALUES (3,0,1,3,10,0,null);