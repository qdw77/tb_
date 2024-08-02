select*from tb_member_info;

INSERT into tb_student_info(
student_name,
student_grade,
student_school,
student_addr,
student_phone)
values(?,?,?,?,?);

select 
student_name as studentName,
student_grade as studentGrade ,
student_school as studentSchool,
student_addr as studentAddr,
student_phone as studentPhone
from tb_student_info ;

SELECT student_idx as studentIdx,
student_name as studentName,
student_grade as studentGrade ,
student_school as studentSchool,
student_addr as studentAddr,
student_phone as studentPhone
FROM tb_student_info
WHERE student_name like concat('%', ? , '%');


update tb_student_info
set student_grade = '2'
where student_idx ='1';

select*from tb_student_info;

UPDATE tb_student_info
set student_name = ?
where student_idx=?;




