package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        // 1. DB 접속 정보
        final String URL = "jdbc:mysql://localhost:3306/jdbc_db";
//      jdbc:mysql://localhost:3306/jdbc_db
//      -> JDBC 프로토콜:사용하는DBMS://서버주소:포트번호(MySQL 기본 3306)/DB이름
        String user = "root";
        String password = "susan429@";

        Connection conn = null; // DB 연결 객체
        Statement stmt = null;  // DB 실행 객체
        ResultSet rs = null;    // DB 결과 객체

        try {
            // 1. 드라이버 로딩
            // : JDBC 드라이버 클래스를 메모리에 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. DB 연결
            // : 실제로 MySQL DB 접속 시도 (성공 시 Connection 객체를 반환, 아니면 null 반환)
            conn = DriverManager.getConnection(URL, user, password);
            System.out.println("DB 연결 성공");

            // == DB 전체 데이터 조회(READ) ==
            String sql = "SELECT * FROM member";
            stmt = conn.createStatement(); // Statement 사용
            rs = stmt.executeQuery(sql);   // Select 실행

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println(id + " | " + name + " | " + email);
            }

        }catch (Exception e) {
            System.out.println("DB 연결 실패");
            e.printStackTrace();
        }finally {
            // 3. 연결 종료
            try {
                if (conn != null && stmt != null && rs != null) { // DB Connection이 존재한다면 - 자원 누수를 막기 위해 자원 해제를 호출
                    conn.close();
                    stmt.close();
                    rs.close();
                    System.out.println("DB 연결 종료");
                }
            }catch (Exception e) {

            }
        }

    }
}