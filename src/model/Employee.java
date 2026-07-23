package model;


public class Employee {

    // fields
    private final int empID;
    private String empName;
    private String email;
    private String department;
    private String designation;
    private double salary;
    private String status;

    // constructor
    public Employee(int empID, String empName, String email, String department, String designation, double salary, String status){

        this.empID = empID;
        setEmpName(empName);
        setEmail(email);
        setDepartment(department);
        setDesignation(designation);
        setSalary(salary);
        setStatus(status);

    }

    // Getter and Setter methods
    public int getEmpID() {
        return empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        if(empName != null && !empName.isBlank()){
            this.empName = empName;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
       if(email != null && email.contains("@")){
           this.email = email;
        }
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if(department != null && !department.isBlank()){
            this.department = department;
        }
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        if(designation != null && !designation.isBlank()){
            this.designation = designation;
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if(salary > 0){
            this.salary = salary;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if("ACTIVE".equals(status) || "INACTIVE".equals(status)){
            this.status = status;
        }
    }

    // Override method to display Object fields
    @Override
    public String toString(){
        return "\nEmployee ID: " + empID + "\nName: " + empName + "\nEmail: " + email + "\nDepartment: " + department + "\nDesignation: " + designation +
                "\nSalary: " + salary + "\nStatus: " + status;
    }

}
