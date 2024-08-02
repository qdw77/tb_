CREATE USER tester@localhost identified by '1234';
CREATE USER 'tester'@'localhost' identified by '1234';
-- 권한 - 사용자 추가
-- localhost가 붙으면 이 컴퓨터만 가능 %가 붙으면 다른 컴퓨터에서도 접속 가능


drop user 'tester'@'localhost';
-- 사용자 삭제


-- 실습

-- 1.2024년 동구 소재 학교 소속 학생들의 수능성적을 조회하시오

-- 1)

select school.school_area,
		school.school_name,
        if(student.student_graduate_yn = 'Y', '졸업생', '재학생') as '학생구분',
       ( select sum(score.score_point) as'수능성적'  from tb_score score 
	where student.student_id = score.student_id
	and score.score_season = '2024' )
from tb_student_info student
inner join tb_school_info school
on student.school_id = school.school_id
and school.school_area ='동구';

-- 2)
select school.school_area,
		school.school_name,
        if(student.student_graduate_yn = 'Y', '졸업생', '재학생') as '학생구분',
        sum(score.score_point) as'수능성적'
from tb_student_info student
inner join tb_school_info school
on student.school_id = school.school_id
and school.school_area ='동구'
inner join tb_score score
on student.student_id = score.student_id
and score.score_season = '2024'
group by student.student_id;


-- 2. 2024년 남자 고등학교를 재학중인 학생들의 수능성적을 조회하시오.

-- 1)
select school.school_area,
	school.school_name,
    student.student_id,
    student.student_name,
    if(student.student_graduate_yn = 'Y', '졸업생', '재학생') as '학생구분',
    (select sum(score.score_point) from tb_score score 
    where score.student_id = student.student_id
    and score.score_season = '2024') as '수능성적'
from tb_student_info student
inner join tb_school_info school
on student.school_id = school.school_id
and !(school.school_name like '%여자%')
;

-- 2)
SELECT  school.school_area,
		school.school_name,
		student.student_id,
		student.student_name,
        if(student.student_graduate_yn = 'Y', '졸업생', '재학생') AS '학생구분',
        sum(score.score_point) AS '수능성적'
FROM tb_student_info student
INNER JOIN tb_school_info school
ON student.school_id = school.school_id
AND school.school_name not like ('%여자%')
INNER JOIN tb_score score
ON student.student_id = score.student_id
AND score.score_season = '2024'
GROUP BY student.student_id;


-- 3. 2024년 학교별 수능성적의 평균성적을 조회하시오.
select t.school_name,
 ifnull (avg(score_point), 0) as '평균 성적' 
 from(select school.school_name,
		school.school_id,
		student_name,
		sum(score_point) as score_point
from tb_student_info student
right join tb_school_info school
on student.school_id = school.school_id
left join tb_score score
on student.student_id = score.student_id
and score.score_season = '2024'
group by school.school_id, student.student_id)t
group by t.school_id;



select student.student_id,student.student_name from tb_student_info student;




