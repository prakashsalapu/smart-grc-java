public class Employee {

    // fields
    final int empID;
    String empName;
    String email;
    String department;
    String designation;
    double salary;
    String status;

    // constructor
    Employee(int empID,  String empName, String email, String department, String designation, double salary, String status){
        this.empID = empID;
        this.empName = empName;
        this.email = email;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.status = status;

    }

//    // methods
//    public void displayDetails() {}
//
//    public void updateSalary(double newSalary){}
//
//    public void changeDepartment(String newDepartment){}
//
//    public void updateEmail(String newEmail){}
//
//    public void updateDesignation(String newDesignation){}
//
//    public void activate(){}
//
//    public void deactivate(){}

    @Override
    public String toString(){
        return "\nEmployee ID: " + empID + "\nName: " + empName + "\nDepartment: " + department + "\nDesignation: " + designation +
                "\nSalary: " + salary + "\nStatus: " + status;
    }

}
