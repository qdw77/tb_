select * from tb_parking_info;
select * from tb_student_info;
select * from tb_student_score;
select * from tb_member_info;
select * from tb_book_info;	

select * 
from tb_parking_info
where parking_number = 1;

update tb_parking_info
set parking_yn = 'Y',
	parking_car_number =?,
    parking_date = current_timestamp()
    where parking_number = ?;
    
    UPDATE tb_parking_info
    SET parking_yn = 'Y',
		parking_number = ?,
		parking_date = CURRENT_TIMESTAMP() 
    WHERE parking_number = ?;

select parking_car_number as parkingNumber,	
		parking_yn as parkingYn
from tb_parking_info
where parking_number = 1;
-- parking_number>파킹넘버
-- parking_location_x/parking_location_y >가로세로
-- parking_car_number>차량넘버
-- parking_date>주차 날짜
-- parking_yn>주차유무


select * from tb_parking_history;

parking_history_idx int(9)
parking_location int
parking_car_number VARCHAR(6)
parking_type CHAR(1)
parking_time TIMESTAMP(6);

insert into tb_parking_history(
			parking_location,
            parking_car_number,
            parking_type,
            parking_time
            )Values(?,?,?,current_timestamp());
            
            select parking_number as parkingNumber,
				   parking_car_number as parkingCarNumber,
                   parking_yn as parkingYn
			from tb_parking_info
            where parking_number = ?;





