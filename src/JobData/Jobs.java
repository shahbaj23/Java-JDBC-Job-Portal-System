package JobData;

public class Jobs {
    private int jobId;
    private String title;
    private String company;
    private String location;
    private Double salary;

    public Jobs(String title, String company, String location, Double salary) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public String getLocation() {
        return location;
    }

    public String getCompany() {
        return company;
    }

    public String getTitle() {
        return title;
    }
}
