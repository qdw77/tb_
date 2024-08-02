-- select * from tb_student_info_test;
-- -- 외래키 연결은 학생 테이블에서

-- select * from tb_school_info;

-- select concat('a', '-', 'b');
-- concat 을 많이 사용하는 이유는 like 문에서 많이 사용하기 때문

-- select * from tb_student_info
-- where addr like '%중구%';
-- -원래 like 값

-- select * from tb_student_info where addr like concat('%', student_school_area,'%')
-- and student_school_area = '중구';
-- -concat 사용

-- SELECT REPLACE('Hello_World', 'World', 'MySQL');
-- REPLACE 문자열 내의 기존문자를 대체문자로 변경

-- select substring('문자열내에서 지정한 시작부분부터 길이 값 만큼 반환
-- ',8,3);
-- substring 특정부분만 뽑아냄

-- select if(trim('   hello   ') = 'hello', true,false);
-- true 값은 1/ false 값은 0

-- select if(ltrim ('   hello   ' )= ' hello  ','참','거짓');

-- -- 실습

-- -- 7
-- select concat(student_school,'_',student_grade,'학년_',student_name)from tb_student_info;


-- 8
-- select replace(school_phone,'-',' ')from tb_school_info;
-- select * from tb_school_info;

-- 9
-- select substring(school_phone,4,4)from tb_school_info;
-- select substring('010-4628-2379',5,4);

-- 10
-- select substring('010-4628-2379',5,10);
-- select replace('010-4628-2379','010-','');
-- select right('010-4628-2379',9);

-- 11
-- SELECT CONCAT(substring('010-4628-2379', 1, 3), '-', substring('010-4628-2379', 5,4), '-', substring('010-4628-2379', 10, 4));

-- 현재 날짜 시간
-- select now();

-- select current_timestamp();
-- current_timestamp-V조회하는 것 위주 사용

-- history-만든시간
-- -- select * from data_insert_history;
-- 만들기
-- -- insert into data_insert_history(table_name)
-- -- values('test');

-- 날짜
-- 현재 날짜 및 시간
-- select curdate();
-- select curtime();

select date('2024-07-16');
select year('2024-05-16'), month('2024-07-16');
select monthname('2024-05-16');
select dayofmonth('2024-05-16'), day('2024-07-16');

select hour(now()), minute(now()), second(now());

select datediff(now(), '2024-07-15'), datediff('2024-07-15',now());

select datediff('2024-07-18',now());

select timediff('2024-07-18 11:30:00',now());

-- 일수+/-
select date_add(now(),interval 30 day);
select date_add(now(),interval 30 month);

select date_sub(now(),interval 30 day);
select date_sub(now(),interval 30 month);

-- 날짜 형식 지정(소문자/대문자 다름)
-- 시간h./분i/초s 시간 입력시 필수

-- y소문자/대문자
select date_format(now(), '%y');
select date_format(now(), '%Y');
-- m,d소문자/대문자
select date_format(now(), '%Y-%m-%d');
select date_format(now(), '%Y-%M-%d');
select date_format(now(), '%Y-%m-%D');

-- 날짜 입력 (대소문자)
select date_format('2024-01-01', '%Y-%m-%D');

-- 시간 추가 (대소문자)
select date_format('2024-01-01', '%Y-%m-%D %H');
select date_format('2024-01-01', '%Y-%m-%D %h');

-- 현재 날짜에서 시간 추가 (대소문자)
select date_format(now(), '%Y-%m-%D %H');
select date_format(now(), '%Y-%m-%D %h');

-- 날짜와 시간 추가(대소문자)
select date_format('2024-01-01 14:30:00', '%Y-%m-%d %H');
select date_format('2024-01-01 14:30:00', '%Y-%m-%d %h');

-- 분,초 추가(대소문자)
select date_format('2024-01-01 14:30:00', '%Y-%m-%d %H:%i:%s');
select date_format('2024-01-01 14:30:00', '%Y-%m-%d %H:%i:%S');
select date_format('2024-01-01 14:30:00', '%Y-%m-%d %H:%I:%S');
select date_format('2024-01-01 14:30:00', '%Y-%m-%d %H:%I:%s');
select date_format('2024-01-01 14:30:00', '%Y%m%d%H%I%S');

-- 현재시간
select date_format(now(), '%Y-%m-%d %H:%i:%s');

-- 실습
-- 12
select datediff(now(),'2001-01-10');

-- 13
select date_format(now(), '%Y%m%d %H:%i:%s');

-- if
select if(student_grade = 1,'신입생','재학생')from tb_student_info;

-- case
-- select
--	 case when 조건 then 반환값 
-- 		  when 조건 then 반환값 
--     	  else 반환값 end
-- from 테이블명;

select *,
	 case when student_grade = 1 then '신입생' 
		  when student_grade = 2 then '재학생' 
    	  else '수험생' end
from tb_student_info;


-- 실습14
select *,
	case when score_point >= 90 then '우수'
		else '보통' end
		as '우수여부'
from tb_score;

select *, if(score_point >= 90, '우수', '보통') as '우수여부'
from tb_score;


-- 15(if문으로 사용불가 if 문은 한가지의 조건만 사용가능)/case/between 사용

select score_subject, score_point,
	case when score_point<80 then '노력필요'
    when score_point<90 then '보통'
		else '우수' end
		as '우수여부'
from tb_score;

select score_subject, score_point,
	case when score_point>=90 then '우수'
    when score_point>= 80 then '보통'
		else '노력필요' end
		as '우수여부'
from tb_score;

select score_subject, score_point,
case when score_point between 70 and 79 then '노력필요'
when score_point between 80 and 89 then '보통'
else '우수' end as '우수여부'
from tb_score;





