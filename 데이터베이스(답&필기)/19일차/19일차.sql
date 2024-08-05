SELECT * FROM tb_member_info;
select*from tb_student_score;
select * from tb_student_info;

-- 수요일부터 스프링
delete from tb_student_info where student_name ="?";
delete from tb_student_info where student_idx ="?";

delete from tb_student_score 
where score_season =? 
and score_semester =? 
 and score_exam_type =? 
 and score_subject =? 
 and student_idx =?; 
 /*
 delete FROM 테이블 WHERE score_season
 */ 
 
 select count(student_idx) as cnt from tb_student_score
 where student_idx =?;



select score_idx as scoreIdx,
score_season as scoreSeason,
score_semester as scoreSemester,
score_exam_type as scoreExamType,
score_subject as scoreSubject,
score_point as scorePoint
 from tb_student_score 
 where student_idx = 1;