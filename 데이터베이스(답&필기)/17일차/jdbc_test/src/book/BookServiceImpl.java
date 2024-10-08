package book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class BookServiceImpl implements BookService {

	BookDAO bookDAO = new BookDAO();
	Scanner sc = new Scanner(System.in);
	//BookDAO<

	@Override
	public void startProgram() {
		// TODO Auto-generated method stub

		while (true) {

			int choice = printMenu();

			switch(choice) {
			case 1 :
				System.out.println(" 1. 도서 정보 등록");
				insertBook();
				break;

			case 2 :
				System.out.println(" 2. 도서 정보 수정");
				updateBook();
				break;

			case 3 :
				System.out.println(" 3. 도서 정보 삭제");
				deleteBook();
				break;

			case 4 :
				System.out.println("4. 도서 정보 출력(도서명)"); 
				printBook();
				break;

			case 5 :
				System.out.println(" 5. 도서 전체 정보 출력");
				PrintAllBooks();
				break;

			case 6 :
				System.out.println(" 6. 프로그램 종료~!");
				break;

			default: 
				//System.out.println("잘못된 숫자가 입력됨. 1~6사이의 숫자 입력 가능");
				break;
			}
			if(choice ==6) {
				break;
			}

		}
	}


	@Override
	public int printMenu() {
		// TODO Auto-generated method stub
		System.out.println(" ===== 도서 관리 프로그램 =====");
		System.out.println(" 1. 도서 정보 등록");
		System.out.println(" 2. 도서 정보 수정");
		System.out.println(" 3. 도서 정보 삭제");
		System.out.println(" 4. 도서 정보 출력(도서명)"); 
		System.out.println(" 5. 도서 전체 정보 출력"); 
		System.out.println(" 6. 프로그램 종료");
		System.out.println("[선택] : ");

		int choice = sc.nextInt();

		return choice;
		//		반환>printMenu 이동>choice>해당 번호 값 ex>insertBook>값
	}

	public void insertBook() {

		System.out.println("도서명을 입력하세요>>>>>>");
		sc.nextLine();
		String title = sc.nextLine();
		System.out.println("도서가격을 입력하세요>>>>>>");
		int price = sc.nextInt();
		System.out.println("저자를 입력하세요>>>>>>");
		sc.nextLine();
		String author = sc.nextLine();
		System.out.println("출판사를 입력하세요>>>>>>");
		String publisher = sc.nextLine();
		System.out.println("출판년도를 입력하세요>>>>>>");
		String pubYear  = sc.nextLine();
		System.out.println("ISBN를 입력하세요>>>>>>");	
		String isbn  = sc.nextLine();
		System.out.println("총 페이지 수를 입력하세요>>>>>>");
		int page  = sc.nextInt();

		BookInfo bookInfo = new BookInfo(); 
		//		BookInfo bookInfo = new BookInfo(); <초기화 실행 후 값 넣기
		bookInfo.setTitle(title);
		bookInfo.setPrice(price);
		bookInfo.setAuthor(author);
		bookInfo.setPublisher(publisher);
		bookInfo.setPubYear(pubYear);
		bookInfo.setIsbn(isbn);
		bookInfo.setPage(page);

		int resultChk =0;
		//		resultChk 결과 값 담기 bookDAO<조작하는 것을 써먹는 곳>
		resultChk = bookDAO.insertBook(bookInfo);
		if(resultChk > 0) {
			System.out.println("도서가 등록되었습니다.");
		}else{
			System.out.println("도서 등록에 실패하였습니다.");
		}

	}

	public void PrintAllBooks() {
		List<HashMap<String, Object>> bookList = new ArrayList();
		bookList = bookDAO.printAllBooks();
		System.out.println("도서명\t저자\t출판사\t출판년도");
		//HashMap>초기화 List는 ArrayList로 선언 > printAllBooks<값 담기
		for(int i = 0; i<bookList.size(); i++) {
			System.out.print(bookList.get(i).get("book_title")+"\t");
			System.out.print(bookList.get(i).get("book_author")+"\t");
			System.out.print(bookList.get(i).get("book_pubilsher")+"\t");
			System.out.print(bookList.get(i).get("book_pubYear"));
			System.out.println(bookList.get(i).get("create_date")+"\t");


		}
	}

	public void printBook() {
		List<HashMap<String, Object>> bookList = new ArrayList();
		//		결과 값이 여러개일 수 있으므로 List 값을 준다
		System.out.println("검색할 도서명을 입력하세요>>>>>>");
		sc.nextLine();
		String title =sc.nextLine();

		bookList = bookDAO.printSearchBooks(title);
		//DAO에서 전체적으로 훑으며 실행


		System.out.println("도서명\t저자\t출판사\t출판년도\t등록일");
		for(int i = 0; i<bookList.size(); i++) {
			System.out.print(bookList.get(i).get("book_title")+"\t");
			System.out.print(bookList.get(i).get("book_author")+"\t");
			System.out.print(bookList.get(i).get("book_pubilsher")+"\t");
			System.out.print(bookList.get(i).get("book_pubYear")+"\t");
			System.out.println(bookList.get(i).get("create_date"));


		}
	}

	public void deleteBook() {
		System.out.println("도서명을 입력하세요>>>>>>");
		sc.nextLine();
		String title = sc.nextLine();

		int resultChk =0;
		//resultChk 결과 값 담기 bookDAO<조작하는 것을 써먹는 곳>
		resultChk = bookDAO.deleteBook(title);
		if(resultChk > 0) {
			System.out.println("도서가 삭제되었습니다.");
		}else{
			System.out.println("도서 삭제에 실패하였습니다.");
		}



	}

	public void updateBook() {
		System.out.println("도서명을 입력하세요>>>>>>");
		sc.nextLine();
		String title = sc.nextLine();

		List<HashMap<String, Object>> bookList = new ArrayList();
		bookList = bookDAO.printSearchBooks(title);
		System.out.println("도서명\t저자\t출판사\t출판년도\t등록일");

		for(int i = 0; i<bookList.size(); i++) {
			System.out.print(bookList.get(i).get("book_title")+"\t");
			System.out.print(bookList.get(i).get("book_price")+"\t");
			System.out.print(bookList.get(i).get("book_author")+"\t");
			System.out.print(bookList.get(i).get("book_pubilsher")+"\t");
			System.out.print(bookList.get(i).get("book_pubYear")+"\t");
			System.out.println(bookList.get(i).get("create_date"));

		}
		//HashMap<사용시 string/int 전부 사용 가능하지만 순수한 int는 형변환 필요
		System.out.println("수정할 도서의 순번을 입력하세요>>>>>>");
		int num = sc.nextInt();

		//System.out.println(bookList.get(num-1).get("book_id"));
		//System.out.println(bookList.get(num-1));
		//int bookId = (int) bookList.get(num-1).get("book_id");
		int bookId =Integer.parseInt(bookList.get(num-1).get("book_id").toString());
		System.out.println("변경될 도서명을 입력하세요>>>>>>");
		sc.nextLine();

		String upbateTitle = sc.nextLine();
		int resultChk =0;
		resultChk = bookDAO.updateBook(bookId, upbateTitle);
		if(resultChk > 0) {
			System.out.println("도서가 수정되었습니다.");
		}else{
			System.out.println("도서 수정에 실패하였습니다.");
		}
	}
}




