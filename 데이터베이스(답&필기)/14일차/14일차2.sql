SELECT book_id,
    book_title,
    book_price,
    book_author,
    book_pubilsher,
    book_pubYear,
    book_isbn,
    book_page,
    create_date,
    update_date
FROM tb_book_info
where book_title like concat('%',?,'%');