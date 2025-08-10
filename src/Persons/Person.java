/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persons;

import Utils.UtilDate;
import java.time.LocalDate;

/**
 *
 * @author jprod
 */
public abstract class Person {
    protected String id;
    protected String name;
    protected LocalDate birthDate;
    protected String phone;
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public int getAge(){
        return UtilDate.calculateAge(birthDate);
    }

    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        if (validatePhone(phone))
            this.phone = phone;
    }
    
    private static boolean validatePhone(String phone){
        return phone.matches("^[0-9]{2}-[0-9]{2}-[0-9]{2}-[0-9]{2}$");
    }

    public Person(String id, String name, LocalDate birthDate, String phone) {
        this.id = id;
        this.name = name;
        if (UtilDate.isNotFutureDate(birthDate))
            this.birthDate = birthDate;
        if(validatePhone(phone))
            this.phone = phone;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", age="+getAge() + ", phone=" + phone;
    }
    
    
    
}
