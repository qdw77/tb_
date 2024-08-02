
-- from tb_score;

 
select score_subject, sum(score_point)/12 , avg(score_point)from tb_score
group by score_subject ;


-- select score_subject, sum(score_point) from tb_score
-- group by score_subject ;



-- select score_subject, count(score_subject) from tb_score
-- group by score_subject ;


-- 앞 명령문 / 구문 대문자로 작성 들여쓰기 중요 