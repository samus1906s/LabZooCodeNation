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
    // CREATE
    private final List<Employee> all = new ArrayList<>();
    private final List<Guide> guides = new ArrayList<>();
    private final List<Zookeeper> zookeepers = new ArrayList<>();
    private final Map<String, Employee> byId = new HashMap<>();

    // ===== CREATE (Guide o Zookeeper sin especialidad) =====
    // Se usa para GUIDE; para ZOOKEEPER usa el overload con 'speciality'
    public Employee add(Role role,
                        String id,
                        String name,
                        LocalDate birthDate,
                        String phone,
                        Double salary) throws SalaryException {
        return add(role, id, name, birthDate, phone, salary, null);
    }

    // ===== CREATE (Zookeeper con especialidad) =====
    public Employee add(Role role,
                        String id,
                        String name,
                        LocalDate birthDate,
                        String phone,
                        Double salary,
                        String speciality) throws SalaryException {

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
                e = new Guide(id, name, birthDate, phone, salary);
                all.add(e);
                guides.add((Guide) e);
                break;
            }
            case ZOOKEEPER: {
                if (speciality == null || speciality.isBlank()) {
                    throw new IllegalArgumentException("Especialidad requerida para Zookeeper");
                }
                e = new Zookeeper(id, name, birthDate, phone, salary, speciality);
                all.add(e);
                zookeepers.add((Zookeeper) e);
                break;
            }
            default:
                throw new IllegalArgumentException("Rol no soportado");
        }

        byId.put(id, e);
        return e;
    }

    // ===== READ =====
    public List<Employee> getAll() { return all; }
    public List<Guide> getGuides() { return guides; }
    public List<Zookeeper> getZookeepers() { return zookeepers; }
    public Employee getById(String id) { return byId.get(id); }
    public boolean existsId(String id) { return byId.containsKey(id); }

    // ===== DELETE =====
    public boolean removeById(String id) {
        Employee e = byId.remove(id);
        if (e == null) return false;
        all.remove(e);
        if (e instanceof Guide) guides.remove(e);
        if (e instanceof Zookeeper) zookeepers.remove(e);
        return true;
    }

    // ===== UPDATE básico (nombre/teléfono/salario) =====
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

    // ===== REFRESH JTable: ListaTable =====
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
            e.getPhone(),   // ← aquí depende 100% de que Person tenga getPhone()
            e.getSalary(),
            rol
        });
    }
    ListaTable.setModel(model);     
    }}   