-- Question 1: VIẾT LỆNH ĐỂ LẤY RA DANH SÁCH NHÂN VIÊN VÀ THÔNG TIN PHÒNG BAN CỦA HỌ
SELECT 
    acc.*, dep.department_name
FROM
    `account` acc
        LEFT JOIN
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