package Users;

import java.lang.reflect.Constructor;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String role;

    public User(int id, String username, String email, String password, String role){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String role, String password, String email, String username) {
        this.role = role;
        this.password = password;
        this.email = email;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getRole(){
        return role;
    }
}
