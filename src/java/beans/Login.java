/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author bellahuang
 */
@ManagedBean
@SessionScoped
public class Login {
    private String username;
    private String password;
    private boolean loggedIn;
    private User currentUser;
    
    public Login(){
        username = null;
        password = null;
        loggedIn = false;
        currentUser = null;
    }
    
    public String login(){
        String page = "login";
        for(User u : Users.getInstance().getUsers()){
            if(username.equals(u.getUsername()) && password.equals(u.getPassword())){
                loggedIn = true;
                currentUser = u;
                page = "index";
            }
        }
        return page;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getCurrentUser() {
        return currentUser;
    }
    
}
