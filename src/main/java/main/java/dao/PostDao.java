package main.java.dao;

import main.java.db.DBConnection;
import main.java.entity.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
    // post 생성
    public void save(Post post) {
        String sql = "INSERT INTO post (title, content, author, created_at) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            // 동적 파라미터(?) 할당
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setString(3, post.getAuthor());

            pstmt.setTimestamp(4, Timestamp.valueOf(post.getCreatedAt()));
            // LocalDateTime 타입인 post.getCreatedAt()을
            // >> JDBC에서 사용 가능한 java.sql.Timestamp 타입으로 변환해줌

            // 실행
            pstmt.executeUpdate();

        } catch (SQLException e) {
            // serr: 오류 형식으로 출력됨
            System.err.println("[ERROR] 게시글 저장 실패: " + e.getMessage());
        }
    }

    // 전체 조회: id 기준으로 내림차순 정렬
    public List<Post> findAll() {
        String sql = "SELECT * FROM post ORDER BY id DESC";
        List<Post> postList = new ArrayList<>();

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql) // 전체 조회 시 반환값 생성됨
        ){
            while(rs.next()){
                Post post = new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("author"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                        // toLocalDateTime(): java.sql.Timestamp 타입을 >> LocalDateTime으로 변환
                );
                postList.add(post);
            }
        }catch (SQLException e) {
            System.err.println("[ERROR] 게시글 전체 조회 실패: " + e.getMessage());
        }
        return postList;
    }

    // 단건 조회: 동적 파라미터(id)에 따라 조회
    public Post findById(int id) {
        String sql = "SELECT * FROM post WHERE id = ?";

        try( Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
             // 동적 파라미터를 사용하기 때문에 ResultSet으로 반환값을 바로 담을 수 없다.
        ) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery(); // 동적 파라미터 할당 후에 반환값 담기

            if(rs.next()){
                return new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("author"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
            }

        }catch(SQLException e){
            System.err.println("[error] 게시글 상세 조회 실패: " + e.getMessage());
        }
        return null; // SQL 처리에 대한 오류는 없으면서 해당 id의 데이터가 없는 경우 null 반환
    }

    // post 데이터 수정: 동적 파라미터(id)에 따라 title과 content를 수정
    public void update(Post post) {
        String sql = "UPDATE post SET title = ?, content = ? WHERE id = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setInt(3, post.getId());
            pstmt.executeUpdate();

        }catch(SQLException e) {
            System.err.println("[error] 게시글 수정 실패: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM post WHERE id = ?";

        try ( Connection conn = DBConnection.getConnection();
              PreparedStatement pstmt = conn.prepareStatement(sql);

        ) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        }catch(SQLException e){
            System.err.println("[error] 게시글 삭제 실패: " + e.getMessage());
        }
    }


}
