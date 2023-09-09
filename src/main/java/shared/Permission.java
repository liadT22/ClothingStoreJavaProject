package shared;

import org.example.Classes.Enum.EmployeeType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Permission {
    public static Map<EmployeeType, List<String>> getPermissions() {
        Map<EmployeeType, List<String>> permissions = new HashMap<>();

        permissions.put(EmployeeType.SHIFT_SUPERVISOR, Arrays.asList(
                "Add New Product",
                "Remove Product",
                "View Sales Report",
                "Manage Employees",
                "Chat with Branch",
                "Logout"
        ));

        permissions.put(EmployeeType.CASHIER, Arrays.asList(
                "Process Sale",
                "Chat with Branch",
                "Logout"
        ));

        permissions.put(EmployeeType.FLOOR, Arrays.asList(
                "Check Inventory",
                "Chat with Branch",
                "Logout"
        ));

        return permissions;
    }
}
