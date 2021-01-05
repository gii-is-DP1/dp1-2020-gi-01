INSERT INTO AUTHORITY(id,version,authority) VALUES (1,0,'ADMIN');
INSERT INTO AUTHORITY(id,version,authority) VALUES (2,0,'MANAGER');
INSERT INTO AUTHORITY(id,version,authority) VALUES (3,0,'COOK');
INSERT INTO AUTHORITY(id,version,authority) VALUES (4,0,'WAITER');
INSERT INTO AUTHORITY(id,version,authority) VALUES (5,0,'CUSTOMER');
INSERT INTO USER(username,password) VALUES ('admin','$2a$10$i20jFJvEBpTaNXO36Th1pO5WHoc8nBSGcanDVueTPUwapMlXnqvnW');
INSERT INTO AUTHORITIES_USERS(user_id,authority_id) VALUES ('admin',1);
INSERT INTO ADMIN(id,version,avatar,email,name,phone_number,surname,username) VALUES (1,0,'https://vignette.wikia.nocookie.net/cute-doge/images/a/af/Fat_Doge.jpg/revision/latest/top-crop/width/450/height/450?cb=20160220123037','admin@tabernassevilla.com','Elba','123456789','Sado','admin');

INSERT INTO ESTABLISHMENT(id,version,title,description,picture,capacity,current_capacity,phone,opening_hours,score) VALUES (1,0,'Ristaurante 1','This is a test','https://coubsecure-s.akamaihd.net/get/b19/p/coub/simple/cw_timeline_pic/3c12a8d5ec4/2d1832d15b1fc6fdd341e/med_1584781416_image.jpg',10,10,'123456788','8:30 - 18:00',3);
INSERT INTO ESTABLISHMENT(id,version,title,description,picture,capacity,current_capacity,phone,opening_hours,score) VALUES (2,0,'Arenal','Test full tavern','https://coubsecure-s.akamaihd.net/get/b19/p/coub/simple/cw_timeline_pic/3c12a8d5ec4/2d1832d15b1fc6fdd341e/med_1584781416_image.jpg',10,0,'123456788','8:30 - 18:00',5);

INSERT INTO RESTAURANT_TABLE(id,version,establishment_id,number,seating,occupied,hour_seated) VALUES (1,0,1,1,8,0,null);
INSERT INTO RESTAURANT_TABLE(id,version,establishment_id,number,seating,occupied,hour_seated) VALUES (2,0,1,2,9,0,null);
INSERT INTO RESTAURANT_TABLE(id,version,establishment_id,number,seating,occupied,hour_seated) VALUES (3,0,1,3,10,0,null);