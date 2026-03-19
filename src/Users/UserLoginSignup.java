package Users;

import DBConnection.DbConnectivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserLoginSignup {

    public boolean isEmailExist(String email) throws SQLException{
        try {
            Connection con = DbConnectivity.getConnection();

            String query = "select * from users where email = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean register(User user){
        Scanner sc = new Scanner(System.in);

        try{
            System.out.println("Sign Up for Job Portal.");

            Connection con = DbConnectivity.getConnection();

            if(isEmailExist(user.getEmail())){
                System.out.println("Email already exist! Try again.");
                return false;
            }

            String query = "INSERT INTO users(username, email, password) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            int rows = ps.executeUpdate();
            if(rows > 0){
                System.out.println("Successfully Registered!");
            } else{
                System.out.println("Failed To Register!");
                return false;
            }

            // Role selection
            System.out.println("Now Select Your Role: ");
            System.out.println("1. User");
            System.out.println("2. Recruiter");

            int roleInput = sc.nextInt();
            sc.nextLine();

            String role = "";
            if(roleInput == 1){
                role = "User";
            } else if(roleInput == 2){
                role = "Recruiter";
            } else{
                System.out.println("Invalid Input!");
                return false;
            }

            String updateQuery = "UPDATE users SET role = ? WHERE email = ?";
            ps = con.prepareStatement(updateQuery);

            ps.setString(1, role);
            ps.setString(2, user.getEmail());

            int updateRows = ps.executeUpdate();
            if(updateRows > 0){
                System.out.println("Role updated successfully!");
                System.out.println("You are registered as: " + role);
                return true;
            } else{
                System.out.println("Failed to update role!");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

    public User login(String email, String password) {

        try {
            Connection con = DbConnectivity.getConnection();

            String query = "SELECT * FROM users WHERE email = ? AND password = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login Successful!");

                // Fetch user data from DB
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String role = rs.getString("role");

                return new User(id, username, email, password, role);

            } else {
                System.out.println("Invalid Email or Password!");
            }

        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
        }

        return null;
    }

}
