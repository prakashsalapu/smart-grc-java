import service.EmployeeService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeService();
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while(running){

            employeeService.displayMenu();
            System.out.print("\nEnter Choice: ");
            int menuOption = sc.nextInt();
            sc.nextLine();
            switch (menuOption){
                case 1:
                    employeeService.addEmployee(sc);
                    break;

                case 2:
                    employeeService.displayAllEmployees();
                    break;

                case 3:
                    System.out.print("\n Enter Employee ID to search: ");
                    int searchID = sc.nextInt();
                    sc.nextLine();
                    employeeService.searchEmployee(searchID);
                    break;

                case 4:
                    System.out.print("\n Enter Employee ID to Update: ");
                    int updateID = sc.nextInt();
                    sc.nextLine();
                    employeeService.updateEmployee(updateID, sc);
                    break;

                case 5:
                    System.out.print("\n Enter Employee ID to Delete: ");
                    int deleteID = sc.nextInt();
                    sc.nextLine();
                    employeeService.deleteEmployee(deleteID);
                    break;

                case 6:
                   running = false;
                   System.out.println("""
                           Thank you for using SmartGRC.
                           
                           Exiting...""");
                   break;

                default:
                    System.out.println("Invalid Input");

            }

        }

        sc.close();

    }
}