select student_id, sum(score_point), avg(score_point) 
from tb_score
group by student_id;

