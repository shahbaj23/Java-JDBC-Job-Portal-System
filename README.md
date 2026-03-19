# Job Portal System (Java + JDBC)

A simple Job Portal System built using **Java, JDBC, and MySQL** where users can register, login, apply for jobs, and track application status.

---

## Features

*  User Registration & Login
*  Apply for Jobs
*  View Applied Jobs
*  Application Status Tracking (Pending / Approved / Rejected)
*  Job Listings
*  Prevent duplicate job applications

---

## Tech Stack

* **Java**
* **JDBC**
* **MySQL**
* **IntelliJ IDEA**

---

## Project Structure

```
Job-Portal-System/
│── DBConnection/
│── JobData/
│── Users/
│── Dashboard.java
│── Main.java
```

---

## Setup Instructions

1. Clone the repository:

```

2. Open project in IntelliJ

3. Setup MySQL Database:

* Create database
* Run SQL tables

4. Update DB connection:

```
DbConnectivity.java
```

5. Run the project 

---

##Database Tables

### Users Table

* id
* username
* email
* password

### Jobs Table

* job_id
* title
* company
* location
* salary

### Applications Table

* user_id
* job_id
* status



---

## Future Improvements

* Web version (Spring Boot / MERN)
* Admin dashboard
* Email notifications
* Resume upload feature

---

##  Author

**Shahbaj Siddique**

---

## ⭐ Support

If you like this project, give it a ⭐ on GitHub!
