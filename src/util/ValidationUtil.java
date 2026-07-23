package util;

public class ValidationUtil {

    public static boolean isValidId(int id){
        return id > 0;
    }

    public static boolean isValidName(String name){
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidDepartment(String department){
        return department != null && !department.trim().isEmpty();
    }

    public static boolean isValidDesignation(String designation){
        return designation != null && !designation.trim().isEmpty();
    }

    public static boolean isValidSalary(double salary){
        return salary >= 0;
    }

    public static boolean isValidEmail(String email){
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9+_.-]+$");
    }

    public static boolean isValidStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            return false;
        }
        String cleanStatus = status.trim().toUpperCase();
        return cleanStatus.equals("ACTIVE") || cleanStatus.equals("INACTIVE");
    }

}
