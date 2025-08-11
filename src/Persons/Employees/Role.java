/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Persons.Employees;

/**
 *
 * @author Valdelomaar
 */
public enum Role {
    
    GUIDE("Guide"),
    ZOOKEEPER("Zookeeper");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName; // Esto es lo que muestra el JComboBox
    }
    
}
