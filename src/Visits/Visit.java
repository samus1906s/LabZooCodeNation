/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visits;

import Persons.Visitors.Visitor;
import java.time.LocalDate;

/**
 *
 * @author jprod
 */
public class Visit {
    private Visitor visitor;
    private LocalDate date;

    public Visitor getVisitor() {
        return visitor;
    }

    public LocalDate getDate() {
        return date;
    }

    public Visit(Visitor visitor) {
        this.visitor = visitor;
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Visit{" + "visitor=" + visitor + ", date=" + date + '}';
    }
    
    
    
}
