select  member_idx,
		member_id,
        member_pw,
        member_name,
        member_birth,
        member_phone,
        member_email
from tb_member_info
WHERE member_name like concat('%', '?', '%');

select*from tb_student_score;

insert into tb_student_score
(score_season,
score_semester,
score_exam_type,
score_subject,
score_point,
student_idx) 
values (?,?,?,?,?,?);

select student.student_name as studentName, 
		student.student_grade as studentGrade,
		student.student_school as studentSchool,
		student.student_addr as studentAddr,
		student.student_phone as studentPhone,
		score.score_season as scoreseason,
		score.score_semester as scoresemester,
		score.score_exam_type as scoreexamtype,
		score.score_subject as scoresubject,
		score.score_point as scorepoint
from tb_student_info student 
inner join tb_student_score score
on score.student_idx = student.student_idx;
-- inner join - on값으로 두개 값 연결

select student.student_idx as studentidx,
		student.student_name as studentName, 
		student.student_grade as studentGrade,
		student.student_school as studentSchool,
		student.student_addr as studentAddr,
		student.student_phone as studentPhone,
		score.score_season as scoreseason,
		score.score_semester as scoresemester,
		case when score.score_exam_type = 'M' then '중간고사'
        when score.score_exam_type = 'F' then '기말고사' end as scoreexamtype,
		score.score_subject as scoresubject,
		score.score_point as scorepoint
from tb_student_info student 
left join tb_student_score score
on score.student_idx = student.student_idx
where student.student_name like concat('%',?,'%');


UPDATE tb_student_score SET score_point = ? WHERE score_idx =?;

select  student.student_idx as studentidx,
		student.student_name as studentName, 
		student.student_grade as studentGrade,
		student.student_school as studentSchool,
		student.student_addr as studentAddr,
		student.student_phone as studentPhone,
        score.score_idx as scoreidx,
		score.score_season as scoreseason,
		score.score_semester as scoresemester,
		case when score.score_exam_type = 'M' then '중간고사'
        when score.score_exam_type = 'F' then '기말고사' end as scoreexamtype,
		score.score_subject as scoresubject,
		score.score_point as scorepoint
from tb_student_info student 
left join tb_student_score score
on score.student_idx = student.student_idx
where student.student_name like concat('%',?,'%');



