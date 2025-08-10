/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Animals;

import Utils.UtilDate;
import java.time.LocalDate;

/**
 *
 * @author jprod
 */
public class Animal {
    private String id;
    private String name;
    private String species;
    private LocalDate birthDate;
    private Zone zone;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public int getAge(){
        return UtilDate.calculateAge(birthDate);
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Animal(String id, String name, String species, LocalDate birthDate) {
        if (id.matches("^[\\w]-[0-9]{4}$"))
            this.id = id;
        this.name = name;
        this.species = species;
        if (UtilDate.isNotFutureDate(birthDate))
            this.birthDate = birthDate;
        this.zone = Zone.CONSERVATION_AND_RESCUE;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", name=" + name + ", species=" + species + ", birthDate=" + birthDate + ", age="+getAge() + ", zone=" + zone + '}';
    }
    
    
}
