/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bellahuang
 */
@ManagedBean
@ApplicationScoped
public class Users {
    private static List<User> users;
    private static Users instance;
    
    public Users() throws SQLException{
        getUsersFromDB();
        instance = this;
    }
    
    public static void getUsersFromDB() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url ="jdbc:mysql://localhost:3306/blog";
        Connection conn = DriverManager.getConnection(url, "root", "");
        
        String query = "SELECT * FROM users";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        users = new ArrayList<>();
        while(rs.next()){
            User u = new User(rs.getInt("id"),rs.getString("username"),rs.getString("passhash"));
            users.add(u);
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public static Users getInstance() {
        return instance;
    }

    
    
    public String getUsernameById(int id){
        for (User u : users){
            if (u.getId() == id)
                return u.getUsername();
        }
        return null;
    }
}
