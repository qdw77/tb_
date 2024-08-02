-- 예제1

SELECT  T1.student_id,
		T1.student_name,
		T2.score_subject,
		T2.score_point
	FROM tb_student_info T1
	INNER JOIN tb_score T2
	ON T1.student_id = T2.student_id;

 -- 예제2   
    
SELECT *
	FROM tb_student_info T1
	LEFT JOIN tb_score T2
	ON T1.student_id = T2.student_id
	WHERE T1.student_id in ('12', '13');



-- -- 예제3
-- -- self hoin-사용이 별로 안됨
-- --     곤충(테이블)
-- --     곤충 천적 수명
-- --     메뚜기 거미 6개월
-- --     거미 참새 1년
-- --     
-- --     select b.천적, b.수명 from 곤충 a
-- --     join 곤충 b
-- --     on a.곤충 = b.천적
-- --     where a.천적 = (select from 곤충 where 곤충 = 메뚜기)

-- 실습1
select * from tb_student_info_test;
select * from tb_school_info;
select * from tb_score;
select * from tb_student_info;


-- select student.student_id 
-- from tb_studnet_info_test(총 3개의 테이블을 연결할 수 있는 학생 테이블) student
-- inner join tb_school_info;

-- 유성구에 재학 중인 1학년
-- select student.*, school.*
-- from tb_student_info_test student
-- inner join tb_school_info school
-- on student.school_idx = school.school_idx
-- where school.school_area ='유성구'
-- and student.student_grade = 1;

-- 방법1
select student.*, school.*,
(select avg(score.score_point) 
from tb_score score 
where student.student_id = score.student_id) as'평균성적'
from tb_student_info_test student
inner join tb_school_info school
on student.school_idx = school.school_idx 
where school.school_area ='유성구'
and student.student_grade = 1;

-- 방법2
select student.student_name, 
	school.school_area,
	school.school_name
,avg(score.score_point) as'평균성적'
from tb_student_info_test student
inner join tb_school_info school
on student.school_idx = school.school_idx
inner join tb_score score 
on student.student_id = score.student_id
where school.school_area = '유성구'
and student.student_grade = 1
group by student.student_id;

-- 실습2

SELECT student.student_name, student.student_grade as '학년', 
school.school_name as '학교 이름', school.school_area as '학교 위치', 
school.school_addr as '학교 상세 위치', school_phone as '전화번호'
FROM tb_student_info_test student
inner join tb_school_info school
on student.school_idx = school.school_idx
where school.school_area ='유성구'
and student.student_grade = 1;

-- 실습3
SELECT student.student_name, student.student_grade as '학년', 
school.school_name as '학교 이름', school.school_area as '학교 위치', 
school.school_addr as '학교 상세 위치', school_phone as '전화번호'
FROM tb_student_info_test student
inner join tb_school_info school
on student.school_idx = school.school_idx
where school.school_area = '중구';

-- 실습4
SELECT student.student_name, student.student_grade as '학년', 
school.school_name as '학교 이름', school.school_area as '학교 위치', 
school.school_addr as '학교 상세 위치', school_phone as '전화번호',
avg(score.score_point)
FROM tb_student_info_test student
inner join tb_school_info school
on student.school_idx = school.school_idx
left join tb_score score 
on student.student_id = score.student_id
where school.school_area = '중구'
group by student.student_id
having avg(score.score_point)<80;

-- (having 주기전)
select t.*
from (SELECT student.student_name, student.student_grade as '학년', 
school.school_name as '학교 이름', school.school_area as '학교 위치', 
school.school_addr as '학교 상세 위치', school_phone as '전화번호',
avg(score.score_point)
FROM tb_student_info_test student
inner join tb_school_info school
on student.school_idx = school.school_idx
left join tb_score score 
on student.student_id = score.student_id
where school.school_area = '중구'
group by student.student_id
)t;

-- (having 준 후)
select t.*, t.avgPoint
from (SELECT student.student_name, student.student_grade as '학년', 
school.school_name as '학교 이름', school.school_area as '학교 위치', 
school.school_addr as '학교 상세 위치', school_phone as '전화번호',
avg(score.score_point)  as avgPoint
FROM tb_student_info_test student
inner join tb_school_info school
on student.school_idx = school.school_idx
left join tb_score score 
on student.student_id = score.student_id
where school.school_area = '중구'
group by student.student_id)t
where t.avgPoint <80;




select * from tb_student_info_test;
select * from tb_school_info;






