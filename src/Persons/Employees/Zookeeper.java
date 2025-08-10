/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persons.Employees;

import Animals.Animal;
import java.time.LocalDate;

/**
 *
 * @author jprod
 */
public class Zookeeper extends Employee {
    private String speciality;
    private String activities[];

    public String getSpeciality() {
        return speciality;
    }

    public Zookeeper(String id, String name, LocalDate birthDate, String phone, Double salary, String speciality) {
        super(id, name, birthDate, phone, salary);
        this.speciality = speciality;
        activities=new String[50];
    }
    
    public void  feedAnimal(Animal animal){
        recordActivitie("Se alimanto a: " + animal);
    }
    
    public void checkHealthAnimal(Animal animal){
        recordActivitie("Se reviso la salud de: " + animal);
    }
    
    private void recordActivitie(String activitie){
        for (int i = 0; i < 50; i++) {
            if(activities[i]==null){
               activities[i]= activitie;
               break;
            }
        }
    }

    @Override
    public String toString() {
        return "Zookeeper{" + super.toString()+ ", speciality=" + speciality + ", activities=" + activities.length + '}';
    }

    
     
}
