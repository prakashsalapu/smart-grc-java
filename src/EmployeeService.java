import java.util.ArrayList;
import java.util.Scanner;


public class EmployeeService {

    private ArrayList<Employee> employees = new ArrayList<>();

    private Scanner sc = new Scanner(System.in);

    // Add Employees to Database
    public void addEmployee(){

        System.out.print("Enter number of employees to add: ");
        int n = sc.nextInt();
        sc.nextLine();

        for(int i=0; i<n; i++){

            System.out.println("Enter the  Details of Employee "+ (i+1));
            System.out.print("\nEmployee ID: ");
            int empID = sc.nextInt();
            sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Department: ");
            String department = sc.nextLine();

            System.out.print("Designation: ");
            String designation = sc.nextLine();

            System.out.print("Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            System.out.print("Status: ");
            String status = sc.nextLine();

            employees.add(new Employee(empID, name, email, department, designation, salary, status));

        }

    }

    // Display all Employees
    public void displayAllEmployees(){
        for(Employee employee : employees){
            System.out.println(employee);
        }
    }

    public void searchEmployee(int searchId){
        boolean found = false;
        for(Employee employee : employees){
            if(employee.empID == searchId){
                System.out.println(employee);
                found = true;
            }
        }
        if(!found){
            System.out.println("\nEmployee details not found!");
        }
    }

    public void updateEmployee(){}

    public void deleteEmployee(){}
}
