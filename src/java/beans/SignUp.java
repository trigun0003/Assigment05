/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bellahuang
 */
@ManagedBean
@SessionScoped
public class SignUp {
    private String username;
    private String password1;
    private String password2;

    public SignUp() {
        username = null;
        password1 = null;
        password2 = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    public String signUp() throws SQLException{
        String page = "signUp";
        if (password1.equals(password2)){
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
            String url ="jdbc:mysql://localhost:3306/blog";
            Connection conn = DriverManager.getConnection(url, "root", "");
        
            String query = "INSERT INTO users(username, passhash) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password1);
            pstmt.executeUpdate();
            
            Users.getUsersFromDB();
            page = "login";
        }
        return page; 
    }
    
}
