/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author C0687631
 */

@Named
@SessionScoped
public class Login implements Serializable {
    private String username;
    private String password;
    private boolean loggedIn;
    private User currentUser;
    
    
    public Login()
    {
        username = null;
        password = null;
        loggedIn = false;
        currentUser = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public String login()
    {
        String passhash = DBUtilis.hash(password);
        
        for (User u : Users.getInstance().getUsers()) {
            if(username.equals(u.getUsername()) && passhash.equals(u.getPasshash())) 
            {
                loggedIn = true;
                currentUser = u;
                return "index";
            }
        }
        currentUser = null;
        loggedIn = false;
        return "index";
    }
    
    
}
