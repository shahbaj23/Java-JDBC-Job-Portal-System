import JobData.JobDetails;
import JobData.Jobs;
import Users.User;
import Users.UserLoginSignup;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        UserLoginSignup userService = new UserLoginSignup();

        System.out.println("====================================");
        System.out.println("  Hello! Welcome to Job Portal App  ");
        System.out.println("====================================");

        User existUser = null;

        while(true){
            System.out.println("Please login or SignUp Account First ----");
            System.out.println("1. SignUp Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Your Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    System.out.print("Username: ");
                    String username = sc.next();

                    System.out.print("Email: ");
                    String email = sc.next();

                    System.out.print("Password: ");
                    String password = sc.next();

                    String role = "";

                    User newUser = new User(username, email, password, role);

                    boolean isRegistered = userService.register(newUser);

                    if (isRegistered) {
                        existUser = userService.login(email, password);
                        Dashboard.showDetails(existUser);
                    } else {
                        System.out.println("Registration failed! Try again.\n");
                    }
                    break;

                case 2:


                    while (existUser == null) {
                        System.out.print("Email: ");
                        String existEmail = sc.nextLine();

                        System.out.print("Password: ");
                        String existPassword = sc.nextLine();

                        existUser = userService.login(existEmail, existPassword);

                        if (existUser == null) {
                            System.out.println("Login Failed! Try again.\n");
                        } else {
                            System.out.println("Login Successful! Welcome " + existUser.getUsername());
                        }
                    }

                    Dashboard.showDetails(existUser);
                    break;


                case 3:
                    System.out.println("Thank you! Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");

            }
        }
    }
}
