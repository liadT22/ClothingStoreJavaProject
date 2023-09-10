package org.example.Classes.Enum;

import java.util.Set;

public enum EmployeeType {
    FLOOR,
    CASHIER,
    SHIFT_SUPERVISOR;

    public static EmployeeType fromString(String type) {
        for (EmployeeType eType : EmployeeType.values()) {
            if (eType.name().equalsIgnoreCase(type)) {
                return eType;
            }
        }
        return null;
    }

}
