import JobData.JobDetails;
import JobData.Jobs;
import Users.User;

import java.sql.SQLException;
import java.util.Scanner;

public class Dashboard {
    public static void showDetails(User existUser) throws SQLException{

        Scanner sc = new Scanner(System.in);

        JobDetails jobService = new JobDetails();

        while (true){
            System.out.println("Select Number: ");
            System.out.println("\n1. View Jobs");
            System.out.println("2. Apply Job");
            System.out.println("3. Applied Job");
            System.out.println("4. Post Job");
            System.out.println("5. View Job Application");
            System.out.println("6. Update Job Application Status");
            System.out.println("7. Delete Job");
            System.out.println("8. Logout");

            System.out.print("Select Your Number: ");
            int jobChoice = sc.nextInt();
            sc.nextLine();

            switch (jobChoice){
                case 1:
                    jobService.viewJob();
                    break;

                case 2:
                    if((existUser.getRole().equalsIgnoreCase("Recruiter"))){
                        System.out.println("Recruiters cannot apply for jobs.");
                        break;
                    }

                    System.out.print("Enter Job ID to apply: ");
                    int jobID = sc.nextInt();
                    sc.nextLine();

                    jobService.applyJob(existUser.getId(), jobID);

                    break;

                case 3:
                    jobService.viewJobApplied(existUser.getId());
                    break;

                case 4:
                    if(!existUser.getRole().equalsIgnoreCase("Recruiter")){
                        System.out.println("Access Denied! Only recruiters can post jobs.");
                        break;
                    }

                    System.out.print("Job Title: ");
                    String title = sc.nextLine();

                    System.out.print("Company Name: ");
                    String company = sc.nextLine();

                    System.out.print("Job Location: ");
                    String location = sc.nextLine();

                    System.out.println("Salary: ");
                    double salary = sc.nextDouble();

                    Jobs Job = new Jobs(title, company, location, salary);
                    jobService.jobPost(Job);
                    break;

                case 5:
                    if(!existUser.getRole().equalsIgnoreCase("Recruiter")){
                        System.out.println("Access Denied! Only recruiters can view your jobs.");
                    }

                    System.out.print("Enter Job ID to apply: ");
                    int jobAppliedID = sc.nextInt();

                    jobService.viewApplication(jobAppliedID);
                    break;

                case 6: {
                    if (!existUser.getRole().equalsIgnoreCase("Recruiter")) {
                        System.out.println("Access Denied! Only recruiters can update job status.");
                    }

                    System.out.print("Enter Job ID: ");
                    int jobId = sc.nextInt();

                    System.out.print("Enter User Id: ");
                    int userId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Status (Selected / Rejected): ");
                    String statusChoice = sc.nextLine();

                    jobService.statusApplication(jobId, userId, statusChoice);
                    break;
                }
                case 7:
                    if(!existUser.getRole().equalsIgnoreCase("Recruiter")){
                        System.out.println("Access Denied! Only recruiters can delete jobs.");
                        break;
                    }

                    System.out.print("Enter job Id: ");
                    int jobId = sc.nextInt();

                    jobService.jobDelete(jobId);
                    break;

                case 8:
                    System.out.println("Logout");
                    return;
                default:
                    System.out.println("Wrong Choice! Try again.");
            }
        }
    }
}
