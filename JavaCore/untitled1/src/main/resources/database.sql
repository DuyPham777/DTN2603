CREATE DATABASE finalexam1;
USE finalexam1;

CREATE TABLE candidate(
	id int PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    phone VARCHAR(50),
    email VARCHAR(50),
    `password` VARCHAR(50),
    exp_in_year int,
    pro_skill VARCHAR(50),
    graduation_rank VARCHAR(50),
    `role` VARCHAR(50)
);