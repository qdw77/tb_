insert into tb_student_info(
	student_name,
    student_grade, 
    student_school_area,
    student_school,
    student_zipcode,
    addr    
)
select '홍길순', 
	student_grade,
	student_school_area, 
	'대전 여자 고등학교',
	'111111',
	'대전 중구'
from tb_student_info
where student_id = 1;

select *
from tb_student_info;