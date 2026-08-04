DROP DATABASE IF EXISTS testing_system;
CREATE DATABASE IF NOT EXISTS testing_system;
USE testing_system;

CREATE TABLE department (
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE `position` (
    position_id INT PRIMARY KEY AUTO_INCREMENT,
    position_name ENUM('DEV', 'TEST', 'SCRUM_MASTER', 'PM', 'DevOps', 'Business Analyst', 'QA Lead', 'Team Lead', 'Intern', 'Intern', 'UI/UX Designer') NOT NULL
);

CREATE TABLE `account` (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) UNIQUE NOT NULL,
    username VARCHAR(100) UNIQUE NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    department_id INT NOT NULL,
    position_id INT NOT NULL,
    create_date DATE DEFAULT (CURRENT_DATE()),
    CONSTRAINT fk_account_department FOREIGN KEY (department_id)
        REFERENCES department (department_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_account_position FOREIGN KEY (position_id)
        REFERENCES `position` (position_id)
        ON DELETE CASCADE
);

CREATE TABLE `group` (
    group_id INT PRIMARY KEY AUTO_INCREMENT,
    group_name VARCHAR(100) NOT NULL,
    creator_id INT UNIQUE NOT NULL,
    create_date DATE DEFAULT (CURRENT_DATE()),
    CONSTRAINT fk_group_creator FOREIGN KEY (creator_id)
        REFERENCES `account` (account_id)
);

DROP TABLE IF EXISTs groupaccount;
CREATE TABLE groupaccount (
    group_id INT NOT NULL,
    account_id INT NOT NULL,
    PRIMARY KEY (group_id, account_id),
    join_date DATE DEFAULT (CURRENT_DATE()),
    CONSTRAINT fk_groupaccount_group FOREIGN KEY (group_id)
        REFERENCES `group` (group_id) ON DELETE CASCADE,
    CONSTRAINT fk_groupaccount_account FOREIGN KEY (account_id)
        REFERENCES `account` (account_id) ON DELETE CASCADE
);

CREATE TABLE typequestion (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_name ENUM('ESSAY', 'MULTIPLE-CHOICE') NOT NULL
);

CREATE TABLE categoryquestion (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE question (
    question_id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(100) NOT NULL,
    category_id INT NOT NULL,
    type_id INT NOT NULL,
    creator_id INT NOT NULL,
    create_date DATE DEFAULT (CURRENT_DATE()),
    CONSTRAINT fk_question_category FOREIGN KEY (category_id)
        REFERENCES categoryquestion (category_id) ON DELETE CASCADE,
    CONSTRAINT fk_question_type FOREIGN KEY (type_id)
        REFERENCES typequestion (type_id) ON DELETE CASCADE,
    CONSTRAINT fk_question_creator FOREIGN KEY (creator_id)
        REFERENCES `account` (account_id) ON DELETE CASCADE
);

CREATE TABLE answer (
    answer_id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(100) NOT NULL,
    question_id INT NOT NULL,
    is_correct BOOLEAN NOT NULL,
    CONSTRAINT fk_answer_question FOREIGN KEY (question_id)
        REFERENCES question (question_id)
        ON DELETE CASCADE
);

CREATE TABLE exam (
    exam_id INT PRIMARY KEY AUTO_INCREMENT,
    `code` INT UNIQUE,
    title VARCHAR(100) NOT NULL,
    category_id INT NOT NULL,
    duration INT NOT NULL,
    creator_id INT NOT NULL,
    create_date DATE DEFAULT (CURRENT_DATE()),
    CONSTRAINT fk_exam_category FOREIGN KEY (category_id)
        REFERENCES categoryquestion (category_id) ON DELETE CASCADE,
    CONSTRAINT fk_exam_creator FOREIGN KEY (creator_id)
        REFERENCES `account` (account_id) ON DELETE CASCADE
);

CREATE TABLE examquestion (
    exam_id INT,
    question_id INT,
    PRIMARY KEY (exam_id , question_id),
    CONSTRAINT fk_examquestion_exam FOREIGN KEY (exam_id)
        REFERENCES exam (exam_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_examquestion_question FOREIGN KEY (question_id)
        REFERENCES question (question_id)
        ON DELETE CASCADE
);

INSERT INTO department(department_name) VALUES
('Marketing'),
('Sale'),
('Bảo vệ'),
('Nhân sự'),
('Kỹ thuật'),
('Tài chính'),
('Phó giám đốc'),
('Giám đốc'),
('Thư kí'),
('Bán hàng');
 
INSERT INTO `position`(position_name) VALUES 
('Dev'),
('Test'),
('Scrum Master'),
('PM'),
('DevOps'),
('Business Analyst'),
('QA Lead'),
('Team Lead'),
('Intern'),
('UI/UX Designer');

INSERT INTO `account`(email, username, fullname, department_id, position_id, create_date) VALUES
('user1@gmail.com', 'user01', 'Nguyen Thi Ngoc A', 1, 1, '2018-05-07'),
('user2@gmail.com', 'user02', 'Hoang Viet T', 2, 2, '2018-01-01'),
('user3@gmail.com', 'user03', 'Nguyen Quang H', 3, 3, '2019-01-10'),
('user4@gmail.com', 'user04', 'Nguyen Duy M', 4, 4, '2019-11-20'),
('user5@gmail.com', 'user05', 'Lai Tuan K', 5, 5, '2020-03-05'),
('user6@gmail.com', 'user06', 'Le Trung H', 6, 6, '2021-07-22'),
('user7@gmail.com', 'user07', 'Lai Hong P', 7, 7, '2022-02-14'),
('user8@gmail.com', 'user08', 'Pham Ngoc L', 8, 8, '2023-10-09'),
('user9@gmail.com', 'user09', 'Le Thi S', 9, 9, '1975-01-01'),
('user10@gmail.com', 'user10', 'Pham Thanh T', 10, 10, '1975-06-10');

INSERT INTO `group`(group_name, creator_id, create_date) VALUES
('Java Backend Team', 1, '2018-05-01'),
('SQL Testing Team', 2, '2018-06-15'),
('.NET Team', 3, '2019-01-10'),
('Automation Team', 4, '2019-11-20'),
('Ruby Team', 5, '2020-03-05'),
('DevOps Team', 6, '2021-07-22'),
('Mobile Team', 7, '2022-02-14'),
('Frontend Team', 8, '2023-05-30'),
('Data Team', 9, '2024-01-01'),
('Security Team', 10, '2025-06-10');

INSERT INTO groupaccount(group_id, account_id, join_date) VALUES
(1, 1, '2018-05-01'),
(2, 2, '2018-06-15'),
(3, 3, '2019-01-10'),
(4, 4, '2019-11-20'),
(5, 5, '2020-03-05'),
(6, 6, '2021-07-22'),
(7, 7, '2022-02-14'),
(8, 8, '2023-05-30'),
(9, 9, '2024-01-01'),
(10, 10, '2025-06-10');

INSERT INTO typequestion(type_name) VALUES
('Essay'),
('Multiple-Choice');

INSERT INTO categoryquestion(category_name) VALUES
('Java'),
('.NET'),
('SQL'),
('Postman'),
('Ruby'),
('JavaScript'),
('Python'),
('DevOps'),
('React'),
('Docker');

INSERT INTO question(content, creator_id, type_id, category_id, create_date) VALUES
('Question 1?', 1, 2, 3, '2026-05-19'),
('Question 2?', 2, 3, 4, '2026-05-19'),
('Question 3?', 3, 4, 5, '2026-05-19'),
('Question 4?', 4, 5, 6, '2026-05-19'),
('Question 5?', 5, 6, 7, '2026-05-19'),
('Question 6?', 6, 7, 8, '2026-05-19'),
('Question 7?', 7, 8, 9, '2026-05-19'),
('Question 8?', 8, 9, 10, '2026-05-19'),
('Question 9?', 9, 10, 8, '2026-05-19'),
('Question 10?', 10, 1, 2, '2026-05-19');

INSERT INTO answer(content, question_id, is_correct) VALUES
('Answer 1', 1, TRUE),
('Answer 2', 2, TRUE),
('Answer 3', 3, FALSE),
('Answer 4', 4, TRUE),
('Answer 5', 5, FALSE),
('Answer 6', 6, TRUE),
('ĐAnswer 7', 7, TRUE),
('Answer 8', 8, FALSE),
('Answer 9', 9, FALSE),
('Answer 10', 10, TRUE);

INSERT INTO exam(`code`, title, category_id, duration, creator_id, create_date) VALUES
(101, 'Đề thi Java cơ bản', 1, 60, 1, '2025-01-10'),
(102, 'Đề thi SQL nâng cao', 3, 90, 2, '2025-02-15'),
(103, 'Đề thi Postman', 4, 45, 3, '2025-03-20'),
(104, 'Đề thi Ruby', 5, 60, 4, '2025-04-25'),
(105, 'Đề thi .NET', 2, 75, 5, '2025-05-30'),
(106, 'Đề thi Python', 7, 50, 6, '2025-06-05'),
(107, 'Đề thi DevOps', 8, 90, 7, '2025-07-10'),
(108, 'Đề thi React', 9, 60, 8, '2025-08-15'),
(109, 'Đề thi Docker', 10, 45, 9, '2025-09-20'),
(110, 'Đề thi JavaScript', 6, 70, 10, '2025-10-25');

INSERT INTO examquestion(exam_id, question_id) VALUES
(1,  3),
(2,  4),
(3,  5),
(4,  6),
(5,  7),
(6,  8),
(7,  9),
(8,  10),
(9,  2),
(10, 1);