package member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class MemberServiceImpl implements MemberService{
	MemberDAO mDAO = new MemberDAO();
	Scanner sc = new Scanner(System.in);

	@Override
	public void startProgram() {
		// TODO Auto-generated method stub
		int count = 0;

		while (true) {

			int choice = printMenu();

			switch(choice) {
			case 1 :
				displayMsg(" 1. 회원 정보 등록");
				insertMember();
				break;
			case 2 :
				displayMsg(" 2. 회원 정보 수정");
				updateMember();
				break;
			case 3 :
				displayMsg(" 3. 회원 정보 삭제");
				deletMember();
				break;
			case 4 :
				displayMsg("4. 회원 정보 출력(이름)"); 
				printMember();
				break;
			case 5 :
				displayMsg(" 5. 회원 전체 정보 출력");
				FindAllMembers();
				break;
			case 6 :
				displayMsg(" 6. 프로그램 종료~!");
				count++;
				break;
			default: 
				System.out.println("잘못된 숫자가 입력됨. 1~6사이의 숫자 입력 가능");
				break;
			}

			if (choice == 6) {
				break;
			}
		}
	}

	//0번 프로그램 출력
	public int printMenu() {
		displayMsg(" ===== 회원 관리 프로그램 =====");
		displayMsg(" 1. 회원 정보 등록");
		displayMsg(" 2. 회원 정보 수정");
		displayMsg(" 3. 회원 정보 삭제");
		displayMsg(" 4. 회원 정보 출력(이름)"); 
		displayMsg(" 5. 회원 전체 정보 출력"); 
		displayMsg(" 6. 프로그램 종료");
		displayMsg("[선택] : ");

		int choice = sc.nextInt();

		return choice;
	}


	//메세지 출력용 
	public void displayMsg(String msg) {
		System.out.println(msg);
	}

	// 1. 회원 정보 등록	
	public void insertMember() {
		//회원정보를 입력할
		Member member = new Member();
		System.out.println("회원 아이디를 입력해 주세요. : ");
		String memberId = sc.next();

		System.out.println("회원 비밀번호 : ");
		String memberPw = sc.next();

		System.out.println("회원 이름 : ");
		String memberName = sc.next();

		System.out.println("생년월일((ex) 20010110) : ");
		String memberBirth = sc.next();

		System.out.println("이메일 주소를 입력 해주세요 : ");
		String email= sc.next();

		System.out.println("연락처 정보를 입력해 주세요.");
		String phon = sc.next();

		//member에 회원정보 셋팅
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		member.setMemberName(memberName);
		member.setMemberBirth(memberBirth);
		member.setMemberEmail(email);
		member.setMemberPhone(phon);

		int chk = 0;
		chk = mDAO.insertMember(member);

		if(chk > 0) {
			System.out.println("등록되었습니다.");
		}else {
			System.out.println("등록에 실패 하였습니다.");
		}
	}

	//	

	public void updateMember() {
		System.out.println("수정할 회원 이름을 입력하세요>>>>>>");
		sc.nextLine();
		String title = sc.nextLine();

		List<HashMap<String, Object>> memberList = new ArrayList();
		memberList = mDAO.printSearchMember(title);
		System.out.println("");

		for(int i = 0; i<memberList.size(); i++) {
			System.out.print(memberList.get(i).get("member_id")+"\t");
			System.out.print(memberList.get(i).get("member_pw")+"\t");
			System.out.print(memberList.get(i).get("member_name")+"\t");
			System.out.print(memberList.get(i).get("member_birth")+"\t");
			System.out.print(memberList.get(i).get("member_phone")+"\t");
			System.out.println(memberList.get(i).get("member_email"));

		}
		//HashMap<사용시 string/int 전부 사용 가능하지만 순수한 int는 형변환 필요
		System.out.println("수정할 회원의 순번을 입력하세요>>>>>>");
		int num = sc.nextInt();

		int memberIdx =Integer.parseInt(memberList.get(num-1).get("member_idx").toString());
		System.out.println("변경될 회원 이름을 입력하세요>>>>>>");
		sc.nextLine();

		String upbateId = sc.nextLine();
		int chk =0;
		chk = mDAO.updateMember(memberIdx,upbateId);
		if(chk > 0) {
			System.out.println("수정되었습니다.");
		}else{
			System.out.println("수정에 실패하였습니다.");
		}
	}


	//	
	public void deletMember() {
		System.out.println("삭제할 회원 이름을 입력하세요>>>>>>");
		sc.nextLine();
		String memberName = sc.nextLine();

		List<HashMap<String, Object>> memberList = mDAO.printSearchMember(memberName);
		for(int i = 0; i<memberList.size(); i++) {
			System.out.print(memberList.get(i).get("member_id")+"\t");
			System.out.print(memberList.get(i).get("member_name")+"\t");
			System.out.print(memberList.get(i).get("member_birth")+"\t");
			System.out.println(memberList.get(i).get("member_phone"));

		}
		System.out.println("삭제할 회원의 순번을 입력하세요.>>>>>>");
		int num = sc.nextInt();
		int memberIdx = Integer.parseInt(memberList.get(num-1).get("member_idx").toString());

		int chk = 0;
		chk = mDAO.deletMember(memberIdx);
		if(chk > 0) {
			System.out.println("도서가 삭제되었습니다.");
		}else{
			System.out.println("도서 삭제에 실패하였습니다.");
		}
	}


	public void printMember(){

		System.out.print("조회할 회원 아이디를 입력해 주세요 : ");
		String findId = sc.next();
		List<HashMap<String, Object>> memberList = mDAO.printSearchMember(findId);

		boolean flag = false;

		for (int i=0; i<memberList.size(); i++) {

			System.out.print(memberList.get(i).get("member_id")+"\t");
			System.out.print(memberList.get(i).get("member_name")+"\t"); 
			System.out.print(memberList.get(i).get("member_birth")+"\t");
			System.out.println(memberList.get(i).get("member_phone"));
		}

	};


	public void FindAllMembers() {
		List<HashMap<String, Object>> memberList = new ArrayList();
		memberList = mDAO.findAllMembers();

		System.out.println("이름 이메일 주소 연락처");

		for(int i = 0; i<memberList.size(); i++) {
			System.out.print(memberList.get(i).get("member_id")+"\t");
			System.out.print(memberList.get(i).get("member_name")+"\t");
			System.out.print(memberList.get(i).get("member_email")+"\t");
			System.out.println(memberList.get(i).get("member_phone"));

		}		
	}

}
