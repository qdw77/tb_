SELECT school.school_id as schoolId,
	school.school_name as schoolName,
    school.school_area as schoolArea,
    student.student_name as studentName,
    '재학중' as graduate
    FROM tb_school_info school
    INNER JOIN tb_student_info student
    on school.school_id = student.school_id
    and student.student_graduate_yn = 'N'
    where school.school_name ='우송고등학교';
