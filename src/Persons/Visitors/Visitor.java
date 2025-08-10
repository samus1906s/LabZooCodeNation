/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persons.Visitors;

import Persons.Person;
import java.time.LocalDate;

/**
 *
 * @author jprod
 */
public class Visitor extends Person {

    public Visitor(String id, String name, LocalDate birthDate, String phone) {
        super(id, name, birthDate, phone);
    }
    
    public Visitor(String id, String name, LocalDate birthDate) {
        this(id, name, birthDate, null);
    }

    @Override
    public String toString() {
        return "Visitor{" +super.toString()+ '}';
    }
    
    
    
}
