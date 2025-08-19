/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persons.Visitors;

import Animals.Animal;
import Lists.List;
import Persons.Visitors.Visitor;
import java.util.ArrayList;

/**
 *
 * @author Eduard Salas Murillo
 */
public class VisitorArrayList implements List<Visitor>{
   private ArrayList<Visitor>map;

    public VisitorArrayList() {
        this.map = new ArrayList<>();
    }

    public ArrayList<Visitor> getMap() {
        return map;
    }
   
    @Override
    public boolean add(Visitor v) {
        if (find(v.getId()) != null) {
            return false; 
        }
        return this.map.add(v);
    }

    @Override
    public boolean remove(Visitor v) {
        Visitor visitorRemove = find(v.getId());
        if (visitorRemove != null) {
            return this.map.remove(visitorRemove);
        }
        return false; 
    }

    @Override
    public Visitor find(Object id) {
        String visitorId = String.valueOf(id);
        for (Visitor visitor : this.map) {
            if (visitor.getId().equals(visitorId)) {
                return visitor; 
            }
        }
        return null; 
    }

    @Override
    public void showAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
