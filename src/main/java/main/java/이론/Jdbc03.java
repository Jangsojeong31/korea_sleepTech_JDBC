package main.java.이론;

// == SQL 실행 및 결과 처리 ==
// Statement VS PreparedStatement VS ResultSet

// === SQL 실행 흐름 ===
// 1. Connection 객체 생성
// 2. SQL문 준비 (SELECT, INSERT 등)
// 3. Statement, PreparedStatement 객체 생성 - SQL 실행 객체 생성
// 4. SQL 실행 (executeQuery, executeUpdate)
// 5. 결과 처리 (ResultSet 또는 영향 줄 확인)

// Statement VS PreparedStatement
// 1) SQL 작성 방식
// Statement: SQL를 직접 문자열로 작성
// PreparedStatement: + ?를 사용해 파라미터 처리

// 2) 보안
// Statement: SQL Injection 위험 있음
// PreparedStatement: SQL Injection 방지

// 3) 성능
// Statement: 매번 컴파일
// PreparedStatement: 사전 컴파일 >> 더 빠름

// == executeQuery VS executeUpdate ==
// 1) executeQuery
// : select 문과 같은 쿼리문 실행 시 사용
// - 쿼리 실행 후 결과를 ResultSet 객체로 반환: 반환된 ResultSet 객체를 통해 결과를 가져옴

// 2) executeUpdate
// : Insert, Update, Delete과 같은 dml(data manipulation language)에서 실행 결과로 영향 받은 레코드 수를 반환
// - 반환 타입이 int
// - 행의 개수를 반환하기 때문에 rs(ResultSet) 사용할 필요 없다.

// == ResultSet 결과 처리 방법 ==
// 1) rs.next()
// : 다음 행 존재 여부 확인 및 이동

// 2) rs.getInt("컬럼명") / getString("컬럼명") / getDate("컬럼명")
// : 정수값 / 문자열 값 / 날짜 값(DATE) 가져오기


public class Jdbc03 {

}
