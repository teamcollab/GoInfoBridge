CREATE TABLE  TEST_TABLE (Code varchar(100) NOT NULL, Name varchar(100), Note varchar(100), PRIMARY KEY (Code));

insert into TEST_TABLE(Code, Name, Note) values ('A001', 'A001', null);
insert into TEST_TABLE(Code, Name, Note) values ('A002', 'A002', null);

CREATE TABLE  TEST_TABLE2 (Wcode varchar(100) NOT NULL, Hcode varchar(100), Sdate DATETIME, ASno SMALLINT, PRIMARY KEY (Wcode));
insert into TEST_TABLE2 (Wcode, Hcode, Sdate, ASno) values ('001', null, now(), null);
