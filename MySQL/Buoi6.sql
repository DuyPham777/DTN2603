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
    SELECT position_id INTO v_PositionID
    FROM `position`
    WHERE position_name = 'DEV';
-- departmentID: sẽ được cho vào 1 phòng chờ    

    INSERT INTO `account`(email, username, fullname, department_id, position_id)
    VALUES (p_Email, v_Username, p_FullName, v_DepartmentID, v_PositionID);
    SELECT 'Tạo tài khoản thành công' AS Message;
END $$
DELIMITER ;

-- Question 8: Viết 1 store cho phép người dùng nhập vào Essay hoặc Multiple-Choice
-- để thống kê câu hỏi essay hoặc multiple-choice nào có content dài nhất
DELIMITER $$
CREATE PROCEDURE question8(
    IN p_typeName VARCHAR(50)
)
BEGIN
    SELECT
        q.questionID,
        q.content,
        tq.typeName,
        CHAR_LENGTH(q.content) AS contentLength
    FROM question q
    JOIN type_question tq
        ON q.typeID = tq.typeID
    WHERE tq.typeName = p_typeName
      AND CHAR_LENGTH(q.content) = (
          SELECT MAX(CHAR_LENGTH(q2.content))
          FROM question q2
          JOIN type_question tq2
              ON q2.typeID = tq2.typeID
          WHERE tq2.typeName = p_typeName
      );
END $$
DELIMITER ;

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
DELIMITER $$
CREATE PROCEDURE question12()
BEGIN
    SELECT
        MONTH(createdDate) AS month,
        COUNT(questionID) AS questionCount
    FROM question
    WHERE YEAR(createdDate) = YEAR(CURRENT_DATE())
    GROUP BY MONTH(createdDate)
    ORDER BY MONTH(createdDate);
END $$
DELIMITER ;

-- Question 13: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong 6 tháng gần đây nhất
-- (Nếu tháng nào không có thì sẽ in ra là "không có câu hỏi nào trong tháng")