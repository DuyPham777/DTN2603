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
CREATE TRIGGER q8
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

CREATE TRIGGER q10_d
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
SELECT 
    d.department_id,
    d.department_name,
    COUNT(a.account_id) AS user_count,
    CASE 
        WHEN COUNT(a.account_id) = 0 THEN 'Không có User'
        ELSE COUNT(a.account_id)
    END AS display_count
FROM department d
LEFT JOIN account a ON d.department_id = a.department_id
GROUP BY d.department_id, d.department_name;