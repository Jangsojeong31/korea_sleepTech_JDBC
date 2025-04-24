package main.java.entity;

// +) Lombok
// 1) Lombok 플러그인
// 2) Dependency 설정
//      // https://mvnrepository.com/artifact/org.projectlombok/lombok
//compileOnly 'org.projectlombok:lombok:1.18.38'
// 3) Enable annotation 설정

// Entity
// : 데이터베이스 테이블과 매핑되는 클래스
// 1:1

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
// : JDBC 또는 ORM (DB와 JAVA를 객체화하여 연동하는 체계)에서는
// , 빈 객체를 먼저 만들고 setter로 값을 넣는 방식을 사용
// >> 따라서 AllArgsConstructor도 NoArgsConstructor가 전제되어야 함 (기본 + 전체 필드 생성자)
@Getter
@Setter
@ToString
public class Member {
    private int id;
    private String name;
    private String email;

//    public Member (int id, String name, String email) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//    }
//
//    public int getId() {return id;}
//    public String getName() {return name;}
//    public String getEmail() {return email;}


}
