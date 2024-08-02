select student_school, student_grade, count(student_id)
from tb_student_info 
group by student_school, student_grade;