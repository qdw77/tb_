
-- view/생성 후 table과 같이 드라이버로 설정 확인 가능

-- CREATE VIEW `v_test` AS
-- SELECT  si.student_name,
-- 		si.student_grade,
--         sc.school_area,
--         sc.school_name,
--         avg(p.score_point) AS '평균성적'
-- FROM tb_student_info_test si
-- LEFT JOIN tb_school_info sc
-- ON si.school_idx = sc.school_idx
-- LEFT JOIN tb_score p
-- ON si.student_id = p.student_id
-- WHERE sc.school_area = '서구'
-- AND si.student_grade = 1
-- group by si.student_id;


-- view 찾기
select * from v_test;

-- 실습 view

-- CREATE VIEW `v_student_info` AS
-- select student.student_id,
-- 		student.student_name,
--         student.student_grade,
--         school.school_name as student_school,
--         school.school_area as student_school_area,
--         student.student_zipcode as zipcode,
--         student.addr as student_addr
--         
-- from tb_student_info_test student
-- left join tb_school_info school
-- on student.school_idx = school.school_idx
-- ;

-- view 찾기
select*from v_student_info;

