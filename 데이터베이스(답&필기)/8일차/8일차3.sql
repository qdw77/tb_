-- trigger 잘안씀  old / new /DELIMITER
-- 사전 데이터 작업을 보통 자바에서 처리  

-- procedure  여러 쿼리 실행/함구와 큰 차이는 
-- sql처리하는 위치로 함수는 클라이언트에서지만 프로시저는 서버로 보내서 처리한다는 차이가 있다
-- 한번에 보는 법이 없고 직접 하나하나 찾아야함

select round((rand() *100), 0);
select (rand() *100);

select * from tb_score;
call proc_score(1);


-- CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_score`(
-- studentId int
-- )
-- BEGIN
-- 	declare score int; -- 랜덤 점수
-- 	
-- 	select  round((rand() *100), 0);
-- 	insert into tb_score(
-- 	student_id,
--     score_season,
--     score_subject,
--     score_point
--   
--   )
--   select  student_id,
--   '202401',
--   'java',
--   score
--     from tb_student_info_test
--     where student_id = studentId;

-- END





