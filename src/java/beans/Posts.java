/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author c0690651
 */
public class Posts {
    
    private List<Post> posts;
    private Post currentPost;

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
    
    public String add(){
        currentPost = new Post(-1, -1, "", null, "");
        return "addPost";
    }
    
    public String delete() throws SQLException{
        Connection conn = DBUtilis.getConnection();
        String query = "DELETE FROM posts WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, currentPost.getId());
        pstmt.executeUpdate();
        
        getPostsFromDB();
        currentPost = new Post(-1, -1, "", null, "");
        return "index";
    }
    
    private void getPostsFromDB() throws SQLException{
        
        Connection conn = DBUtilis.getConnection();
        String query = "SELECT * FROM posts";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        posts = new ArrayList<>();
        while (rs.next()){
            Post p = new Post(rs.getInt("id"), rs.getInt("userId"), rs.getString("title"), rs.getTimestamp("createdTime"), rs.getString("contents"));
            posts.add(p);
        }
    }
    
    
}
