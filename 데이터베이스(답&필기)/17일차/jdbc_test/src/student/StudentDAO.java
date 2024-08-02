package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentDAO {
	final String driver = "org.mariadb.jdbc.Driver";
	final String db_ip = "localhost";
	final String db_port = "3306";
	final String db_name = "jdbc_tesst";
	final String db_url = 
			"jdbc:mariadb://"+db_ip+":"+db_port+"/"+db_name;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 학생 정보 등록
	public int insertStudent(HashMap<String, Object> paramMap) {
		int resultChk = 0;

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
			String sql ="INSERT into tb_student_info(\r\n"
					+ "student_name,\r\n"
					+ "student_grade,\r\n"
					+ "student_school,\r\n"
					+ "student_addr,\r\n"
					+ "student_phone)\r\n"
					+ "values(?,?,?,?,?);";

			System.out.println(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("studentName").toString());
			pstmt.setInt(2,Integer.parseInt(paramMap.get("studentGrade").toString()) );
			pstmt.setString(3, paramMap.get("studentSchool").toString());
			pstmt.setString(4, paramMap.get("studentAddr").toString());
			pstmt.setString(5, paramMap.get("studentPhone").toString());

			resultChk = pstmt.executeUpdate();

		}
		catch (SQLException e) {
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

	// 학생 성적 등록
	public int insertScore(HashMap<String, Object> paramMap) {
		int resultChk = 0;

		return resultChk;
	}

	// 전체 학생 조회
	public List<HashMap<String, Object>> printAllStudent(){
		List<HashMap<String, Object>> studentList = new ArrayList();


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
			String sql ="select \r\n"
					+ "student_name as studentName,\r\n"
					+ "student_grade as studentGrade ,\r\n"
					+ "student_school as studentSchool,\r\n"
					+ "student_addr as studentAddr,\r\n"
					+ "student_phone as studentPhone\r\n"
					+ "from tb_student_info ;";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				HashMap<String, Object> rsMap = new HashMap<String, Object>();
				rsMap.put("studentName", rs.getString("studentName"));
				rsMap.put("studentGrade", rs.getInt("studentGrade"));
				rsMap.put("studentSchool", rs.getString("studentSchool"));
				rsMap.put("studentAddr", rs.getString("studentAddr"));
				rsMap.put("studentPhone", rs.getString("studentPhone"));

				studentList.add(rsMap);

			}
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
		return studentList;

	}

	// 학생이름으로 학생 정보 조회
	public List<HashMap<String, Object>> printSearchStudent(String studentName){
		List<HashMap<String, Object>> studentList = new ArrayList();

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
			String sql ="SELECT student_idx as studentIdx,\r\n"
					+ "student_name as studentName,\r\n"
					+ "student_grade as studentGrade ,\r\n"
					+ "student_school as studentSchool,\r\n"
					+ "student_addr as studentAddr,\r\n"
					+ "student_phone as studentPhone\r\n"
					+ "FROM tb_student_info\r\n"
					+ "WHERE student_name like concat('%', ? , '%');";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentName);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				HashMap<String, Object> rsMap = new HashMap<String, Object>();
				rsMap.put("studentIdx", rs.getInt("studentIdx"));
				rsMap.put("studentName", rs.getString("studentName"));
				rsMap.put("studentGrade", rs.getInt("studentGrade"));
				rsMap.put("studentSchool", rs.getString("studentSchool"));
				rsMap.put("studentAddr", rs.getString("studentAddr"));
				rsMap.put("studentPhone", rs.getString("studentPhone"));


				studentList.add(rsMap);
			}

			//px값
		}catch (SQLException e) {
			// TODO: handle exception
			//			catch<예외처리<오류 등의 이유로 실행 불가
			System.out.println("error :" + e);
		}
		finally { try {

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
		return studentList;
	}



	// 학생 정보 수정

	public int updateStudent(HashMap<String, Object> paramMap) {
		int resultChk = 0;

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
			String sql ="UPDATE tb_student_info \n";
			int updateChoice = Integer.parseInt(paramMap.get("updateChoice").toString());

			switch(updateChoice) {
			case 1: {
				sql +="set student_name = ?\n";
				break;

			}
			case 2: {
				sql +="set student_grade = ?\n";
				break;
			}
			case 3: {
				sql +="set student_school = ?\n";
				break;
			}
			case 4: {
				sql +="set student_addr = ?\n";
				break;
			}
			case 5: {
				sql +="set student_phone = ?\n";
				break;
			}

			//			if(updateChoice ==1) {
			//				
			//			}else if (updateChoice ==2) {
			//				
			//			}else {
			//				
			//			}

			//			default:{
			//				
			//			}

			}
			sql += "where student_idx = ?;";
			pstmt= conn.prepareStatement(sql);

			pstmt.setString(1, paramMap.get("upadrteContents").toString());
			pstmt.setInt(2, Integer.parseInt(paramMap.get("studentIdx").toString()));


			//			pstmt.setString(1,"값");

			resultChk = pstmt.executeUpdate();



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

	


	

	// 학생 성적 수정
	public int updateScore(HashMap<String, Object> paramMap) {
		int resultChk = 0;



		return resultChk;
	}

	// 학생 정보 삭제
	public int deleteStudent(int studentIdx) {
		int resultChk = 0;


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
			String sql ="DELETE from tb_student_info where student_idx = ?;";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentIdx);

			resultChk = pstmt.executeUpdate();

		}
		catch (SQLException e) {
			// TODO: handle exception
			//			catch<예외처리<오류 등의 이유로 실행 불가
			System.out.println("error :" + e);
		}
		finally { try {

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


	// 학생 성적 삭제
	public int deleteScore(HashMap<String, Object> paramMap) {
		int resultChk = 0;

		return resultChk;
	}
}
