
-- 실습1
select student.student_name, student.student_grade,
	(select score.score_point 
	from tb_score score 
	where score.score_subject ='국어' 
	and score.student_id = student.student_id ) as '국어',
	(select score.score_point 
	from tb_score score 
	where score.score_subject ='영어' 
	and score.student_id = student.student_id ) as '영어',
    (select score.score_point 
	from tb_score score 
	where score.score_subject ='수학' 
	and score.student_id = student.student_id ) as '수학'
from tb_student_info student;


-- 실습2
select st.student_name, st.student_grade, st.student_school_area, st.student_school,
	(select avg(sc.score_point) 
		from tb_score sc 
		where sc.score_subject)as '평균점수'
	from tb_student_info st 
	where st.student_school_area ='서구'
	and st.student_grade =1;
    
-- 실습3
select student.student_name, student.student_grade, student.addr,
(select school.school_name 
from tb_school_info school 
where school.school_idx = student.school_idx) as '학교명' 
from tb_student_info_test student 
where student.student_grade = 1;

select student.student_name, student.student_grade, 
(select school.school_area 
from tb_school_info school 
where school.school_idx = 1) as '학교 위치',
(select school.school_name 
from tb_school_info school 
where school.school_idx = student.school_idx) as '학교명' ,
(select school.school_addr
from tb_school_info school 
where school.school_idx = 1) as '학교 위치2',
(select school.school_phone
from tb_school_info school 
where school.school_idx = 1) as '전화번호'
from tb_student_info_test student 
where student.student_grade = 1;


-- 실습4
select * from tb_student_info_test student
where student.school_idx in(
	select school_idx 
	from tb_school_info 
	where school_area = '중구'
);

select * from tb_student_info_test student
where student.school_idx in(
1,2);

select student.student_name, student.student_grade,(
	select school_name 
		from tb_school_info
		where school_idx = student.school_idx
) as '학교명', 
student.student_zipcode, student.addr 
from tb_student_info_test student 
where student.school_idx in(
	select school_idx 
	from tb_school_info 
	where school_area = '중구');

-- 실습5
select * from tb_student_info_test student
where student.school_idx in(
select school_idx
from tb_school_info
where school_area ='중구'
) and  student.student_id in(
select student_id
from tb_score
group by student_id
having avg(score_point)<80)
;

select student.student_name, (
select school_name 
from tb_school_info 
where school_idx = student.school_idx)as'학교명',
student.student_grade, 
(select avg(score_point) 
from tb_score 
where student_id = student.student_id)as'평균 성적'
from tb_student_info_test student
where student.school_idx in(
select school_idx
from tb_school_info
where school_area ='중구'
) and  student.student_id in(
select student_id
from tb_score
group by student_id
having avg(score_point)<80)
;

select student_id, avg (score_point)
from tb_score
group by student_id
having avg(score_point)<80;

-- 실습7(select (@ROWNUM:=@ROWNUM+1) AS rnum
-- , score. * from tb_score score, (SELECT @ROWNUM:=0 FROM DUAL) R)

-- 국어 정렬
select (@ROWNUM:=@ROWNUM+1) AS rnum
, score. * from tb_score score, (SELECT @ROWNUM:=0 FROM DUAL) R
where score.score_subject ='국어'
order by score.score_point asc;

-- 국어 10부터 정렬
-- select (@ROWNUM:=@ROWNUM+1) AS rnum
-- , score. * from tb_score score, (SELECT @ROWNUM:=9 FROM DUAL) R
-- where score.score_subject ='국어'
-- order by score.score_point asc;

-- 테이블이 주어짐
select * from (select (@ROWNUM:=@ROWNUM+1) AS rnum
, score. * from tb_score score, (SELECT @ROWNUM:=0 FROM DUAL) R
where score.score_subject ='국어'
order by score.score_point asc) t;

-- 순번 재정렬
select * from (select (@ROWNUM:=@ROWNUM+1) AS rnum
, score. * from tb_score score, (SELECT @ROWNUM:=0 FROM DUAL) R
where score.score_subject ='국어'
order by score.score_point asc) t
order by t.rnum desc;

-- 상위10명 (limit은 무조껀 맨 마지막줄에 작성)
select * from (select (@ROWNUM:=@ROWNUM+1) AS rnum
, score. * from tb_score score, (SELECT @ROWNUM:=0 FROM DUAL) R
where score.score_subject ='국어'
order by score.score_point asc) t
order by t.rnum desc
limit 10 offset 0 ;

select * from tb_score;
select * from tb_student_info;
select*from tb_school_info;
select * from tb_student_info_test;