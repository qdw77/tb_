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
			String sql ="insert into tb_student_score\r\n"
					+ "(score_season,\r\n"
					+ "score_semester,\r\n"
					+ "score_exam_type,\r\n"
					+ "score_subject,\r\n"
					+ "score_point,\r\n"
					+ "student_idx) \r\n"
					+ "values (?,?,?,?,?,?);";

			System.out.println(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("season").toString());
			pstmt.setInt(2,Integer.parseInt(paramMap.get("semester").toString()) );
			pstmt.setString(3, paramMap.get("examType").toString());
			pstmt.setString(4, paramMap.get("subject").toString());
			pstmt.setInt(5, Integer.parseInt(paramMap.get("point").toString()));
			pstmt.setInt(6, Integer.parseInt(paramMap.get("studentIdx").toString()));

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
		//List<HashMap<String, Object>> studentList = new ArrayList();
		try {
			String sql ="select student.student_name as studentName, \r\n"
					+ "		student.student_grade as studentGrade,\r\n"
					+ "		student.student_school as studentSchool,\r\n"
					+ "		student.student_addr as studentAddr,\r\n"
					+ "		student.student_phone as studentPhone,\r\n"
					+ "		score.score_season as scoreseason,\r\n"
					+ "		score.score_semester as scoresemester,\r\n"
					+ "		score.score_exam_type as scoreexamtype,\r\n"
					+ "		score.score_subject as scoresubject,\r\n"
					+ "		score.score_point as scorepoint\r\n"
					+ "from tb_student_info student \r\n"
					+ "inner join tb_student_score score\r\n"
					+ "on score.student_idx = student.student_idx;";
			//join/lift 등은 on 값 필수 
			//if(idx > 0) {
			//sql +="where student.student_idx = ?;";
			//}else{				
			//sql += ";";
			//}

			pstmt = conn.prepareStatement(sql);
			//if(idx > 0)
			//rs = pstmt{
			//pstmt.setInt(1, idx);
			//}
			rs = pstmt.executeQuery();

			while(rs.next()) {
				HashMap<String, Object> rsMap = new HashMap<String, Object>();
				rsMap.put("studentName", rs.getString("studentName"));
				rsMap.put("studentGrade", rs.getInt("studentGrade"));
				rsMap.put("studentSchool", rs.getString("studentSchool"));
				rsMap.put("studentAddr", rs.getString("studentAddr"));
				rsMap.put("studentPhone", rs.getString("studentPhone"));
				rsMap.put("scoreSeason", rs.getString("scoreSeason"));
				rsMap.put("scoreSemester", rs.getInt("scoreSemester"));
				rsMap.put("scoreExamType", rs.getString("scoreExamType"));
				rsMap.put("scoreSubject", rs.getString("scoreSubject"));
				rsMap.put("scorePoint", rs.getInt("scorePoint"));


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

	//	// 전체 학생 조회
	//	public List<HashMap<String, Object>> printAllStudent(){
	//		List<HashMap<String, Object>> studentList = new ArrayList();
	//
	//
	//		try {
	//			Class.forName(driver);
	//			conn = DriverManager.getConnection(db_url, "root", "4623");
	//			if(conn != null) {
	//				System.out.println("접속성공");
	//			}
	//		}catch(ClassNotFoundException e) {
	//			System.out.println("드라이버 로드 실패");
	//			e.printStackTrace();
	//		}catch(SQLException e) {
	//			System.out.println("접속 실패");
	//			e.printStackTrace();
	//		}
	//		try {
	//			String sql ="select student.student_name as studentName, \r\n"
	//					+ "		student.student_grade as studentGrade,\r\n"
	//					+ "		student.student_school as studentSchool,\r\n"
	//					+ "		student.student_addr as studentAddr,\r\n"
	//					+ "		student.student_phone as studentPhone,\r\n"
	//					+ "		score.score_season as scoreseason,\r\n"
	//					+ "		score.score_semester as scoresemester,\r\n"
	//					+ "		score.score_exam_type as scoreexamtype,\r\n"
	//					+ "		score.score_subject as scoresubject,\r\n"
	//					+ "		score.score_point as scorepoint\r\n"
	//					+ "from tb_student_info student \r\n"
	//					+ "lift join tb_student_score score\r\n"
	//					+ "on score.student_idx = student.student_idx;";
	//
	//			pstmt = conn.prepareStatement(sql);
	//			rs = pstmt.executeQuery();
	//
	//			while(rs.next()) {
	//				HashMap<String, Object> rsMap = new HashMap<String, Object>();
	//				rsMap.put("studentName", rs.getString("studentName"));
	//				rsMap.put("studentGrade", rs.getInt("studentGrade"));
	//				rsMap.put("studentSchool", rs.getString("studentSchool"));
	//				rsMap.put("studentAddr", rs.getString("studentAddr"));
	//				rsMap.put("studentPhone", rs.getString("studentPhone"));
	//				rsMap.put("scoreSeason", rs.getString("scoreSeason"));
	//				rsMap.put("scoreSemester", rs.getInt("scoreSemester"));
	//				rsMap.put("scoreExamType", rs.getString("scoreExamType"));
	//				rsMap.put("scoreSubject", rs.getString("scoreSubject"));
	//				rsMap.put("scorePoint", rs.getInt("scorePoint"));
	//
	//
	//				studentList.add(rsMap);
	//
	//			}
	//		}catch (SQLException e) {
	//			// TODO: handle exception
	//			//			catch<예외처리<오류 등의 이유로 실행 불가
	//			System.out.println("error :" + e);
	//		}finally {
	//			try {
	//
	//				if(pstmt != null) {
	//					pstmt.close();
	//					//close>다 닫아주어야 함
	//				}
	//				if(conn != null && conn.isClosed()) {
	//					conn.close();
	//				}
	//
	//			}catch(SQLException e) {
	//				e.printStackTrace();
	//			}
	//		}
	//		return studentList;
	//
	//	}



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
			String sql ="select  student.student_idx as studentIdx,\r\n"
					+ "		student.student_name as studentName, \r\n"
					+ "		student.student_grade as studentGrade,\r\n"
					+ "		student.student_school as studentSchool,\r\n"
					+ "		student.student_addr as studentAddr,\r\n"
					+ "		student.student_phone as studentPhone,\r\n"
					+ "        score.score_idx as scoreIdx,\r\n"
					+ "		score.score_season as scoreSeason,\r\n"
					+ "		score.score_semester as scoreSemester,\r\n"
					+ "		case when score.score_exam_type = 'M' then '중간고사'\r\n"
					+ "        when score.score_exam_type = 'F' then '기말고사' end as scoreExamType,\r\n"
					+ "		score.score_subject as scoreSubject,\r\n"
					+ "		score.score_point as scorePoint\r\n"
					+ "from tb_student_info student \r\n"
					+ "left join tb_student_score score\r\n"
					+ "on score.student_idx = student.student_idx\r\n"
					+ "where student.student_name like concat('%',?,'%');";
			//where & concat > 세트

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
				rsMap.put("scoreIdx", rs.getInt("scoreIdx"));
				rsMap.put("scoreSeason", rs.getString("scoreSeason"));
				rsMap.put("scoreSemester", rs.getInt("scoreSemester"));
				rsMap.put("scoreExamType", rs.getString("scoreExamType"));
				rsMap.put("scoreSubject", rs.getString("scoreSubject"));
				rsMap.put("scorePoint", rs.getInt("scorePoint"));

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
		//소스의 중복은 최소화

		try {
			String sql ="UPDATE tb_student_info \n";
			int updateChoice = Integer.parseInt(paramMap.get("updateChoice").toString());
			//sql += "set 1=1";
			switch(updateChoice) {
			case 1: {
				sql +="set student_name = ?\n";
				//sql +=", student_name = ?\n";
				break;

			}

			case 2: {
				sql +="set student_school = ?\n";				
				break;
			}

			case 3: {
				sql +="set student_grade = ?\n";
				break;
			}

			case 4: {
				sql +="set student_phone = ?\n";				
				break;
			}

			case 5: {
				sql +="set student_addr = ?\n";
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
			//catch<예외처리<오류 등의 이유로 실행 불가
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

	// 학생이름으로 학생 정보 조회(수정, 삭제 검색용)
	public List<HashMap<String, Object>> printSearchStudentInfo(String studentName){
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
			String sql = "SELECT  student.student_idx AS studentIdx,"
					+ "		student.student_name AS studentName,\r\n"
					+ "		student.student_grade AS studentGrade,\r\n"
					+ "		student.student_school AS studentSchool,\r\n"
					+ "		student.student_addr AS studentAddr,\r\n"
					+ "		student.student_phone AS studentPhone\r\n"
					+ "FROM tb_student_info student\r\n"
					+ "WHERE student.student_name like concat('%', ?, '%');";

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

		return studentList;
	}






	// 학생 성적 수정
	public int updateScore(int scoreIdx, int updateScore) {
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
			String sql ="UPDATE tb_student_score SET score_point = ? WHERE score_idx =?;";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, updateScore);
			pstmt.setInt(2, scoreIdx);

			resultChk = pstmt.executeUpdate();

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
		return resultChk;
	}

	// 학생 정보 삭제

	//학생 성적 조회&삭제
	public int deleteStudentScore(int studentIdx) {
		// TODO Auto-generated method stub
		//성적삭제
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
			String sql ="delete from tb_student_score \r\n"
					+ "where student_idx =? ; ";

			pstmt = conn.prepareStatement(sql);
			//변경X			
			pstmt.setInt(1,studentIdx);
			//String 값은 작아서 season 입력 불가/반대는 가능
			resultChk = pstmt.executeUpdate();
			//변경X

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
		return resultChk;
	}


	//성적이 몇개나 있는지 체크
	public int selectStudentScoreCnt(int studentIdx) {

		// TODO Auto-generated method stub
		int cnt = 0;


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
			String sql =" select count(student_idx) as cnt from tb_student_score\r\n"
					+ " where student_idx =?;";

			pstmt = conn.prepareStatement(sql);
			//변경X			
			pstmt.setInt(1, studentIdx);
			//String 값은 작아서 season 입력 불가/반대는 가능
			rs = pstmt.executeQuery();

			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			//변경X

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
		return cnt;
	}

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
			String sql ="delete from tb_student_info where student_idx = ?;";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentIdx);

			resultChk = pstmt.executeUpdate();

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
		return resultChk;
	}



	// 학생 성적 삭제
	public int deleteScore(HashMap<String, Object> scoreMap) {
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
			String sql ="delete from tb_student_score \r\n"
					+ "where score_season =? \r\n"
					+ "and score_semester =? \r\n"
					+ " and score_exam_type =? \r\n"
					+ " and score_subject =? \r\n"
					+ " and student_idx =?; ";

			pstmt = conn.prepareStatement(sql);
			//변경X			
			pstmt.setString(1, scoreMap.get("season").toString());
			pstmt.setInt(2, Integer.parseInt(scoreMap.get("semester").toString()));
			pstmt.setString(3, scoreMap.get("examType").toString());
			pstmt.setString(4, scoreMap.get("subject").toString());
			pstmt.setInt(5, Integer.parseInt(scoreMap.get("studentIdx").toString()));
			//String 값은 작아서 season 입력 불가/반대는 가능
			resultChk = pstmt.executeUpdate();
			//변경X

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
		return resultChk;
	}




	//	list 뽑는 것

	public List<HashMap<String, Object>> printSearchScore(int idx){

		List<HashMap<String, Object>> scoreList = new ArrayList();

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
			String sql = "select score_idx as scoreIdx,\r\n"
					+ "score_season as scoreSeason,\r\n"
					+ "score_semester as scoreSemester,\r\n"
					+ "score_exam_type as scoreExamType,\r\n"
					+ "score_subject as scoreSubject,\r\n"
					+ "score_point as scorePoint\r\n"
					+ " from tb_student_score \r\n"
					+ " where student_idx = ?;";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();


			while(rs.next()) {
				HashMap<String, Object> rsMap = new HashMap<String, Object>();
				rsMap.put("scoreIdx", rs.getInt("scoreIdx"));
				rsMap.put("scoreSeason", rs.getString("scoreSeason"));
				rsMap.put("scoreSemester", rs.getInt("scoreSemester"));
				rsMap.put("scoreExamType", rs.getString("scoreExamType"));
				rsMap.put("scoreSubject", rs.getString("scoreSubject"));
				rsMap.put("scorePoint", rs.getInt("scorePoint"));

				scoreList.add(rsMap);

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

		return scoreList;
	}




}
