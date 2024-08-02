package book;

public class BookController {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub		
		BookService bookService = new BookServiceImpl();
		
		bookService.startProgram();
//		서비스 안 프로그램으로 BookService

	}

}
