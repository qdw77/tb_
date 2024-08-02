select * from tb_student_info student 
inner join tb_school_info school
on student.school_id = school.school_id;

-- view 사용전
select student.student_id,
student.student_name,
student.student_graduate_yn,
school.school_area,
school.school_addr,
school.school_phone
 from tb_student_info student 
inner join tb_school_info school
on student.school_id = school.school_id;

-- view 사용 후
-- CREATE VIEW `v_student_info` AS
-- select student.student_id,
-- student.student_name,
-- student.student_graduate_yn,
-- school.school_area,
-- school.school_addr,
-- school.school_phone
--  from tb_student_info student 
-- inner join tb_school_info school
-- on student.school_id = school.school_id
-- ;
-- -- > apply 후
-- CREATE 
--     ALGORITHM = UNDEFINED 
--     DEFINER = `root`@`localhost` 
--     SQL SECURITY DEFINER
-- VIEW `v_student_info` AS
--     SELECT 
--         `student`.`student_id` AS `student_id`,
--         `student`.`student_name` AS `student_name`,
--         `student`.`student_graduate_yn` AS `student_graduate_yn`,
--         `school`.`school_area` AS `school_area`,
--         `school`.`school_addr` AS `school_addr`,
--         `school`.`school_phone` AS `school_phone`
--     FROM
--         (`tb_student_info` `student`
--         JOIN `tb_school_info` `school` ON ((`student`.`school_id` = `school`.`school_id`)))

-- 설정 후 확인
select * from v_student_info;

select * from v_student_info v
inner join tb_score score
on v.student_id = score.student_id;
