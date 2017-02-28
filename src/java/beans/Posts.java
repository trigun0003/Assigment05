/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author c0690651
 */
@ManagedBean
@ApplicationScoped
public class Posts {

    private List<Post> posts;
    private Post currentPost;

    public Posts() throws SQLException {
        currentPost = new Post(-1, -1, "", null, "");
        getPostsFromDB();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Post getCurrentPost() {
        return currentPost;
    }

    public void setCurrentPost(Post currentPost) {
        this.currentPost = currentPost;
    }

    public Post getPostByTitle(String title) {
        Post pp = null;
        for (Post p : posts) {
            if (title.equals(p.getTitle())) {
                pp = p;
            }
        }
        return pp;
    }

    public Post getPostById(int id) {
        Post pp = null;
        for (Post p : posts) {
            if (id == p.getId()) {
                pp = p;
            }
        }
        return pp;
    }
    
    public String viewPost(Post post){
        currentPost = post;
        return "viewPost";
    }

    public String add() {
        currentPost = new Post(-1, -1, "", null, "");
        return "addPost";
    }
    
    public String addPost(User user) throws SQLException{
        
        Connection conn = DBUtilis.getConnection();
        
        String query = "INSERT INTO posts (userId, title, createdTime, contents) VALUES (?,?,NOW(),?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, user.getId());
        pstmt.setString(2, currentPost.getTitle());
        pstmt.setString(3, currentPost.getContents());
        pstmt.executeUpdate();
        
        getPostsFromDB();
        currentPost = getPostByTitle(currentPost.getTitle());
        return "viewPost";
    }
    
    public String editPost() throws SQLException{
        
        Connection conn = DBUtilis.getConnection();
        
        String query = "UPDATE posts SET title = ?, contents = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, currentPost.getTitle());
        pstmt.setString(2, currentPost.getContents());
        pstmt.setInt(3, currentPost.getId());
        pstmt.executeUpdate();
        
        getPostsFromDB();
        return "viewPost";
        
    }
    
    public String cancel() throws SQLException{
        int id = currentPost.getId();
        getPostsFromDB();
        currentPost = getPostById(id);
        return "viewPost";
    }

    public String delete() throws SQLException {
        Connection conn = DBUtilis.getConnection();
        String query = "DELETE FROM posts WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, currentPost.getId());
        pstmt.executeUpdate();

        getPostsFromDB();
        currentPost = new Post(-1, -1, "", null, "");
        return "index";
    }

    private void getPostsFromDB() throws SQLException {

        Connection conn = DBUtilis.getConnection();
        String query = "SELECT * FROM posts";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        posts = new ArrayList<>();
        while (rs.next()) {
            Post p = new Post(rs.getInt("id"), rs.getInt("user_id"), rs.getString("title"), rs.getDate("created_time"), rs.getString("contents"));
            posts.add(p);
        }
    }

}
