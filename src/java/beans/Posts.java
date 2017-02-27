/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;

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
    
    
    
}
