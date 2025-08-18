/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visits;
import Lists.List;
import Persons.Visitors.Visitor;
import java.util.ArrayList;
/**
 *
 * @author je110
 */
public class VisitsArrayList implements List<Visitor> {
    ArrayList<Visitor> visitors;

    public ArrayList<Visitor> getVisitors() {
        return visitors;
    }

    public VisitsArrayList(ArrayList<Visitor> visitors) {
        this.visitors = new ArrayList<>();
    }

    @Override
    public boolean add(Visitor t) {
        if (find(t.getId()) != null) return false;
        visitors.add(t);
        return true;
    }

    @Override
    public boolean remove(Visitor t) {
        for (int i = 0; i < visitors.size(); i++) {
            if (visitors.get(i).getId().equals(t.getId())) {
                visitors.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Visitor find(Object id) {
         String strId = String.valueOf(id);
        for (Visitor visitor : visitors) {
            if (visitor.getId().equals(strId)) {
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
