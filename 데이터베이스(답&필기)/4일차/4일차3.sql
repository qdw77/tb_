select * from tb_student_info_test;

-- CREATE TABLE `tb_student_info_test` (
--   `student_id` int NOT NULL AUTO_INCREMENT,
--   `student_name` varchar(20) NOT NULL,
--   `student_grade` int DEFAULT '1',
--   `school_idx` int not null,
--   `student_zipcode` varchar(6) DEFAULT NULL,
--   `addr` varchar(100) DEFAULT NULL,
--   PRIMARY KEY (`student_id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- V기존의 테이블은 따로 연결이 안돼기 때문에 테이블 구조를 바꾸는 작업을한거임
-- INSERT INTO tb_student_info_test(
-- 	student_name,
--     student_grade,
--     school_idx,
--     student_zipcode,
--     addr
-- )
-- SELECT student_name,
-- 		student_grade,
-- 		CASE
-- 			WHEN student_school = '대전고등학교' then 1
-- 			WHEN student_school = '대전여자고등학교' then 2
-- 			WHEN student_school = '관평고등학교' then 3
--             WHEN student_school = '관평여자고등학교' then 4
-- 		END,
--         student_zipcode,
--         addr
-- FROM tb_student_info;

