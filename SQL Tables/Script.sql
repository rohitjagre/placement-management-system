create database placement;

use placement;

create table Admin(AdminId varchar(20) primary key,fullname varchar(30), passwd varchar(20));

create table Company(cid varchar(20) primary key, passwd varchar(20),name varchar(30), cgpa decimal(3,1), description varchar(100));

create table Student(rollno varchar(12) primary key, passwd varchar(20), name varchar(30), dob date, branch varchar(5), cgpa decimal(3,1), contact varchar(10), email varchar(30), SchoolBackLog varchar(5), CollegeBackLog varchar(5) );

create table StudentMarks(rollno varchar(12) primary key, 10th decimal(3,1), 12th decimal(3,1), sem1 decimal(3,1), sem2 decimal(3,1), sem3 decimal(3,1), sem4 decimal(3,1), sem5 decimal(3,1), sem6 decimal(3,1), sem7 decimal(3,1), sem8 decimal(3,1), foreign key(rollno) references Student(rollno) on delete cascade on update cascade);

create table AdminInfo(AdminId varchar(20) primary key,dob date, contact varchar(10), email varchar(30), foreign key(AdminId) references Admin(AdminId) on delete cascade on update cascade);

create database shortlist;

 