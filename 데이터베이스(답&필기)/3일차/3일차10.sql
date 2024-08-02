-- select score_subject, avg(score_point) from tb_score 
-- group by score_subject 
-- having avg(score_point) >90;

-- select * from student.tb_score;

-- update tb_score
-- set score_point = 90
-- where score_idx = 1;

-- 업데이트 조전은 상세히 작성 전부 날라감

INSERT INTO student.tb_score (
	student_id,
	score_season,
	score_subject,
	score_point
)VALUES 
(17,'202401','database',100);

delete from tb_score 
where student_id = 17 
and  score_subject = 'database';

select * from tb_student_info;


select * from student.tb_score;





