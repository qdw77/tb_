-- -- 1. 대전 지역 학생들의 2024년 수능 성적 중 언어 과목의 최고점을 얻은 학생의 정보를 구하시오.
-- 1) 
select student.student_name, 
if(student.student_graduate_yn = 'Y', '졸업생', '재학생') as '재학 여부',
 score.score_season as '수능년도' , 
score.score_subject as '과목',
score.score_point as '점수'
 from tb_student_info student
 inner join tb_score score
 on student.student_id = score.student_id 
and score.score_season ='2024'
and score.score_subject = '언어'
order by score.score_point desc
limit 1;

-- 2)
select student.student_name, if(student.student_graduate_yn = 'Y', '졸업생', '재학생') as '재학여부', 
score.score_season as '수능년도' , 
score.score_subject as '과목',
score.score_point as '점수'
from tb_student_info student
inner join tb_score score
on student.student_id = score.student_id
and score.score_season ='2024'
and score.score_subject = '언어'
where score.score_point = (
select max(sc.score_point) 
from tb_score sc
where sc.score_season = '2024'
and sc.score_subject = '언어'
);

-- 2. 대전 지역 학생들의 2024년 수능 성적 중 언어 과목의 최고점을 얻은 학생의 정보를 구하시오.
-- 1)
select student.student_name,school_id,
if(student.student_graduate_yn = 'Y', '졸업생', '재학생') as '재학여부',
score.score_season as '수능년도',
score.score_subject as '과목',
score.score_point as '점수'
from tb_student_info student
inner join tb_score score
on student.student_id = score.student_id
and score.score_season = '2024'
and score.score_subject = '수학'
order by score.score_point asc
limit 1;


-- 2)
select student.student_name, 
if(student.student_graduate_yn = 'Y', '졸업생', '재학생') as '재학여부',
score.score_season as '수능 년도' , 
score.score_subject as '과목', 
score_point as '점수' 
from tb_student_info student
inner join tb_score score
on student.student_id = score.student_id
and score.score_season ='2024'
and score.score_subject = '수학'
where score.score_point = (
select max(sc.score_point) 
from tb_score sc
where sc.score_season = '2024'
and sc.score_subject = '수학'
);



select * from tb_score;

-- 3
select * from tb_student_info;
select * from tb_score;
select * from tb_school_info;

-- 수능성적 전부 매칭
select * from tb_student_info student
inner join tb_score score
on student.student_id = score.student_id
;

-- 1)
select (select school.school_name 
from tb_school_info school 
where school.school_id = student.school_id) as '학교',
student.student_name as '학생이름', 
if(student.student_graduate_yn = 'Y', '졸업생', '재학생') as '재학여부',

(select sc.score_point 
from tb_score sc 
where sc.student_id = student.student_id 
and sc.score_season = '2024' 
and sc.score_subject = '언어') as '언어',

(select sc.score_point 
from tb_score sc 
where sc.student_id = student.student_id 
and sc.score_season = '2024' 
and sc.score_subject = '영어') as '외국어',
(select sc.score_point 
from tb_score sc 
where sc.student_id = student.student_id 
and sc.score_season = '2024' 
and sc.score_subject = '수학') as '수학',

(select sc.score_point 
from tb_score sc 
where sc.student_id = student.student_id 
and sc.score_season = '2024' 
and sc.score_subject = '사회탐구1') as '사회탐구1',

(select sc.score_point 
from tb_score sc 
where sc.student_id = student.student_id 
and sc.score_season = '2024' 
and sc.score_subject = '사회탐구2') as '사회탐구2'
from tb_student_info student
inner join tb_score score
on student.student_id = score.student_id
and score.score_season = '2024'
group by student.student_id
;

-- 2) 다른 방법 -Functions 사용/설정

-- CREATE FUNCTION `fn_getScore` (
--  season varchar(4),
--  subject varchar (10),
--  studentId integer
-- )
-- RETURNS INTEGER
-- BEGIN

-- declare scorePoint integer;

-- select score.score_point 
-- from tb_score score
-- where score.score_season= season
-- and score.score_subject = subject 
-- and score.student_id = studentId
-- into scorePoint;

-- RETURN scorePoint;
-- END

-- 설정 후 확인

-- select fn_getScore('2023','언어', 1);

-- select * from tb_score;

select (select school.school_name from tb_school_info school where school.school_id = student.school_id) as '학교',
student.student_name as '학생이름', 
if(student.student_graduate_yn = 'Y', '졸업생', '재학생') as '재학여부',
fn_getScore('2024', '언어', student.student_id) as '언어',
fn_getScore('2024', '영어', student.student_id) as '영어',
fn_getScore('2024', '수학', student.student_id) as '수학',
fn_getScore('2024', '사회탐구1', student.student_id) as '사회탐구1',
fn_getScore('2024', '사회탐구2', student.student_id) as '사회탐구2'
from tb_student_info student
inner join tb_score score
on student.student_id = score.student_id
and score.score_season = '2024'
group by student.student_id
;



