-- Question 2: LẤY RA TẤT CẢ CÁC PHÒNG BAN
SELECT * FROM department;
-- SELECT * FROM `position`;
-- SELECT * FROM typequestion;
-- SELECT * FROM question;
-- SELECT * FROM answer;
-- SELECT * FROM `account`;
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