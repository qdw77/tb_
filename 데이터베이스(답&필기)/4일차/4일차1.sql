-- -- and = 둘다 충족되어야 나옴

-- select *
-- from tb_student_info
-- where student_school_area = '중구'
-- and student_grade = 2;



-- or = 또는 으로 사용되며 한쪽만 충족되면 나옴

-- select * 
-- from tb_student_info
-- where student_school = '대전고등학교'
-- or student_school = '대전여자고등학교';



-- select * from -출력
-- where 어디서/무엇을



-- 실습1 
-- and/or

-- select * from tb_student_info
-- where student_school_area = '서구'
-- and student_grade = 2;

-- select * from tb_student_info
-- where student_school ='관평고등학교'
-- or student_school ='관평여자고등학교';

-- 실습2 and/or
-- select * from tb_score
-- where score_subject = '수학'
-- and score_point <70 
-- or score_point >=80;

-- like 실습2 관평으로 시작하는 곳

-- select * from tb_student_info
-- where student_school like '관평%';

-- between 실습2

-- 조건 2개의 between 
-- select 
-- score_idx,student_id,score_season,score_subject,score_point
-- from tb_score
-- where score_subject ='국어'
-- and score_point between 80 and 90 ;



-- like 실습2
-- 자리수 ___
-- select 
-- student_name,student_grade,student_school_area,student_school,student_zipcode
-- from tb_student_info
-- where student_name like '김__';

-- 일반 
-- select 
-- student_name,student_grade,student_school_area,student_school,student_zipcode
-- from tb_student_info
-- where student_name like '%김%;

-- like 주소/여자 실습2
-- select * from tb_student_info
-- where student_school_area = '중구'
-- and student_school like '%여자%';

-- select * from tb_student_info
-- where addr like '%중구%'
-- and  student_school  like '%여자%';

-- in/not in 실습3

-- select * from tb_student_info
-- where student_grade not in(3);

-- select * from tb_student_info
-- where student_grade in(1,2);

-- 실습3 in/not in 제외하고 사용시
-- select * from tb_student_info
-- where student_grade !=3;


-- 실습3 in/ not in
-- select * from tb_student_info 
-- where student_school not in ('관평고등학교','대전고등학교');

-- group by-기준점 (그룹화)/숫자 count
-- 어떤걸 기준으로
-- select student_school, count(student_id) from tb_student_info
-- where student_school in ('대전여자고등학교','대전고등학교') 
-- group by student_school ;


-- 별칭
-- select
-- tsi.student_id as 'id',
-- tsi.student_school as 'school',
-- tsi.student_name as 'name',
-- ts.score_subject as 'subject',
-- ts.score_point as 'point'
-- from tb_student_info tsi 
-- join tb_score ts 
-- on tsi.student_id = ts.student_id;


-- 정렬- 기본은 내림차순
-- select * from tb_student_info
-- order by student_grade;


-- 내림차순
-- select * from tb_student_info
-- order by student_grade asc;

-- 오름차순
-- select * from tb_student_info
-- order by student_grade desc;


-- 실습4

-- 별칭
-- select
-- tsi.student_id as 'id',
-- tsi.student_name as 'name',
-- tsi.student_school as 'school',
-- tsi.student_school_area as 'schoolArea'
-- from tb_student_info tsi;

-- union
-- union all 중복 제거지만 비효율적이라 사용 x 
-- 필요하다면 꼭 필요한 것만 사용한다면 union all사용

-- dual 가상 테이블이다. 직접 만들지 않았으나 테스트용으로 만들어져 있음





