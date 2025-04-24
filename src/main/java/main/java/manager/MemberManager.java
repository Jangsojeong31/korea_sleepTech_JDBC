package main.java.manager;

import main.java.dao.MemberDao;
import main.java.entity.Member;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MemberManager {
    public void run() throws SQLException {
        Scanner sc = new Scanner(System.in);
        MemberDao memberDao = new MemberDao();

        while (true) {
            System.out.println("1. 사용자 단건 조회");
            System.out.println("2. 사용자 전체 조회");
            System.out.println("3. 사용자 정보 추가");
            System.out.println("4. 사용자 정보 수정");
            System.out.println("5. 사용자 삭제");
            System.out.println("6. 프로그램 종료");
            System.out.print("메뉴 선택: ");

            String selectedMenu = sc.nextLine();

            switch (selectedMenu) {
                case "1":
                    viewMemberById(sc, memberDao);
                    break;
                case "2":
                    viewAllMembers(memberDao);
                    break;
                case "3":
                    addMemeber(sc, memberDao);
                    break;
                case "4":
                    updateMember(sc, memberDao);
                    break;
                case "5":
                    deleteMember(sc, memberDao);
                    break;
                case "6":
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도해주세요");
                    break;
            }
        }
    }

    // 1. 사용자 단건 조회
    private void viewMemberById(Scanner sc, MemberDao memberDao) throws SQLException {
        System.out.println("조회할 사용자 ID 입력: ");
        int id = Integer.parseInt(sc.nextLine());

        Member member = memberDao.getMemberById(id);

        if (member != null) {
            System.out.println(member);
        } else {
            System.out.println("해당 ID의 사용자를 찾을 수 없습니다.");
        }
    }
    // 2. 사용자 전체 조회
    private void viewAllMembers(MemberDao memberDao) throws SQLException {
        List<Member> members = memberDao.findAll();

        if (members.isEmpty()) {
            System.out.println("등록된 사용자가 없습니다.");
        } else {
//            members.forEach(member -> System.out.println(member.toString()));
            members.forEach(System.out::println); // 축약형

        }
    }
    // 3. 사용자 생성
    private void addMemeber(Scanner sc, MemberDao memberDao) throws SQLException {
        System.out.println("새로운 사용자 이름 입력: ");
        String name = sc.nextLine();

        System.out.println("새로운 사용자 이메일 입력: ");
        String email = sc.nextLine();

        // cf) DB의 auto_increment 값 전달 시
        // : -1을 전달
        // - 아직 DB에 저장되지 않음을 의미하는 값
        Member newMember = new Member(-1, name, email);
        memberDao.addMember(newMember);

        System.out.println("사용자가 성공적으로 추가되었습니다.");

    }
    // 4. 사용자 수정
    private void updateMember(Scanner sc, MemberDao memberDao) throws SQLException {
        System.out.print("수정할 사용자 id: ");
        int id = Integer.parseInt(sc.nextLine());

        Member member = memberDao.getMemberById(id);

        if (member == null) {
            System.out.println("해당 ID의 사용자를 찾을 수 없습니다.");
            return;
        }

        System.out.println("사용자의 새 이름 (변경하지 않으려면 Enter)");
        String newName = sc.nextLine();
        System.out.println("사용자의 새 이메일 (변경하지 않으려면 Enter)");
        String newEmail = sc.nextLine();

        if(!newName.isEmpty()) { // 변경하는 경우
            member.setName(newName);
        }
        if(!newEmail.isEmpty()) { // 변경하는 경우
            member.setEmail(newEmail);
        }

        memberDao.updateMember(member);
        System.out.println("사용자 정보가 성공적으로 업데이트 되었습니다.");
    }
    // 5. 사용자 삭제
    private void deleteMember(Scanner sc, MemberDao memberDao) throws SQLException {
        System.out.print("삭제할 사용자 ID 입력: ");
        int id = Integer.parseInt(sc.nextLine());

        memberDao.deleteMember(id);
        System.out.println("사용자가 성공적으로 삭제되었습니다.");
    }
}
