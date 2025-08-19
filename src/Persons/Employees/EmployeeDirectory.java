/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persons.Employees;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Exceptions.SalaryException;
import Persons.Employees.Zookeeper;
import Persons.Employees.Guide;
/**
 *
 * @author Valdelomaar
 */
public class EmployeeDirectory {
    private final List<Employee> all = new ArrayList<>();
    private final List<Guide> guides = new ArrayList<>();
    private final List<Zookeeper> zookeepers = new ArrayList<>();
    private final Map<String, Employee> byId = new HashMap<>();
   
    public Employee add(Role role,
                        String id,
                        String name,
                        LocalDate birthDate,
                        String phone,
                        Double salary) throws SalaryException {
        return add(role, id, name, birthDate, phone, salary, null);
    }
    
    // NOTA: el último parámetro ahora es "extra":
    //  - Para ZOOKEEPER: especialidad (obligatoria)
    //  - Para GUIDE: idioma (opcional, si viene se guarda)
    public Employee add(Role role,
                        String id,
                        String name,
                        LocalDate birthDate,
                        String phone,
                        Double salary,
                        String extra) throws SalaryException {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id requerido");
        }
        if (byId.containsKey(id)) {
            throw new IllegalArgumentException("id duplicado");
        }
        if (salary == null || salary < 3000) {
            throw new SalaryException("El valor debe ser mayor o igual a 3000");
        }

        Employee e;
        switch (role) {
            case GUIDE: {
                Guide g = new Guide(id, name, birthDate, phone, salary);
                // Si nos pasan un "extra" para GUIDE, se interpreta como idioma
                if (extra != null && !extra.isBlank()) {
                    g.setLanguages(new String[]{ extra.trim() });
                }
                e = g;
                all.add(e);
                guides.add(g);
                break;
            }
            case ZOOKEEPER: {
                if (extra == null || extra.isBlank()) {
                    throw new IllegalArgumentException("Especialidad requerida para Zookeeper");
                }
                Zookeeper z = new Zookeeper(id, name, birthDate, phone, salary, extra.trim());
                e = z;
                all.add(e);
                zookeepers.add(z);
                break;
            }
            default:
                throw new IllegalArgumentException("Rol no soportado");
        }
        byId.put(id, e);
        return e;
    }
    
    public List<Employee> getAll() { return all; }
    public List<Guide> getGuides() { return guides; }
    public List<Zookeeper> getZookeepers() { return zookeepers; }
    public Employee getById(String id) { return byId.get(id); }
    public boolean existsId(String id) { return byId.containsKey(id); }
    
    public boolean removeById(String id) {
        Employee e = byId.remove(id);
        if (e == null) return false;
        all.remove(e);
        if (e instanceof Guide) guides.remove(e);
        if (e instanceof Zookeeper) zookeepers.remove(e);
        return true;
    }
    
    public boolean updateBasics(String id, String name, String phone, Double salary) throws SalaryException {
        Employee e = byId.get(id);
        if (e == null) return false;
        if (name != null)  e.setName(name);
        if (phone != null) e.setPhone(phone);
        if (salary != null) {
            if (salary < 3000) throw new SalaryException("El valor debe ser mayor o igual a 3000");
            e.setSalary(salary);
        }
        return true;
    }
  
    public void refreshTable(JTable ListaTable) {
        String[] cols = { "ID", "Nombre", "Fecha Nac.", "Teléfono", "Salario", "Rol" };
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        for (Employee e : all) {
            String rol =
                (e instanceof Guide) ? "Guide" :
                (e instanceof Zookeeper) ? "Zookeeper" : "";
            model.addRow(new Object[] {
                e.getId(),
                e.getName(),
                e.getBirthDate(),
                e.getPhone(),
                e.getSalary(),
                rol
            });
        }
        ListaTable.setModel(model);
    }
    
}   