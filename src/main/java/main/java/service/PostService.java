package main.java.service;

import main.java.dao.PostDao;
import main.java.entity.Post;

import java.util.List;

public class PostService {
    // 비즈니스 로직 처리
    // View, Manager(Controller 역할), Service
    // : 뷰에서 사용자의 요청을 받아
    // : Manager(controller)에서 요청에 해당하는 Service(비즈니스 로직)으로 연결

    // cf) 비즈니스 로직에서 Dao(DB와의 연결, Modle)에 접근
    private final PostDao postDao = new PostDao();

    // View에서 사용자로부터 직접 입력받을 값을 매개변수로
    public boolean writePost(String title, String content, String author){
        if (title == null || title.isBlank() || title.length() > 100) return false;
        if(content == null || content.isBlank()) return false;
        if (author == null || author.isBlank() || author.length() > 100) return false;

        Post post = new Post(title, content, author);
        postDao.save(post);
        return true;
    }

    public List<Post> getAllPost() {
        return postDao.findAll();
    }

    public Post getPostDetail(int id) {
        return postDao.findById(id);
    }

    public boolean updatePost(int id, String newTitle, String newContent) {
        if (newTitle == null || newTitle.isBlank() || newTitle.length() > 10) return false;
        if (newContent == null || newContent.isBlank()) return false;

        Post post = postDao.findById(id);
        post.setTitle(newTitle);
        post.setContent(newContent);

        postDao.update(post);
        return true;
    }

    public void deletePost(int id) {
        postDao.delete(id);
    }


}
