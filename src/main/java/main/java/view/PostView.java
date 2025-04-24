package main.java.view;

import main.java.entity.Post;

import java.util.List;
import java.util.Scanner;

public class PostView {
    private final Scanner sc = new Scanner(System.in);

    public int showMenu() {
        System.out.println("\n===게시판 메뉴===");
        System.out.println("1. 게시글 작성");
        System.out.println("2. 게시글 전체 조회");
        System.out.println("3. 게시글 단건 조회");
        System.out.println("4. 게시글 수정");
        System.out.println("5. 게시글 삭제");
        System.out.println("0. 프로그램 종료");
        System.out.println("선택");
        return Integer.parseInt(sc.nextLine());
    }

    public void showPostList(List<Post> posts){
        System.out.println("\n[게시글 목록]");
        for(Post post: posts){
            System.out.println(post);
        }
    }

    public String input(String label) {
        System.out.print(label + ": ");
        return sc.nextLine();
    }
    // input("제목")
    // >> 제목: (입력값)

    public void showPostDetail(Post post) {
        if (post == null) {
            System.out.println("해당 게시글이 존재하지 않습니다.");
            return;
        }

        System.out.println("\n[ 게시글 상세 보기]");
        System.out.println(post);
    }


}
