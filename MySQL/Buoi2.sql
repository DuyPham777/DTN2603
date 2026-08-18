DROP DATABASE IF EXISTS testing_system;
CREATE DATABASE IF NOT EXISTS testing_system;
USE testing_system;

CREATE TABLE department (
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE `position` (
    position_id INT PRIMARY KEY AUTO_INCREMENT,
    position_name ENUM('DEV', 'TEST', 'SCRUM_MASTER', 'PM', 'DEVOPS', 'BUSINESS ANALYST', 'QA LEAD', 'TEAM LEAD', 'INTERN', 'UI/UX DESIGNER')
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
    creator_id INT UNIQUE,
    create_date DATE DEFAULT (CURRENT_DATE()),
    CONSTRAINT fk_group_creator FOREIGN KEY (creator_id)
        REFERENCES `account` (account_id)
);

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
 
INSERT INTO `position`(position_id, position_name) VALUES 
(1, 'DEV'),
(2, 'TEST'),
(3, 'SCRUM_MASTER'),
(4, 'PM'),
(5, 'DEVOPS'),
(6, 'BUSINESS ANALYST'),
(7, 'QA LEAD'),
(8, 'TEAM LEAD'),
(9, 'INTERN'),
(10, 'UI/UX DESIGNER');

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
 ('user10@gmail.com', 'user10', 'Pham Thanh T', 10, 10, '1975-06-10'),
('user11@gmail.com', 'user11', 'Dang Van O', 10, 10, '1975-06-10');

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

INSERT INTO question(question_id, content, creator_id, type_id, category_id, create_date) VALUES
(1, 'Question 1?', 1, 2, 3, '2026-05-19'),
(2, 'Question 2?', 2, 1, 4, '2026-05-19'),
(3, 'Question 3?', 3, 1, 5, '2026-05-19'),
(4, 'Question 4?', 4, 1, 6, '2026-05-19'),
(5, 'Question 5?', 5, 2, 7, '2026-05-19'),
(6, 'Question 6?', 6, 1, 8, '2026-05-19'),
(7, 'Question 7?', 7, 2, 9, '2026-05-19'),
(8, 'Question 8?', 8, 2, 10, '2026-05-19'),
(9, 'Question 9?', 9, 1, 8, '2026-05-19'),
(10, 'Question 10?', 10, 2, 2, '2026-05-19');

INSERT INTO answer(content, question_id, is_correct) VALUES
('Answer 1', 1, TRUE),
('Answer 2', 1, TRUE),
('Answer 3', 1, FALSE),
('Answer 4', 1, TRUE),
('Answer 5', 5, FALSE),
('Answer 6', 6, TRUE),
('Answer 7', 7, TRUE),
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

-- Question 2: LẤY RA TẤT CẢ CÁC PHÒNG BAN
SELECT * FROM department;
 SELECT * FROM `position`;
-- SELECT * FROM typequestion;
-- SELECT * FROM question;
-- SELECT * FROM answer;
SELECT * FROM `account`;
-- SELECT * FROM `group`;

-- Question 3: LẤY RA ID CỦA PHÒNG BAN "SALE"
SELECT department_id FROM department WHERE department_name = "Sale";

-- Question 4: LẤY RA THÔNG TIN ACCOUNT CÓ FULL NAME DÀI NHẤT
SELECT * FROM `account` WHERE length(fullname) = (SELECT MAX(length(fullname)) FROM `account`);

-- Question 5: LẤY RA THÔNG TIN ACCOUNT CÓ FULL NAME DÀI NHẤT VÀ THUỘC PHÒNG BAN CÓ ID= 3
SELECT * FROM `account` WHERE length(fullname) = (SELECT MAX(length(fullname)) FROM `account` WHERE department_id = 3);

-- Question 6: LẤY RA TÊN GROUP ĐÃ THAM GIA TRƯỚC NGÀY 20/12/2019

-- Question 7: LẤY RA ID CỦA QUESTION CÓ >= 4 CÂU TRẢ LỜI
SELECT question_id FROM answer GROUP BY question_id HAVING COUNT(*) >= 4;

-- Question 8: LẤY RA CÁC MÃ ĐỀ THI CÓ THỜI GIAN THI >= 60 PHÚT VÀ ĐƯỢC TẠO TRƯỚC NGÀY 20/12/2019
-- SELECT exam_id FROM exam WHERE duration >= 60 AND 

-- Question 9: LẤY RA 5 GROUP ĐƯỢC TẠO GẦN ĐÂY NHẤT
SELECT * FROM `group` ORDER BY create_date DESC LIMIT 5;

-- Question 10: ĐẾM SỐ NHÂN VIÊN THUỘC DEPARTMENT CÓ ID = 2
SELECT count(*) FROM `account` WHERE department_id = 2;

-- Question 11: LẤY RA NHÂN VIÊN CÓ TÊN BẮT ĐẦU BẰNG CHỮ "D" VÀ KẾT THÚC BẰNG CHỮ "O"
SELECT * FROM `account` WHERE fullname Like 'D%O';

 -- Question 1: VIẾT LỆNH ĐỂ LẤY RA DANH SÁCH NHÂN VIÊN VÀ THÔNG TIN PHÒNG BAN CỦA HỌ
SELECT 
    acc.*, dep.department_name
FROM
    `account` acc
         JOIN
    department dep ON acc.department_id = dep.department_id;

-- Question 2: Viết lệnh để lấy ra thông tin các account được tạo sau ngày 20/12/2010
SELECT 
    *
FROM
    `account`
WHERE
    create_date > '2010-12-20';

-- Question 3: Viết lệnh để lấy ra tất cả các developer
SELECT 
    acc.*
FROM
    `account` acc
        JOIN
    position pos ON acc.position_id = pos.position_id
WHERE
    pos.position_name = 'DEV';

-- Question 4: VIẾT LỆNH ĐỂ LẤY RA DANH SÁCH CÁC PHÒNG BAN CÓ >3 NHÂN VIÊN
SELECT 
    dep.department_id,
    dep.department_name,
    COUNT(acc.account_id) AS so_nhan_vien
FROM
    department dep
        LEFT JOIN
    `account` acc ON dep.department_id = acc.department_id
GROUP BY dep.department_id , dep.department_name
HAVING COUNT(acc.account_id) > 3;

-- Question 5: VIẾT LỆNH ĐỂ LẤY RA DANH SÁCH CÂU HỎI ĐƯỢC SỬ DỤNG TRONG ĐỀ THI NHIỀU NHẤT
SELECT 
    q.question_id,
    q.content,
    COUNT(eq.exam_id) AS so_lan_xuat_hien
FROM
    question q
        JOIN
    examquestion eq ON q.question_id = eq.question_id
GROUP BY q.question_id , q.content
HAVING COUNT(eq.exam_id) = (SELECT 
        MAX(counts.so_lan)
    FROM
        (SELECT 
            COUNT(*) AS so_lan
        FROM
            examquestion
        GROUP BY question_id) AS counts);

-- Question 6: THÔNG KÊ MỖI CATEGORY QUESTION ĐƯỢC SỬ DỤNG TRONG BAO NHIÊU QUESTION
SELECT 
    c.category_id,
    c.category_name,
    COUNT(q.question_id) AS so_luong_cau_hoi
FROM
    categoryquestion c
        LEFT JOIN
    question q ON c.category_id = q.category_id
GROUP BY c.category_id , c.category_name;

-- Question 7: Thống kê mỗi Question được sử dụng trong bao nhiêu Exam    
SELECT 
    q.question_id,
    q.content,
    COUNT(eq.exam_id) AS so_lan_trong_de_thi
FROM
    question q
        LEFT JOIN
    examquestion eq ON q.question_id = eq.question_id
GROUP BY q.question_id , q.content;

-- Question 8: LẤY RA QUESTION CÓ NHIỀU CÂU TRẢ LỜI NHẤT
SELECT 
    q.question_id,
    q.content,
    COUNT(a.answer_id) AS so_luong_tra_loi
FROM
    question q
        LEFT JOIN
    answer a ON q.question_id = a.question_id
GROUP BY q.question_id , q.content
HAVING COUNT(a.answer_id) = (SELECT 
        MAX(counst.so_luong)
    FROM
        (SELECT 
            COUNT(answer_id) AS so_luong
        FROM
            answer
        GROUP BY question_id) AS counst);

-- Question 9: THỐNG KÊ SỐ LƯỢNG ACCOUNT TRONG MỖI GROUP
SELECT 
    g.group_id,
    g.group_name,
    COUNT(ga.account_id) AS so_thanh_vien
FROM
    `group` g
        LEFT JOIN
    groupaccount ga ON g.group_id = ga.group_id
GROUP BY g.group_id , g.group_name;

-- Question 10: TÌM CHỨC VỤ CÓ ÍT NGƯỜI NHẤT
SELECT 
    p.position_id,
    p.position_name,
    COUNT(a.account_id) AS so_nguoi
FROM
    `position` p
        LEFT JOIN
    `account` a ON p.position_id = a.position_id
GROUP BY p.position_id , p.position_name
HAVING COUNT(a.account_id) = (SELECT 
        MIN(counst.so_nguoi)
    FROM
        (SELECT 
            COUNT(account_id) AS so_nguoi
        FROM
            `account`
        GROUP BY position_id) AS counst);

-- Question 11: THỐNG KÊ MỖI PHÒNG BAN CÓ BAO NHIÊU DEV, TEST, SCRUM MASTER, PM
SELECT 
    dep.department_id,
    dep.department_name,
    SUM(CASE WHEN p.position_name = 'DEV' THEN 1 ELSE 0 END) AS dev,
    SUM(CASE WHEN p.position_name = 'TEST' THEN 1 ELSE 0 END) AS test,
    SUM(CASE WHEN p.position_name = 'SCRUM_MASTER' THEN 1 ELSE 0 END) AS scrum_master,
    SUM(CASE WHEN p.position_name = 'PM' THEN 1 ELSE 0 END) AS pm
FROM department dep
LEFT JOIN `account` acc ON dep.department_id = acc.department_id
LEFT JOIN `position` p ON acc.position_id = p.position_id
GROUP BY dep.department_id, dep.department_name;

-- Question 12: LẤY THÔNG TIN CHI TIẾT CỦA CÂU HỎI BAO GỒM: THÔNG TIN CƠ BẢN CỦA
SELECT 
    q.question_id,
    q.content AS cau_hoi,
    q.create_date AS ngay_tao,
    t.type_name AS loai_cau_hoi,
    acc.fullname AS nguoi_tao,
    a.answer_id,
    a.content AS cau_tra_loi,
    a.is_correct AS dung_sai
FROM
    question q
        JOIN
    typequestion t ON q.type_id = t.type_id
        JOIN
    `account` acc ON q.creator_id = acc.account_id
        LEFT JOIN
    answer a ON q.question_id = a.question_id
ORDER BY q.question_id , a.answer_id;

-- Question 13: LẤY RA SỐ LƯỢNG CÂU HỎI CỦA MỖI LOẠI TỰ LUẬN HAY TRẮC NGHIỆM
SELECT 
    t.type_id, t.type_name, COUNT(q.question_id) AS so_luong
FROM
    typequestion t
        LEFT JOIN
    question q ON t.type_id = q.type_id
GROUP BY t.type_id , t.type_name;

-- Question 14: LẤY RA GROUP KHÔNG CÓ ACCOUNT NÀO
SELECT 
    g.group_id, g.group_name
FROM
    `group` g
        LEFT JOIN
    groupaccount ga ON g.group_id = ga.group_id
WHERE
    ga.account_id IS NULL;

-- Question 16: LẤY RA QUESTION KHÔNG CÓ ANSWER NÀO
SELECT 
    q.question_id, q.content
FROM
    question q
        LEFT JOIN
    answer a ON q.question_id = a.question_id
WHERE
    a.answer_id IS NULL;
    
SELECT * FROM `account`;
SELECT * FROM department;
SELECT * FROM `position`;
SELECT * FROM question;
SELECT * FROM exam;
SELECT * FROM `group`;
SELECT * FROM groupaccount;

-- Question 1: Tạo view có chứa danh sách nhân viên thuộc phòng ban sale
-- CTE
CREATE VIEW view_nv_sale AS
WITH nv_sale AS (
	SELECT department_id 
    FROM department
    WHERE department_name = 'Sale'
)
SELECT a.*
FROM `account` a
JOIN nv_sale n ON a.department_id = n.department_id;
SELECT * FROM view_nv_sale;
-- subquery
CREATE VIEW view_nv_sale AS
SELECT a.*
FROM `account` a
WHERE a.department_id = (
	SELECT department_id
    FROM department
    WHERE department_name = 'Sale'
);

-- Question 2: Tạo view có chứa thông tin các account tham gia vào nhiều group nhất
CREATE VIEW view_acc_most_group AS
WITH acc_count AS (
	SELECT account_id, COUNT(group_id) AS count
    FROM groupaccount
    GROUP BY account_id
),
max_group AS (
	SELECT MAX(count) AS max_count
    FROM acc_count
)
SELECT a.*, ac.count
FROM `account` a
JOIN acc_count ac ON a.account_id = ac.account_id
JOIN max_group mg ON ac.count = mg.max_count;
SELECT * FROM view_acc_most_group;

CREATE VIEW view_acc_most_group AS
SELECT a.*, COUNT(g.group_id)
FROM `account` a
JOIN groupaccount ga ON a.account_id = ga.account_id
JOIN `group` g ON 


-- Question 3: Tạo view có chứa câu hỏi có những content quá dài (content quá 300 từ
-- được coi là quá dài) và xóa nó đi
CREATE VIEW dl_content_long AS
WITH content_long AS(
	SELECT *
    FROM question
    WHERE length(content) > 300
)
SELECT * 
FROM content_long ;
SELECT * FROM dl_content_long;
DELETE  
FROM question
WHERE length(content) > 300;

-- Question 4: Tạo view có chứa danh sách các phòng ban có nhiều nhân viên nhất
CREATE VIEW department_most_acc AS
WITH acc_count AS(
	SELECT department_id, COUNT(account_id) AS count
    FROM `account`
    GROUP BY department_id
),
max_acc AS(
	SELECT MAX(count) AS max_count
    FROM acc_count
)
SELECT d.*, ac.count
FROM department d
JOIN acc_count ac ON d.department_id = ac.department_id
JOIN max_acc ma ON ac.count = ma.max_count;
SELECT * FROM department_most_acc;

-- Question 5: Tạo view có chứa tất các các câu hỏi do user họ Nguyễn tạo
CREATE VIEW question_by_Nguyen AS
WITH user_Nguyen AS(
	SELECT * 
    FROM `account`
    WHERE fullname like 'Nguyen%'
)
SELECT q.*, u.fullname
FROM question q
JOIN user_Nguyen u ON q.creator_id = u.account_id;
SELECT * FROM question_by_Nguyen;

UPDATE `account` set username = '2' WHERE account_id = 1;

-- Question 1: Tạo store để người dùng nhập vào tên phòng ban và in ra tất cả các
-- account thuộc phòng ban đó.
DELIMITER $$
CREATE PROCEDURE question1(IN dept_name VARCHAR(100))
BEGIN
    SELECT a.*, d.department_name
    FROM `account` a
    JOIN department d ON a.department_id = d.department_id
    WHERE d.department_name = dept_name;
END$$
DELIMITER ;
CALL question1('Sale') ;

-- Question 2: Tạo store để in ra số lượng account trong mỗi group
DELIMITER $$
CREATE PROCEDURE question2()
BEGIN
    SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS so_luong_account
    FROM `group` g
    LEFT JOIN groupaccount ga ON g.group_id = ga.group_id
    GROUP BY g.group_id, g.group_name;
END$$
DELIMITER ;
CALL question2();

-- Question 3: Tạo store để thống kê mỗi type question có bao nhiêu question được tạo
-- trong tháng hiện tại.
DELIMITER $$
CREATE PROCEDURE question3()
BEGIN
    SELECT t.type_id, t.type_name, COUNT(q.question_id) AS so_luong
    FROM typequestion t
    LEFT JOIN question q ON t.type_id = q.type_id 
        AND MONTH(q.create_date) = MONTH(CURDATE()) 
        AND YEAR(q.create_date) = YEAR(CURDATE())
    GROUP BY t.type_id, t.type_name;
END$$
DELIMITER ;

-- Question 4: Tạo store để trả ra id của type question có nhiều câu hỏi nhất.
DELIMITER $$
CREATE PROCEDURE question4()
BEGIN
    SELECT type_id
    FROM question
    GROUP BY type_id
    HAVING COUNT(*) = (
        SELECT MAX(cnt) FROM (
            SELECT COUNT(*) AS count FROM question GROUP BY type_id
        ) AS t
    );
END$$
DELIMITER ;
CALL question4();

-- Question 5: Sử dụng store ở question 4 để tìm ra tên của type question.
DELIMITER $$
CREATE PROCEDURE question5()
BEGIN
    DECLARE type_id_2 INT;
    CALL question4(v_type_id);
    SELECT type_name FROM typequestion WHERE type_id = type_id_2;
END$$
DELIMITER ;

-- Question 6: Viết 1 store cho phép người dùng nhập vào 1 chuỗi và trả về group có tên
-- chứa chuỗi của người dùng nhập vào hoặc trả về user có username chứa chuỗi của người dùng nhập vào.
DELIMITER $$
CREATE PROCEDURE question6(IN p_keyword VARCHAR(100))
BEGIN
    SELECT group_id, group_name AS result_name, 'Group' AS type
    FROM `group`
    WHERE group_name LIKE CONCAT('%', p_keyword, '%')
    UNION
    SELECT account_id, username, 'User'
    FROM `account`
    WHERE username LIKE CONCAT('%', p_keyword, '%');
END$$
DELIMITER ;

-- Question 7: Viết 1 store cho phép người dùng nhập vào thông tin fullName, email và trong store sẽ tự động gán:
-- username sẽ giống email nhưng bỏ phần @..mail đi
-- positionID: sẽ có default là developer
-- departmentID: sẽ được cho vào 1 phòng chờ
-- Sau đó in ra kết quả tạo thành công --
-- Store 7
drop PROCEDURE question7;
DELIMITER $$
CREATE PROCEDURE question7(
    IN p_fullName VARCHAR(100),
    IN p_email VARCHAR(100)
)
BEGIN
    DECLARE v_username VARCHAR(100);
    DECLARE v_position_id INT;
    DECLARE v_department_id INT;
-- username sẽ giống email nhưng bỏ phần @..mail đi    
    SET v_username = SUBSTRING_INDEX(p_email, '@', 1);
-- positionID: sẽ có default là developer    
    SELECT position_id INTO v_position_id
    FROM `position`
    WHERE position_name = 'DEV';
-- departmentID: sẽ được cho vào 1 phòng chờ    

    INSERT INTO `account`(email, username, fullname, department_id, position_id)
    VALUES (p_email, v_username, p_fullName, v_department_id, v_position_id);
    SELECT 'Tạo tài khoản thành công' AS Message;
END $$
DELIMITER ;
CALL question7('Nguyen Van A', 'nguyenvana@gmail.com');

-- Question 8: Viết 1 store cho phép người dùng nhập vào Essay hoặc Multiple-Choice
-- để thống kê câu hỏi essay hoặc multiple-choice nào có content dài nhất
drop PROCEDURE question8;
DELIMITER $$
CREATE PROCEDURE question8(
    IN p_type_name VARCHAR(50)
)
BEGIN
    SELECT
        q.question_id,
        q.content,
        tq.type_name,
        CHAR_LENGTH(q.content) AS contentLength
    FROM question q
    JOIN typequestion tq
        ON q.type_id = tq.type_id
    WHERE tq.type_name = p_type_name
      AND CHAR_LENGTH(q.content) = (
          SELECT MAX(CHAR_LENGTH(q2.content))
          FROM question q2
          JOIN typequestion tq2
              ON q2.type_id = tq2.type_id
          WHERE tq2.type_name = p_type_name
      );
END $$
DELIMITER ;
CALL question8('Essay');

-- Question 9: Viết 1 store cho phép người dùng xóa exam dựa vào ID
DELIMITER $$
CREATE PROCEDURE question9(IN exam_id INT)
BEGIN
    DELETE FROM exam WHERE exam_id = exam_id;
END $$
DELIMITER ;

-- Question 10: Tìm ra các exam được tạo từ 3 năm trước và xóa các exam đó đi (sử
-- dụng store ở câu 9 để xóa)
-- Sau đó in số lượng record đã remove từ các table liên quan trong khi removing

-- Question 11: Viết store cho phép người dùng xóa phòng ban bằng cách người dùng
-- nhập vào tên phòng ban và các account thuộc phòng ban đó sẽ được
-- chuyển về phòng ban default là phòng ban chờ việc

-- Question 12: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong năm nay
DROP PROCEDURE question12;
DELIMITER $$
CREATE PROCEDURE question12()
BEGIN
    SELECT
        MONTH(create_date) AS month,
        COUNT(question_id) AS questionCount
    FROM question
    WHERE YEAR(create_date) = YEAR(CURRENT_DATE())
    GROUP BY MONTH(create_date)
    ORDER BY MONTH(create_date);
END $$
DELIMITER ;
CALL question12();

-- Question 13: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong 6 tháng gần đây nhất
-- (Nếu tháng nào không có thì sẽ in ra là "không có câu hỏi nào trong tháng"

-- Question 1: Tạo trigger không cho phép người dùng nhập vào Group có ngày tạo
-- trước 1 năm trước
DELIMITER $$
CREATE TRIGGER q1 
BEFORE INSERT ON `group`
FOR EACH ROW
BEGIN
	IF NEW.create_date < DATE_SUB(CURDATE(), INTERVAL 1 YEAR) THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không thể nhập vào Group có ngày tạo trước 1 năm trước';
	END IF;
END $$
DELIMITER ;

INSERT INTO `group`(group_id, group_name, create_date)
VALUE ('20', 'Group A', '2025-01-01')

-- Question 2: Tạo trigger Không cho phép người dùng thêm bất kỳ user nào vào
-- department "Sale" nữa, khi thêm thì hiện ra thông báo "Department
-- "Sale" cannot add more user"
DELIMITER $$
CREATE TRIGGER q2
BEFORE INSERT ON `account`
FOR EACH ROW
BEGIN
	DECLARE v_department_name VARCHAR(100);
    
    SELECT department_name INTO v_department_name
    FROM department
    WHERE department_id = NEW.department_id;
    
	IF v_department_name = 'Sale' THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Department "Sale" cannot add more user';
	END IF;
END $$
DELIMITER ;

INSERT INTO `account`(email, username, fullname, department_id, position_id, create_date) 
VALUES ('user12@gmail.com', 'user12', 'Nguyen Gia H', 2, 10, '2024-06-10')

-- Question 3: Cấu hình 1 group có nhiều nhất là 5 user
select * from `group`
select * from `account`
select * from groupaccount
DELIMITER $$
	CREATE TRIGGER q3
BEFORE INSERT ON groupaccount
FOR EACH ROW 
BEGIN
	DECLARE v_count INT;
    
    SELECT COUNT(1) INTO v_count
    FROM groupaccount as ga
    WHERE ga.group_id = NEW.group_id
    GROUP BY ga.group_id;
    
    IF v_count >= 5 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = '1 group chỉ có nhiều nhất là 5 user';
    END IF;
END $$
DELIMITER ;

-- Question 4: Cấu hình 1 bài thi có nhiều nhất là 10 Question
SELECT * from question;
SELECT * from exam;
SELECT * from examquestion;
DELIMITER $$
	CREATE TRIGGER q4
BEFORE INSERT ON examquestion
FOR EACH ROW 
BEGIN
	DECLARE v_count INT;
    
    SELECT COUNT(1) INTO v_count
    FROM examquestion as eq
    WHERE eq.exam_id = NEW.exam_id
    GROUP BY eq.exam_id;
    
    IF v_count >= 10 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = '1 bài thi chỉ có nhiều nhất là 10 Question';
    END IF;
END $$
DELIMITER ;


-- Question 5: Tạo trigger không cho phép người dùng xóa tài khoản có email là
-- admin@gmail.com (đây là tài khoản admin, không cho phép user xóa),
-- còn lại các tài khoản khác thì sẽ cho phép xóa và sẽ xóa tất cả các thông
-- tin liên quan tới user đó
DELIMITER $$
	CREATE TRIGGER q5
BEFORE DELETE ON `account`
FOR EACH ROW 
BEGIN
    
    IF OLD.email = 'admin@gmail.com' THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'đây là tài khoản admin, không cho phép user xóa';
    -- ON DELETE CASCADE
    END IF;
END $$
DELIMITER ;

-- Question 6: Không sử dụng cấu hình default cho field DepartmentID của table
-- Account, hãy tạo trigger cho phép người dùng khi tạo account không điền
-- vào departmentID thì sẽ được phân vào phòng ban "waiting Department"
INSERT INTO department (department_name) VALUES ('waiting Department');
DELIMITER $$
CREATE TRIGGER q6
BEFORE INSERT ON account
FOR EACH ROW
BEGIN
	DECLARE waiting_id INT;
	
    SELECT department_id INTO waiting_id
    FROM department
    WHERE department_name = 'waiting Department';
    
	IF NEW.department_id IS NULL OR NEW.department_id = 0 THEN
		SET NEW.department_id = waiting_id;
	END IF;
END $$

DELIMITER ;

-- Question 7: Cấu hình 1 bài thi chỉ cho phép user tạo tối đa 4 answers cho mỗi
-- question, trong đó có tối đa 2 đáp án đúng.
DELIMITER $$
CREATE TRIGGER q7
BEFORE INSERT ON answer
FOR EACH ROW
BEGIN
    DECLARE v_answers INT;
    DECLARE correct_answers INT;
    
    SELECT COUNT(1) INTO v_answers
    FROM answer as a
    WHERE a.question_id = NEW.question_id
    GROUP BY a.question_id;
    
    SELECT COUNT(1) INTO correct_answers
    FROM answer as a
    WHERE a.question_id = NEW.question_id AND is_correct = 1
    GROUP BY a.question_id;
    
    IF total_answers >= 4 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Mỗi question chỉ được tối đa 4 answers';
    END IF;
    
    IF NEW.is_correct = 1 AND correct_answers >= 2 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Mỗi question chỉ được tối đa 2 đáp án đúng';
    END IF;
END $$
DELIMITER ;

-- Question 8: Viết trigger sửa lại dữ liệu cho đúng:
-- Nếu người dùng nhập vào gender của account là nam, nữ, chưa xác định
-- Thì sẽ đổi lại thành M, F, U cho giống với cấu hình ở database
SELECT * from `account`;
ALTER TABLE `account` ADD COLUMN gender VARCHAR(20) DEFAULT NULL;

DELIMITER $$
CREATE TRIGGER before_insert_account_gender
BEFORE INSERT ON `account`
FOR EACH ROW
BEGIN
    CASE NEW.gender
        WHEN 'nam' THEN SET NEW.gender = 'M';
        WHEN 'nữ' THEN SET NEW.gender = 'F';
        WHEN 'chưa xác định' THEN SET NEW.gender = 'U';
        ELSE SET NEW.gender = NEW.gender;
    END CASE;
END $$
DELIMITER ;

-- Question 9: Viết trigger không cho phép người dùng xóa bài thi mới tạo được 2 ngày
DELIMITER $$
CREATE TRIGGER q9
BEFORE DELETE ON exam
FOR EACH ROW
BEGIN
    IF OLD.create_date >= (CURDATE() - INTERVAL 2 DAY) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không thể xóa bài thi mới tạo được 2 ngày';
    END IF;
END $$
DELIMITER ;

-- Question 10: Viết trigger chỉ cho phép người dùng chỉ được update, delete các
-- question khi question đó chưa nằm trong exam nào
DELIMITER $$
CREATE TRIGGER q10_u
BEFORE UPDATE ON question
FOR EACH ROW
BEGIN
    DECLARE exam_count INT;
    SELECT COUNT(1) INTO exam_count
    FROM examquestion as eq
    WHERE eq.question_id = OLD.question_id
    GROUP BY eq.question_id;
    
    IF exam_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không thể sửa question đã có trong exam';
    END IF;
END $$

CREATE TRIGGER q1_d
BEFORE DELETE ON question
FOR EACH ROW
BEGIN
    DECLARE exam_count INT;
    SELECT COUNT(1) INTO exam_count
    FROM examquestion as eq
    WHERE eq.question_id = OLD.question_id
    GROUP BY eq.question_id;
    
    IF exam_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không thể xóa question đã có trong exam';
    END IF;
END $$
DELIMITER ;

-- Question 12: Lấy ra thông tin exam trong đó:
-- Duration <= 30 thì sẽ đổi thành giá trị "Short time"
-- 30 < Duration <= 60 thì sẽ đổi thành giá trị "Medium time"
-- Duration > 60 thì sẽ đổi thành giá trị "Long time"
SELECT 
    exam_id,
    `code`,
    title,
    duration,
    CASE 
        WHEN duration <= 30 THEN 'Short time'
        WHEN duration > 30 AND duration <= 60 THEN 'Medium time'
        WHEN duration > 60 THEN 'Long time'
    END AS duration_label
FROM exam;

-- Question 13: Thống kê số account trong mỗi group và in ra thêm 1 column nữa có tên
-- là the_number_user_amount và mang giá trị được quy định như sau:
-- Nếu số lượng user trong group =< 5 thì sẽ có giá trị là few
-- Nếu số lượng user trong group <= 20 và > 5 thì sẽ có giá trị là normal
-- Nếu số lượng user trong group > 20 thì sẽ có giá trị là higher
SELECT 
    g.group_id,
    g.group_name,
    COUNT(ga.account_id) AS user_count,
    CASE 
        WHEN COUNT(ga.account_id) <= 5 THEN 'few'
        WHEN COUNT(ga.account_id) <= 20 THEN 'normal'
        ELSE 'higher'
    END AS the_number_user_amount
FROM `group` g
LEFT JOIN groupaccount ga ON g.group_id = ga.group_id
GROUP BY g.group_id, g.group_name;
-- Question 14: Thống kê số mỗi phòng ban có bao nhiêu user, nếu phòng ban nào
-- không có user thì sẽ thay đổi giá trị 0 thành "Không có User"