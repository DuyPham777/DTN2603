-- Question 1: Tạo view có chứa danh sách nhân viên thuộc phòng ban sale
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