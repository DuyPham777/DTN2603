CREATE DATABASE testing_system;
USE testing_system;
CREATE TABLE department (
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(100)
);
CREATE TABLE `position` (
    position_id INT PRIMARY KEY AUTO_INCREMENT,
    position_name ENUM('DEV', 'TEST', 'SCRUM_MASTER', 'PM')
);
CREATE TABLE `account` (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    email    VARCHAR(100) UNIQUE,
    username VARCHAR(100) UNIQUE,
    fullname VARCHAR(100),
    department_id INT,
    position_id INT,
    create_date DATETIME,
    CONSTRAINT fk_account_department FOREIGN KEY (department_id)
        REFERENCES department (department_id),
    CONSTRAINT fk_account_position FOREIGN KEY (position_id)
        REFERENCES `position` (position_id)
);
CREATE TABLE `group` (
    group_id INT PRIMARY KEY AUTO_INCREMENT,
    group_name VARCHAR(100),
    creator_id INT UNIQUE,
    create_date DATETIME,
    CONSTRAINT fk_group_creator FOREIGN KEY (creator_id)
        REFERENCES `account` (account_id)
);
CREATE TABLE groupaccount (
    group_id INT,
    account_id INT,
    join_date DATETIME,
    CONSTRAINT fk_groupaccount_group FOREIGN KEY (group_id)
        REFERENCES `group` (group_id),
    CONSTRAINT fk_groupaccount_account FOREIGN KEY (account_id)
        REFERENCES `account` (account_id)
);
CREATE TABLE typequestion (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    typename ENUM('ESSAY', 'MULTIPLE-CHOICE')
);
CREATE TABLE categoryquestion (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    categoryname VARCHAR(100) UNIQUE
);
CREATE TABLE question (
    question_id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(100),
    category_id INT,
    type_id INT,
    creator_id INT,
    create_date DATETIME,
    CONSTRAINT fk_question_category FOREIGN KEY (category_id)
        REFERENCES categoryquestion (category_id),
    CONSTRAINT fk_question_type FOREIGN KEY (type_id)
        REFERENCES typequestion (type_id),
    CONSTRAINT fk_question_creator FOREIGN KEY (creator_id)
        REFERENCES `account` (account_id)
);
CREATE TABLE answer (
    answer_id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(100),
    question_id INT UNIQUE,
    is_correct BOOLEAN,
    CONSTRAINT fk_answer_question FOREIGN KEY (question_id)
        REFERENCES question (question_id)
);
CREATE TABLE exam (
    exam_id INT PRIMARY KEY AUTO_INCREMENT,
    code INT UNIQUE,
    title VARCHAR(100),
    category_id INT,
    duration INT,
    creator_id INT,
    create_date DATETIME,
    CONSTRAINT fk_exam_category FOREIGN KEY (category_id)
        REFERENCES categoryquestion (category_id),
    CONSTRAINT fk_exam_creator FOREIGN KEY (creator_id)
        REFERENCES `account` (account_id)
);
CREATE TABLE examquestion (
    exam_id INT,
    question_id INT,
    CONSTRAINT fk_examquestion_exam FOREIGN KEY (exam_id)
        REFERENCES exam (exam_id),
    CONSTRAINT fk_examquestion_question FOREIGN KEY (question_id)
        REFERENCES question (question_id)
);