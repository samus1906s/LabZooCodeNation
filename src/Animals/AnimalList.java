/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Animals;

import Lists.List;
import java.util.Arrays;

/**
 *
 * @author jprod
 */
public class AnimalList implements List<Animal> {
    private Animal animals[];

    public AnimalList() {
        this.animals = new Animal[100];
    }
    
    @Override
    public boolean add(Animal animal) {
        if (find(animal.getId())!=null)
            return false; 
        for (int i = 0; i < 100; i++) {
            if(animals[i]==null){
               animals[i]=animal;
               return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(Animal animal) {
        for (int i = 0; i < 100; i++) {
            if(animals[i]==animal){
               animals[i]=null;
               return true;
            }
        }
        return false;
    }

    @Override
    public Animal find(Object id) {
        String idt=String.valueOf(id);
        for (int i = 0; i < 100; i++) {
            if(animals[i].getId().equals(idt))
                return animals[i];
        }
        return null;
    }

    @Override
    public void showAll() {
        System.out.println(Arrays.toString(animals));
    }
    
}
