INSERT INTO `address` VALUES (1,'Лютеранская','2'),
                            (2,'Драгоманова','5'),
                            (3,'Ломоносова','7'),
                            (4,'Урловская','12'),
                            (5,'Богдана Хмельницкого','11'),
                            (6,'Руставели Шота','16а'),
                            (7,'Оболонская пл.','6'),
                            (8,'Липковского','10'),
                            (9,'Тимошенка','1'),
                            (10,'Петровская','25');

INSERT INTO `car` VALUES (1,'BI5522KO','Audi','blue','UNIVERSAL', 'IN_GARAGE', 4),
                         (2,'AA4533AM','BMW','yellow','UNIVERSAL', 'IN_GARAGE', 4),
                         (3,'AA7888HH','Mercedes','red','MINIVAN', 'IN_GARAGE', 7),
                         (4,'BX2093MX','Skoda','black','STANDARD', 'BOOKED', 4),
                         (5,'AA9988AM','BMV','green','COMFORT', 'FREE', 4),
                         (6,'AA5577KO','Seat','black','STANDARD', 'FREE', 4),
                         (7,'AB2378AB','Audi','white','COMFORT', 'FREE', 4);

INSERT INTO `user` VALUES (1,'Александр','4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2', '+380976970444','dddkkk@gmail.com'), /*root*/
                            (2,'Дмитрий','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '+380665557789','admin@gmail.com'), /*admin*/
                            (3,'Николай','daaad6e5604e8e17bd9f108d91e26afe6281dac8fda0091040a7a6d7bd9b43b5','+380961248566','qqq@mail.ru'),/*qwerty123*/
                            (4,'Игорь','e14cb9e5c0eeee0ea313a4e04fbd10aa17ac17aa33a3cad4bdfe74b87ca18ef8', '+380994445566','aasda@gmail.com'),/*root123*/
                            (5,'Сергей','ec74f80aa0c33fa2c5c65c88e741450c482bccacf2c4afef9ac660b3a4281f6b', '+380997895263','aasddd@gmail.com'),/*root124*/
                            (6,'Михаил','9511fba8b8a120750220e263b8dcd2abd5aef297d91c976a31589ef17fde90c9', '+380674478956','aasdfff@gmail.com'),/*root125*/
                            (7,'Григорий','9bfafe5a57165c4b7909adee2dbb42f029dd8204a64df3d40dc010dce31dce05', '+380997854223','aassss@gmail.com');/*root127*/

INSERT INTO `car_driver` VALUES (1, null),
                                (2, null),
                                (3, null),
                                 (4, 4),
                                 (5, 5),
                                 (6, 6),
                                 (7, 7);

INSERT INTO `role` VALUES ('CLIENT', 1),
                          ('ADMIN', 2),
                          ('CLIENT', 3),
                          ('DRIVER', 4),
                          ('DRIVER', 5),
                          ('DRIVER', 6),
                          ('DRIVER', 7);

INSERT INTO `order` VALUES (4,'COMPLETE',1,4,1,5,100,90,4,'2021-05-15'),
                           (5,'COMPLETE',3,5,2,9,100,100,5,'2021-05-25' ),
                           (1,'COMPLETE',1,6,3,10,120,100,6, '2021-04-10'),
                           (6,'COMPLETE',1,7,5,1,120,120,7, '2021-05-26'),
                           (3,'COMPLETE',3,4,1,10,120,100,4, '2021-05-15'),
                           (2,'COMPLETE',3,5,1,5,360,230,5, '2021-05-01'),
                           (7,'COMPLETE',3,5,7,9,260,260,5, '2021-05-29'),
                           (8,'COMPLETE',1,6,7,4,120,100,6, '2021-05-30');