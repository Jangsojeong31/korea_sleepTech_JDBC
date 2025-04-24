package main.java.db;

// DBConnection 클래스
// : DB 연결을 관리하는 역할

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // DB 연결 URL을 상수로 선언: MySQl DB 연결
    // +) localhost(로컬 컴퓨터), 3306(MySQL 서버 기본 포트 번호)
//    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_db";
    private static final String URL = "jdbc:mysql://localhost:3306/board_db";

    // DB 사용자 이름 / 비밀번호 상수로 선언
    // +) DB 접속 시 사용할 사용자 계정 이름 / 비밀번호
    private static final String USER = "root";
    private static final String PASSWORD = "susan429@";

    // == DB 연결 상태를 반환 == //
    // : SQL 관련 에러 발생을 방지
    public static Connection getConnection() throws SQLException {
        // DB 연결 시도 + 연결 객체 반환
        // : URL + 사용자 이름 + 비밀번호를 인자로 전달
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }



}
