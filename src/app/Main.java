package app;
import service.EmployeeService;
import static util.MenuUtil.displayMenu;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeService();
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while(running){
            displayMenu();
            System.out.print("\nEnter Choice: ");

            if(!sc.hasNextInt()){
                System.out.println("Please enter a valid number.");
                sc.nextLine();
                continue;
            }

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
                    employeeService.totalEmployees();
                    break;
                case 7:
                   running = false;
                   System.out.println("""
                           Thank you for using SmartGRC.
                           
                           Exiting...""");
                   break;

                default:
                    System.out.println("Invalid Input, Please enter valid number!");

            }

        }

        sc.close();

    }
}