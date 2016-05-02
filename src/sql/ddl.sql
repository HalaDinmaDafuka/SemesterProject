drop table Guest_Login;
drop table Employee_Login;
drop table Client_PrivateInf_TBL;
drop table Reservation_TBL;
drop table Room_TBL;
drop table RoomType_TBL;
drop table Sport_booking;
drop table Client_TBL;
drop sequence client_no_seq;
drop sequence employee_no_seq;
drop sequence reservation_no_seq;
drop sequence sport_booking_no_seq;
drop sequence field_no_seq;
drop sequence Sport_Instructor_no_seq;
drop table Field;
drop table Sport_Instructor;
drop table Sport;
drop table Employee;


create sequence client_no_seq start with 10000;
create sequence employee_no_seq start with 10000;
create sequence reservation_no_seq start with 10000;
create sequence sport_booking_no_seq start with 10000;
create sequence field_no_seq start with 10000;
create sequence Sport_Instructor_no_seq start with 1;

create table Client_TBL
(
client_no number(5) NOT NULL,
client_name varchar2(25) NOT NULL,
client_surname varchar2(25) NOT NULL,
client_address varchar2(60) NOT NULL,
representative_no number(5),
primary key (client_no)
);

create table RoomType_TBL
(
room_type char(11) NOT NULL,
room_price number(3) NOT NULL,
primary key (room_type)
);

create table Room_TBL
(
room_no number(3) NOT NULL,
room_type char(11) NOT NULL,
primary key (room_no),
foreign key (room_type) references RoomType_TBL
);

create table Reservation_TBL
(
reservation_no number(5) NOT NULL,
client_arrival date NOT NULL,
client_departure date NOT NULL,
client_no number(5) NOT NULL,
room_no number(3) NOT NULL,
deposit_paid varchar(1) NOT NULL,
reservation_date date NOT NULL,
primary key (reservation_no),
foreign key (client_no) references Client_TBL,
foreign key (room_no) references Room_TBL
);

create table Client_PrivateInf_TBL
(
client_no number(5) NOT NULL,
client_passport  number(10) NOT NULL,
client_country varchar2(20) NOT NULL,
client_phone number(15) NOT NULL,
client_email varchar2(40) NOT NULL,
client_agency varchar2(20) NOT NULL,
primary key (client_no),
foreign key (client_no) references Client_TBL
);

create table Sport
(
sport_name varchar(25) NOT NULL,
min_players number (2) NOT NULL,
max_players number (2) NOT NULL,
price number(5) NOT NULL,
primary key(Sport_name)
);

create table Employee
(
employee_no number(5) NOT NULL,
employee_name varchar(25) NOT NULL,
employee_position varchar(25) NOT NULL,
primary key (employee_no)
);

create table Sport_Instructor
(
sequence_no number(5) NOT NULL,
employee_no number(5) NOT NULL,
sport_name varchar(25) NOT NULL,
primary key(employee_no, Sport_name),
foreign key (employee_no) references Employee,
foreign key (sport_name) references Sport
);

create table Field
(
field_no number(5) NOT NULL,
sport_name varchar(25) NOT NULL,
primary key (field_no),
foreign key (sport_name) references Sport
);

create table Sport_booking
(
booking_no number(5) NOT NULL,
client_no number(5) NOT NULL,
field_no number(5) NOT NULL,
date_reservation date NOT NULL,
start_hour varchar(5) NOT NULL,
employee_no number(5),
primary key (booking_no),
foreign key (field_no) references Field,
foreign key (employee_no) references Employee,
foreign key (client_no) references Client_TBL
);

create table Guest_Login
(
user_name varchar2(25) NOT NULL,
user_password varchar2(25) NOT NULL,
client_no number(5) NOT NULL,
primary key (user_name),
foreign key (client_no) references Client_TBL
);

create table Employee_Login
(
user_name varchar2(25) NOT NULL,
user_password varchar2(25) NOT NULL,
employee_no number(5) NOT NULL,
primary key (user_name),
foreign key (employee_no) references Employee
);

insert into RoomType_TBL values ('Single Room',60);
insert into RoomType_TBL values ('Double Room',80); 
insert into RoomType_TBL values ('Family Room',100);

insert into Room_TBL values(001,'Single Room');
insert into Room_TBL values(002,'Single Room');
insert into Room_TBL values(003,'Single Room');
insert into Room_TBL values(004,'Single Room');
insert into Room_TBL values(005,'Single Room');
insert into Room_TBL values(006,'Single Room');
insert into Room_TBL values(007,'Single Room');
insert into Room_TBL values(008,'Single Room');
insert into Room_TBL values(009,'Single Room');
insert into Room_TBL values(010,'Single Room');
insert into Room_TBL values(011,'Single Room');
insert into Room_TBL values(012,'Single Room');
insert into Room_TBL values(013,'Single Room');
insert into Room_TBL values(014,'Single Room');
insert into Room_TBL values(015,'Single Room');
insert into Room_TBL values(016,'Single Room');
insert into Room_TBL values(017,'Single Room');
insert into Room_TBL values(018,'Single Room');
insert into Room_TBL values(019,'Single Room');
insert into Room_TBL values(020,'Double Room');
insert into Room_TBL values(021,'Double Room');
insert into Room_TBL values(022,'Double Room');
insert into Room_TBL values(023,'Double Room');
insert into Room_TBL values(024,'Double Room');
insert into Room_TBL values(025,'Double Room');
insert into Room_TBL values(026,'Double Room');
insert into Room_TBL values(027,'Double Room');
insert into Room_TBL values(028,'Double Room');
insert into Room_TBL values(029,'Double Room');
insert into Room_TBL values(030,'Double Room');
insert into Room_TBL values(031,'Double Room');
insert into Room_TBL values(032,'Double Room');
insert into Room_TBL values(033,'Double Room');
insert into Room_TBL values(034,'Double Room');
insert into Room_TBL values(035,'Double Room');
insert into Room_TBL values(036,'Double Room');
insert into Room_TBL values(037,'Double Room');
insert into Room_TBL values(038,'Double Room');
insert into Room_TBL values(039,'Double Room');
insert into Room_TBL values(040,'Double Room');
insert into Room_TBL values(041,'Double Room');
insert into Room_TBL values(042,'Double Room');
insert into Room_TBL values(043,'Double Room');
insert into Room_TBL values(044,'Double Room');
insert into Room_TBL values(045,'Double Room');
insert into Room_TBL values(046,'Double Room');
insert into Room_TBL values(047,'Double Room');
insert into Room_TBL values(048,'Double Room');
insert into Room_TBL values(049,'Double Room');
insert into Room_TBL values(050,'Double Room');
insert into Room_TBL values(051,'Double Room');
insert into Room_TBL values(052,'Double Room');
insert into Room_TBL values(053,'Double Room');
insert into Room_TBL values(054,'Double Room');
insert into Room_TBL values(055,'Double Room');
insert into Room_TBL values(056,'Double Room');
insert into Room_TBL values(057,'Double Room');
insert into Room_TBL values(058,'Double Room');
insert into Room_TBL values(059,'Double Room');
insert into Room_TBL values(060,'Family Room');
insert into Room_TBL values(061,'Family Room');
insert into Room_TBL values(062,'Family Room');
insert into Room_TBL values(063,'Family Room');
insert into Room_TBL values(064,'Family Room');
insert into Room_TBL values(065,'Family Room');
insert into Room_TBL values(066,'Family Room');
insert into Room_TBL values(067,'Family Room');
insert into Room_TBL values(068,'Family Room');
insert into Room_TBL values(069,'Family Room');
insert into Room_TBL values(070,'Family Room');
insert into Room_TBL values(071,'Family Room');
insert into Room_TBL values(072,'Family Room');
insert into Room_TBL values(073,'Family Room');
insert into Room_TBL values(074,'Family Room');
insert into Room_TBL values(075,'Family Room');
insert into Room_TBL values(076,'Family Room');
insert into Room_TBL values(077,'Family Room');
insert into Room_TBL values(078,'Family Room');
insert into Room_TBL values(079,'Family Room');
insert into Room_TBL values(080,'Family Room');
insert into Room_TBL values(081,'Family Room');
insert into Room_TBL values(082,'Family Room');
insert into Room_TBL values(083,'Family Room');
insert into Room_TBL values(084,'Family Room');
insert into Room_TBL values(085,'Family Room');
insert into Room_TBL values(086,'Family Room');
insert into Room_TBL values(087,'Family Room');
insert into Room_TBL values(088,'Family Room');
insert into Room_TBL values(089,'Family Room');
insert into Room_TBL values(090,'Family Room');
insert into Room_TBL values(091,'Family Room');
insert into Room_TBL values(092,'Family Room');
insert into Room_TBL values(093,'Family Room');
insert into Room_TBL values(094,'Family Room');
insert into Room_TBL values(095,'Family Room');
insert into Room_TBL values(096,'Family Room');
insert into Room_TBL values(097,'Family Room');
insert into Room_TBL values(098,'Family Room');
insert into Room_TBL values(099,'Family Room');
insert into Room_TBL values(100,'Family Room');
insert into Room_TBL values(101,'Family Room');
insert into Room_TBL values(102,'Family Room');
insert into Room_TBL values(103,'Family Room');
insert into Room_TBL values(104,'Family Room');




insert into Client_TBL values(client_no_seq.nextval,'boyko','surlev','Street Birkevej, no. 14, Copenhagen',0);
insert into Client_PrivateInf_TBL values(client_no_seq.currval, 108956423, 'Denmark', 203468, 'boyko_surlev@gmail.com', 'SkodAgency');
insert into Guest_Login values ('cl_BoSu81', 'cl_BoSu81', client_no_seq.currval);

insert into Client_TBL values(client_no_seq.nextval,'madalina','dragan','Street Elmevej, no. 63, Copenhagen',0);
insert into Client_PrivateInf_TBL values(client_no_seq.currval, 258054202, 'Denmark', 553455, 'madalina_dragan@yahoo.com', 'SkodAgency');
insert into Guest_Login values ('cl_MaDr82', 'cl_MaDr82', client_no_seq.currval);

insert into Client_TBL values(client_no_seq.nextval,'valentina','mata','S\F8borg Hovegade, no 46, Copenhagen',10002);
insert into Client_PrivateInf_TBL values(client_no_seq.currval, 353245408, 'Denmark', 324580, 'valentina_mata@yahoo.com', 'New Travel Agency');
insert into Guest_Login values ('cl_VaMa83', 'cl_VaMa83', client_no_seq.currval);

insert into Client_TBL values(client_no_seq.nextval,'nikolaj','desting','N\F8rebrogade, no 122, Copenhagen',0);
insert into Client_PrivateInf_TBL values(client_no_seq.currval, 438602406, 'Denmark', 324580, 'nikolaj_desting@gmail.com', 'New Travel Agency');
insert into Guest_Login values ('cl_NiDe84', 'cl_NiDe84', client_no_seq.currval);

insert into Reservation_TBL values (reservation_no_seq.nextval,'06-MAR-2014','08-MAR-2014',10001,1,'Y','03-MAR-2014');
insert into Reservation_TBL values (reservation_no_seq.nextval,'06-MAR-2014','10-MAR-2014',10002,20,'Y','03-MAR-2014');
insert into Reservation_TBL values (reservation_no_seq.nextval,'06-MAR-2014','10-MAR-2014',10003,20,'N','03-MAR-2014');
insert into Reservation_TBL values (reservation_no_seq.nextval,'06-MAR-2014','10-MAR-2014',10004,2,'N','03-MAR-2014');

insert into Sport values('Tennis',2,4,30);
insert into Sport values('Table Tennis',2,4,0);
insert into Sport values('Badminton',2,4,30);
insert into Sport values('Volleyball',2,4,100);
insert into Sport values('Handball',2,4,100);
insert into Sport values('Fitness centre',0,20,100);
insert into Sport values('Golf',2,4,40);
insert into Sport values('Swimming',2,4,40);
insert into Sport values('Mountain biking',2,4,0);

insert into Field values(field_no_seq.nextval,'Tennis'); 
insert into Field values(field_no_seq.nextval,'Tennis');
insert into Field values(field_no_seq.nextval,'Tennis');
insert into Field values(field_no_seq.nextval,'Tennis');
insert into Field values(field_no_seq.nextval,'Tennis');
insert into Field values(field_no_seq.nextval,'Tennis');
insert into Field values(field_no_seq.nextval,'Badminton');
insert into Field values(field_no_seq.nextval,'Badminton');
insert into Field values(field_no_seq.nextval,'Badminton');
insert into Field values(field_no_seq.nextval,'Badminton');
insert into Field values(field_no_seq.nextval,'Volleyball');
insert into Field values(field_no_seq.nextval,'Volleyball');
insert into Field values(field_no_seq.nextval,'Handball');
insert into Field values(field_no_seq.nextval,'Handball');
insert into Field values(field_no_seq.nextval,'Mountain biking');
insert into Field values(field_no_seq.nextval,'Table Tennis');

insert into Employee values(employee_no_seq.nextval,'Casper Larsen', 'Instructor');
insert into Employee_Login values ('emp_ins1', 'emp_ins1', employee_no_seq.currval);

insert into Employee values(employee_no_seq.nextval,'Albert Hansen', 'Instructor');
insert into Employee_Login values ('emp_ins2', 'emp_ins2', employee_no_seq.currval);
insert into Sport_Instructor values(sport_instructor_no_seq.nextval, 10002, 'Tennis');

insert into Employee values(employee_no_seq.nextval,'Yvonne Larsen', 'Instructor');
insert into Employee_Login values ('emp_ins3', 'emp_ins3', employee_no_seq.currval);
insert into Sport_Instructor values(sport_instructor_no_seq.nextval, 10001, 'Badminton');

insert into Employee values(employee_no_seq.nextval,'Maria Larsen', 'Instructor');
insert into Employee_Login values ('emp_ins4', 'emp_ins4', employee_no_seq.currval);
insert into Sport_Instructor values(sport_instructor_no_seq.nextval, 10001, 'Golf');

insert into Employee values(employee_no_seq.nextval,'Casper Larsen', 'Instructor');
insert into Employee_Login values ('emp_ins5', 'emp_ins5', employee_no_seq.currval);
insert into Sport_Instructor values(sport_instructor_no_seq.nextval, 10001, 'Swimming');

insert into Employee values(employee_no_seq.nextval,'Emil Andersen', 'Administrator');
insert into Employee_Login values ('emp_adm', 'emp_adm', employee_no_seq.currval);

insert into Employee values(employee_no_seq.nextval,'Caroline Olsen', 'Boss');
insert into Employee_Login values ('emp_boss', 'emp_boss', employee_no_seq.currval);

insert into Sport_Booking values(sport_booking_no_seq.nextval,10001,10001,to_date('01-jan-01','DD-MON-YY'),'08.00',10001);

commit;
