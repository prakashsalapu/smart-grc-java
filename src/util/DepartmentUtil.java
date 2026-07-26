package util;

public class DepartmentUtil {

    public static void displayMenu() {

        System.out.println("""
            
            ==================================================
                     SMART GRC MANAGEMENT SYSTEM
            ==================================================
            
            EMPLOYEE MANAGEMENT
            -------------------
            1. Add Employee
            2. Display All Employees
            3. Search Employee
            4. Update Employee
            5. Delete Employee
            6. Employee Statistics
            
            DEPARTMENT MANAGEMENT
            ---------------------
            7. Add Department
            8. Display Departments
            9. Update Department
            10. Delete Department
            
            --------------------------------------------------
            0. Exit
            ==================================================
            """);
    }
}