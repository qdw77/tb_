SET GLOBAL log_bin_trust_function_creators = 1;

-- CREATE FUNCTION `fn_getName` (
-- 	studentId integer
--     
-- )
-- RETURNS varchar(100)
-- BEGIN

-- 	declare studentName varchar(100);

-- 	select student_name
--     from tb_student_info_test
--     where student_id = studentId
--     into studentName;

-- RETURN studentName;
-- END

-- 1428 에러 해결

-- -- SET GLOBAL log_bin_trust_function_creators = 1;

-- function/생성 후 table과 같이 드라이버로 설정 확인 가능
-- function 생성
SELECT fn_getName(1);

