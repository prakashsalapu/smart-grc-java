package org.smartgrc.util;

public class MenuUtil {

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
            6. Total Employees
            
            DEPARTMENT MANAGEMENT
            ---------------------
            7. Display Departments
            8. Add Department
            9. Update Department
            10. Delete Department
            
            --------------------------------------------------
            0. Exit
            ==================================================
            """);
    }
}
