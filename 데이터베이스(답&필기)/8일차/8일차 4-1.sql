select * from tb_school_info;
select * from tb_score;
select * from tb_student_info;

-- 1. 학교별 졸업생들의 정보를 출력하시오. y가 졸업생
-- where>전부 
-- 방법1
select school.school_name, school.school_area, student.student_name
from tb_school_info school
left join tb_student_info student
on school.school_id = student.school_id
and student.student_graduate_yn ='y';

-- 방법2
select school.school_name, school.school_area, student.student_name
from tb_student_info student 
right join tb_school_info school
on school.school_id = student.school_id
and student.student_graduate_yn ='y';

-- 3. 2024년 수능 성적 상위 5명의 학생정보를 출력하시오.
SELECT student.student_id, 
student.student_name, 
sum(score.score_point)
FROM tb_student_info student
left join tb_score score
on student.student_id = score.student_id
and score.score_season = '2024'
group by student.student_id
order by sum(score.score_point) desc 
limit 5;

-- 3. 2023년 수능 평균 성적 상위 10명의 학생정보를 출력하시오.
SELECT  student.student_id, student.student_name ,avg(score.score_point) FROM tb_student_info student
LEFT JOIN tb_score score
on student.student_id = score.student_id
and score.score_season = '2023'
group by student.student_id
order by avg(score.score_point) desc
limit 10
;

-- 3. 2023년 수능 평균 성적과 학생정보를 출력하시오.
SELECT  student.student_id, student.student_name ,avg(score.score_point) FROM tb_student_info student
LEFT JOIN tb_score score
on student.student_id = score.student_id
and score.score_season = '2023'
group by student.student_id
order by avg(score.score_point) desc

;

-- 3. 2024년 수능 성적 하위 5명의 학생정보를 출력하시오.
select student.student_id, student.student_name , sum(score.score_point) from tb_student_info student
left join tb_score score
on student.student_id = score.student_id
and score_season ='2024'
group by student.student_id
order by sum(score.score_point) limit 5;

-- 4. 2024년 졸업생과 재학생의 수능 성적 평균을 출력하시오.
--   결과) 
select if(t.student_graduate_yn = 'Y', '졸업생', '재학생') as '학생 구분', 
avg(t.sumPoint) as '수능 평균 성적' from
(select student.student_id, student.student_name, student.student_graduate_yn, sum(score.score_point) as sumPoint
from tb_student_info student
inner join tb_score score
on student.student_id = score.student_id
and score.score_season ='2024'
group by student.student_id) t
group by t.student_graduate_yn;

select student.student_graduate_yn, 
(select sum(score.score_point)/count(student_id) )from tb_score score
where score.score_season = '2024';

-- 인라인 뷰(Inline View)T

select * from tb_student_info;



