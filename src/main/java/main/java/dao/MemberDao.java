package main.java.dao;

// DAO 패턴 적용
// 1. DAO 패턴 정의
// : Data Access Object
// - DB와의 접근 로직을 하나의 클래스로 '캡슐화'하여 애플리케이션의 다른 부분과
// , DB 처리 코드를 분리하는 설계 패턴

// 2. DAO 패턴 사용 목적
// 1) DB 처리만 전담하는 DAO 클래스 생성
// 2) DAO 클래스만 수정하면 DB 처리 수정이 가능
//      >> 구조 명확, 유지보수 용이

// 3. 구조 설계
// 1) db - DBConnection.java (DB 연결 전담)
// 2) entity - Member.java (데이터를 담는 객체)
// 3) dao - MemberDao.java (DB 처리 로직 담당)
// 4) manager - MemberManager.java (실행 및 테스트)


// MemberDAO 클래스
// : Member 테이블에 대한 CRUD 작업 처리

import main.java.db.DBConnection;
import main.java.entity.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    // 1. Read(단건): 아이디(id) 값을 기준으로 사용자 정보를 가져오는 메서드
    public Member getMemberById(int id) throws SQLException {
        // +) get + 사용자 정보 + By + 찾고자 하는 컬럼명
        // DBConnection을 통해 DB 연결을 가져옴
        Connection conn = DBConnection.getConnection(); // static 메서드: 클래스명으로 호출

        // 실행할 SQL문
        // : id를 조건으로 사용자 정보 조회 (동적 파라미터)
        String sql = "SELECT * FROM member WHERE id = ?";

        // SQL 쿼리 실행
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 실행 객체에 파라미터 값 설정
        pstmt.setInt(1, id); // getMemberById 메서드의 매개변수를 인자로 전달

        // SQL 쿼리 실행 >> 결과를 ResultSet으로 저장
        ResultSet rs = pstmt.executeQuery();

        Member member = null; // Member 객체 초기화
        if(rs.next()) { // 결과 집합에 다음 행이 있으면 true 반환
            // Member 객체 생성 및 결과 저장
            member = new Member(rs.getInt("id")
                    , rs.getString("name")
                    , rs.getString("email"));
        }

        rs.close();
        pstmt.close();
        conn.close();

        return member;

    }

    // 2. Read(전체): 모든 사용자 정보를 조회하는 메서드(리스트 이용)
    public List<Member> findAll() throws SQLException {
        Connection conn = DBConnection.getConnection();

        // 모든 사용자 정보를 조회하는 SQL 쿼리문
        String sql = "SELECT * FROM member";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        List<Member> members = new ArrayList<>();

        // 결과 집합에서 다음 행이 있는 동안 반복
        while (rs.next()) {
            Member member = new Member(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email")
            );

            // 리스트에 Member 객체 추가
            members.add(member);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return members;
    }

    // 3. Create: 사용자 정보 추가 메서드
    public void addMember (Member member) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String sql = "INSERT INTO member (name, email) VALUES (?, ?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, member.getName());
        pstmt.setString(2, member.getEmail());

        pstmt.executeUpdate(); // 반환값 있음 (수정된 레코드 수: int 값)

        pstmt.close();
        conn.close();

    }

    // 4. Update: 사용자 정보 수정 메서드 (이름 & 이메일)
    public void updateMember(Member member) throws SQLException {
        Connection conn = DBConnection.getConnection();

        // 업데이트 할 이름과 이메일이 존재하는지 확인
        // : null이 아니고 빈 문자열이 아닐 경우 true
        boolean updateName = member.getName() != null && !member.getName().isEmpty();
        boolean updateEmail = member.getEmail() != null && !member.getEmail().isEmpty();

        // SQL 쿼리 작성
        // cf) StringBuilder
        //      : Java에서 가변 문자열을 만드는 클래스
        //      : 하나의 객체에 문자열을 계속 추가하거나 수정이 가능
        // - java.lang 패키지의 클래스

        // +) 자바의 String 문자열 타입은 '불변(immutable)'
//        String str = "Hello";
//        str += "JDBC";    // str에 "JDBC"를 추가한 것이 아니라 기존 문자열은 버려지고 새로운 String 객체가 생성!

        StringBuilder sql = new StringBuilder("UPDATE member SET ");

        // 이름이 업데이트된 경우
        if (updateName) {
            sql.append("name = ?, ");
        }

        // 이메일이 업데이트된 경우
        if (updateEmail) {
            sql.append("email = ?, ");
        }

        // 마지막에 붙는 콤마와 공백을 제거
        // 마지막에 ,가 오면 SQL 문법에 오류 발생
        // 위에서 name 또는 email 중 최소 하나는 true이다.
        sql.deleteCharAt(sql.length() - 2);

        // WHERE 절 추가 (어떤 사용자인지 특정 - id 기준)
        sql.append("WHERE id = ?");

        // SQL 쿼리를 기반으로 SQL 실행
        PreparedStatement pstmt = conn.prepareStatement(sql.toString()); // StringBuilder 타입인 sql 을 String 타입으로 바꿈

        // 파라미터 인덱스 설정 (1부터 시작)
        int parameterIndex = 1;

        if (updateName) {
            pstmt.setString(parameterIndex++, member.getName());
        }

        if (updateEmail) {
            pstmt.setString(parameterIndex++, member.getEmail());
        }

        // 마지막 파라미터는 WHERE 절의 id 값
        pstmt.setInt(parameterIndex, member.getId());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    // 4. Delete: 사용자 정보 삭제 (id 기준)
    public void deleteMember(int id) throws SQLException {
        // try-with-resources 구문 (JAVA 7 부터 도입)
        // : 자원을 자동으로 닫아주는 기능
        // try(자원객체 선언) {
        //    자원 사용
        // } catch (...) {
        //    예외처리
        // }
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM member WHERE id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
