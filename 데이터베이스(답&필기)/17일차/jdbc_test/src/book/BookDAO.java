package book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookDAO {

	//	조작하는 것을 써먹는 곳

	final String driver = "org.mariadb.jdbc.Driver";
	final String db_ip = "localhost";
	final String db_port = "3306";
	final String db_name = "jdbc_tesst";
	final String db_url = 
			"jdbc:mariadb://"+db_ip+":"+db_port+"/"+db_name;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;


	//1번 도서등록
	public int insertBook(BookInfo bookInfo) {	
		//BookInfo bookInfo 이대로 값 넣어줄 것
		int resultChk = 0;
		//int resultChk = 0;/return resultChk;<이 값 때문에 public int로
		//return 값이 필수
		//<초기화

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(db_url, "root", "4623");
			if(conn != null) {
				System.out.println("접속성공");
			}
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println("접속 실패");
			e.printStackTrace();
		}


		try {
			String sql ="INSERT into tb_book_info(\r\n"
					+ "	book_title,\r\n"
					+ "    book_price,\r\n"
					+ "    book_author,\r\n"
					+ "    book_pubilsher,\r\n"
					+ "    book_pubYear,\r\n"
					+ "    book_isbn,\r\n"
					+ "    book_page\r\n"
					+ ")\r\n"
					+ "VALUES(\r\n"
					+ "	?,?,?,?,?,?,?\r\n"
					+ ");";



			System.out.println(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,bookInfo.getTitle());
			pstmt.setInt(2,bookInfo.getPrice());
			pstmt.setString(3,bookInfo.getAuthor());
			pstmt.setString(4,bookInfo.getPublisher());
			pstmt.setString(5,bookInfo.getPubYear());
			pstmt.setString(6,bookInfo.getIsbn());
			pstmt.setInt(7,bookInfo.getPage());


			resultChk = pstmt.executeUpdate();
			//executeUpdate > SQL에서 실행하는 역할

		}catch (SQLException e) {
			// TODO: handle exception
			//			catch<예외처리<오류 등의 이유로 실행 불가
			System.out.println("error :" + e);
		}finally {
			try {

				if(pstmt != null) {
					pstmt.close();
					//close>다 닫아주어야 함
				}
				if(conn != null && conn.isClosed()) {
					conn.close();
				}

			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return resultChk;
	}




	//2. 도서 정보 수정

	public int updateBook(int bookId, String upbateTitle) {

		int resultChk = 0;
		//<초기화

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(db_url, "root", "4623");
			if(conn != null) {
				System.out.println("접속성공");
			}
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println("접속 실패");
			e.printStackTrace();
		}


		try {
			String sql ="    update tb_book_info\r\n"
					+ "    set book_title = ?\r\n"
					+ "    where book_id = ?;";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,upbateTitle);
			pstmt.setInt(2, bookId);


			resultChk = pstmt.executeUpdate();
			//executeUpdate > SQL에서 실행하는 역할

		}catch (SQLException e) {
			// TODO: handle exception
			//			catch<예외처리<오류 등의 이유로 실행 불가
			System.out.println("error :" + e);
		}finally {
			try {

				if(pstmt != null) {
					pstmt.close();
					//close>다 닫아주어야 함
				}
				if(conn != null && conn.isClosed()) {
					conn.close();
				}

			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return resultChk;

	}



	//3. 도서 정보 삭제
	public int deleteBook(String title) {
		int resultChk = 0;
		//<초기화

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(db_url, "root", "4623");
			if(conn != null) {
				System.out.println("접속성공");
			}
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println("접속 실패");
			e.printStackTrace();
		}


		try {
			String sql ="DELETE from tb_Book_info\r\n"
					+ "where book_title = ?;";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);


			resultChk = pstmt.executeUpdate();
			//executeUpdate > SQL에서 실행하는 역할

		}catch (SQLException e) {
			// TODO: handle exception
			//			catch<예외처리<오류 등의 이유로 실행 불가
			System.out.println("error :" + e);
		}finally {
			try {

				if(pstmt != null) {
					pstmt.close();
					//close>다 닫아주어야 함
				}
				if(conn != null && conn.isClosed()) {
					conn.close();
				}

			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return resultChk;

	}



	//4번 도서 정보 출력 (도서명)
	public List<HashMap<String, Object>> printSearchBooks(String title){
		List<HashMap<String,Object>> bookList = new ArrayList();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(db_url, "root", "4623");
			if(conn != null) {
				System.out.println("접속성공");
			}
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println("접속 실패");
			e.printStackTrace();
		}

		try {

			String sql = "SELECT book_id,\r\n"
					+ "    book_title,\r\n"
					+ "    book_price,\r\n"
					+ "    book_author,\r\n"
					+ "    book_pubilsher,\r\n"
					+ "    book_pubYear,\r\n"
					+ "    book_isbn,\r\n"
					+ "    book_page,\r\n"
					+ "    create_date,\r\n"
					+ "    update_date\r\n"
					+ "FROM tb_book_info\r\n"
					+ "where book_title like concat('%',?,'%');";


			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			// ?값은 <pstmt.setString(1, title);

			rs = pstmt.executeQuery();


			while(rs.next()) {
				HashMap<String, Object> rsMap = new HashMap<String, Object>();
				//조회결과 HashMap에 추가
				rsMap.put("book_id", rs.getInt("book_id"));
				rsMap.put("book_title", rs.getString("book_title"));
				rsMap.put("book_price", rs.getInt("book_price"));
				rsMap.put("book_author", rs.getString("book_author"));
				rsMap.put("book_pubilsher", rs.getString("book_pubilsher"));
				rsMap.put("book_pubYear", rs.getString("book_pubYear"));
				rsMap.put("book_isbn", rs.getString("book_isbn"));
				rsMap.put("book_page", rs.getInt("book_page"));
				rsMap.put("create_date", rs.getString("create_date"));
				rsMap.put("update_date", rs.getString("update_date"));



				bookList.add(rsMap);

			}
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println("error :" + e);
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}

				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null && conn.isClosed()) {
					conn.close();
				}

			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return bookList;
	}



	//5번 도서 전체 정보 출력
	public List<HashMap<String,Object>> printAllBooks(){
		List<HashMap<String,Object>> bookList = new ArrayList();

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(db_url, "root", "4623");
			if(conn != null) {
				System.out.println("접속성공");
			}
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println("접속 실패");
			e.printStackTrace();
		}

		try {

			String sql = "SELECT book_id,\r\n"
					+ "    book_title,\r\n"
					+ "    book_price,\r\n"
					+ "    book_author,\r\n"
					+ "    book_pubilsher,\r\n"
					+ "    book_pubYear,\r\n"
					+ "    book_isbn,\r\n"
					+ "    book_page,\r\n"
					+ "    create_date,\r\n"
					+ "    update_date\r\n"
					+ "FROM tb_book_info "
					+ "WHERE create_date;";


			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();


			while(rs.next()) {
				HashMap<String, Object> rsMap = new HashMap<String, Object>();
				//조회결과 HashMap에 추가
				rsMap.put("book_id", rs.getInt("book_id"));
				rsMap.put("book_title", rs.getString("book_title"));
				rsMap.put("book_price", rs.getInt("book_price"));
				rsMap.put("book_author", rs.getString("book_author"));
				rsMap.put("book_pubilsher", rs.getString("book_pubilsher"));
				rsMap.put("book_pubYear", rs.getString("book_pubYear"));
				rsMap.put("book_isbn", rs.getString("book_isbn"));
				rsMap.put("book_page", rs.getInt("book_page"));
				rsMap.put("create_date", rs.getString("create_date"));
				rsMap.put("update_date", rs.getString("update_date"));

				bookList.add(rsMap);

			}
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println("error :" + e);
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}

				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null && conn.isClosed()) {
					conn.close();
				}

			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return bookList;
	}







}


