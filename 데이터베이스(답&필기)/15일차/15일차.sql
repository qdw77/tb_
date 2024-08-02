DELETE from tb_Book_info
where
	book_title = ?;
    
    update tb_book_info
    set book_title = ?
    where book_id = ?;