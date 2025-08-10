/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tours;

import Animals.Animal;
import Persons.Employees.Guide;
import Persons.Visitors.Visitor;
import java.time.LocalDate;

/**
 *
 * @author jprod
 */
public class Tour {
    private Guide guide;
    LocalDate date;
    private Visitor visitors[];
    private Animal animals[];

    public Guide getGuide() {
        return guide;
    }

    public LocalDate getDate() {
        return date;
    }

    public Visitor[] getVisitors() {
        return visitors;
    }

    public Animal[] getAnimals() {
        return animals;
    }

    public Tour(Guide guide, Visitor visitors[]) {
        this.guide = guide;
        this.date = LocalDate.now();
        this.visitors = visitors;
        this.animals = new Animal[30];
    }
    
    public void addSeenAnimal(Animal animal){
        for (int i = 0; i < 30; i++) {
            if(animals[i]==null){
                animals[i]=animal;
                break;
            }
        }
    }
}
