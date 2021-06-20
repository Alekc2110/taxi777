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
                            (7,'Григорий','9bfafe5a57165c4b7909adee2dbb42f029dd8204a64df3d40dc010dce31dce05', '+380997854223','aassss@gmail.com'),/*root127*/
                            (8,'Анна','073a5d226acb588f2746761cbca0f046ad56b81155fa0d2cecd2a53102226a3d', '+380675575676','a_b_s@mail.ua'),/*root111*/
                            (9,'Александр','821a317ba65d56d7ce4cd6124178f8a5497214a8383d4f6346736c3075d03194', '+380504268686','ooo.granum@mail.ru');/*8686*/


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
                          ('DRIVER', 7),
                          ('CLIENT', 8),
                          ('CLIENT', 9);

INSERT INTO `order` VALUES (4,'COMPLETE',1,4,'Лютеранская,2','Драгоманова,5',100,4,'2021-05-15 22:10','17550'),
                           (5,'COMPLETE',3,5,'Ломоносова,7','Богдана Хмельницкого,11',100,5,'2021-05-25 21:15', '15000' ),
                           (1,'COMPLETE',1,6,'Урловская,12','Оболонская пл.,6',120,6, '2021-04-10 21:25', '10580'),
                           (6,'COMPLETE',1,7,'Липковского,10','Тимошенка,1',120,7, '2021-05-26 20:10', '11250'),
                           (3,'COMPLETE',3,4,'Руставели Шота,16а','Петровская,25',120,4, '2021-05-15 19:35', '9500'),
                           (2,'COMPLETE',3,5,'Ломоносова,7','Лютеранская,10',360,5, '2021-05-01 18:52', '5500'),
                           (7,'COMPLETE',3,5,'Богдана Хмельницкого,77','Тимошенка,10',260,5, '2021-05-29 14:15', '7800'),
                           (8,'COMPLETE',1,6,'Урловская,42','Петровская,10',120,6, '2021-05-30 12:15', '12500');

INSERT INTO discount VALUES (1, 8, 5, 5550),
                            (2, 9, 10, 10850),
                            (3, 1, 0, 1500);