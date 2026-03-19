package JobData;

import DBConnection.DbConnectivity;

import java.sql.*;

public class JobDetails  {

    public void jobPost(Jobs job) throws SQLException {
        try{
            Connection con = DbConnectivity.getConnection();

            System.out.println("----- Post Your Jobs -----");

            String query = "INSERT INTO jobs(title, company, location, salary) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, job.getTitle());
            ps.setString(2, job.getCompany());
            ps.setString(3, job.getLocation());
            ps.setDouble(4, job.getSalary());

            ps.executeUpdate();

            System.out.println("Job Posted Successfully!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void viewJob() throws SQLException {
        System.out.println("==== Jobs Available ====");

        Connection con = DbConnectivity.getConnection();

        String query = "SELECT * FROM jobs";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println(
                        rs.getInt("job_id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getString("company") + " | " +
                        rs.getString("location") + " | " +
                        rs.getDouble("salary") + "LPA"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jobDelete(int jobId) throws SQLException {
        System.out.println("----- Delete your job -----");

        Connection con = DbConnectivity.getConnection();
        try {

            String query = "DELETE FROM jobs WHERE job_id = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, jobId);
            int rows = ps.executeUpdate();

            if(rows > 0){
                System.out.println("Job Deleted Successfully!");
            }else{
                System.out.println("Job Deleted Failed! Job not found!");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void applyJob(int userId, int jobId) throws SQLException {

        Connection con = DbConnectivity.getConnection();

        try {

            String query = "INSERT INTO applications(user_id, job_id, status) VALUES(?,?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, userId);
            ps.setInt(2, jobId);
            ps.setString(3, "Applied");

            ps.executeUpdate();

            System.out.println("Job Applied Successfully!");
        } catch (SQLException e){
            if(e.getErrorCode() == 1062){
                System.out.println("You have already applied for this job!");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void viewJobApplied(int userId) throws SQLException {
        Connection con = DbConnectivity.getConnection();

        try{
            String query = "SELECT j.job_id, j.title, j.company, j.location, j.salary, a.status " +
                    "FROM applications a " +
                    "JOIN jobs j ON a.job_id = j.job_id " +
                    "WHERE a.user_id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            System.out.println("==== Your Applied Jobs ====");

            boolean hasJobs = false;

            while (rs.next()) {
                hasJobs = true;
                System.out.println(
                        rs.getInt("job_id") + " | " +
                                rs.getString("title") + " | " +
                                rs.getString("company") + " | " +
                                rs.getString("location") + " | " +
                                rs.getDouble("salary") + " | " +
                                rs.getString("status")
                );
            }

            if (!hasJobs) {
                System.out.println("You have not applied for any jobs yet.");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void viewApplication(int jobId) throws SQLException {

        Connection con = DbConnectivity.getConnection();

        try{
            String query = "SELECT u.id, u.username, u.email, a.status " +
                    "FROM applications a " +
                    "JOIN users u ON a.user_id = u.id " +
                    "WHERE a.job_id = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, jobId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Applicants ---");

            boolean foundJobs = false;

            while (rs.next()) {
                foundJobs = true;
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("username") + " | " +
                                rs.getString("email") + " | " +
                                rs.getString("status")
                );
            }

            if (!foundJobs) {
                System.out.println("No Applicants found for this job.");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void statusApplication(int userId, int jobId, String status) throws SQLException {
        Connection con = DbConnectivity.getConnection();

        try{
            String query = "UPDATE applications SET status = ? WHERE user_id = ? AND job_id = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, status);
            ps.setInt(2, jobId);
            ps.setInt(3, userId);

            int rows = ps.executeUpdate();

            if(rows > 0){
                System.out.println("Application Status Updated Successfully!");
            } else {
                System.out.println("No! Applicants Found for this Job!");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
