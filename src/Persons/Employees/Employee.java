/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persons.Employees;

import Persons.Person;
import Utils.UtilDate;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author jprod
 */
public abstract class Employee extends Person {
    private Double salary;

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        if (validateSalary(salary))
            this.salary = salary;
    }
    
    private static boolean validateSalary(double salary){
        return salary>=3000;
    }

    public Employee(String id, String name, LocalDate birthDate, String phone, Double salary) {
        super(id,name,UtilDate.isLegalAge(birthDate)?birthDate:null,phone);
        if(validateSalary(salary))
            this.salary = salary;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", phone=" + phone + ", salary=" + salary;
    }
    
    
    
}
