create table `user`(id int, name varchar(20) );
insert into `user` values(10000, 'moon');
insert into `user` values(10001, 'sui');
create table  items(id int, uid int, itype int);
insert  into items values (1, 10000, 1);
insert  into items values (2, 100001, 1);
insert  into items values (2, 100001, 1);
create table  goods(id int, des varchar(64) );
insert  into goods values (1, '1|100|100');
insert  into goods values (2, '2|25|100');
insert  into goods values (3, '1||100|50;2|25|75');