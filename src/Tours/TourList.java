/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tours;
import Lists.List;
import java.util.HashMap;


public class TourList implements List<Tour> {
    HashMap<String, Tour> map;

    public TourList() {
        this.map = new HashMap<>();
    }

    @Override
    public boolean add(Tour t) {
        String key = generateKey(t);
        if (map.containsKey(key)) return false; 
        map.put(key, t);
        return true;
    }

    @Override
    public boolean remove(Tour t) {
        String key = generateKey(t);
        if (!map.containsKey(key)) return false;
        map.remove(key);
        return true;
    }

    @Override
    public Tour find(Object id) {
        String strId = String.valueOf(id); 
        return map.get(strId);
    }
    @Override
    public void showAll() {
       
    }

    private String generateKey(Tour t) {
        return t.getGuide().getId() + "-" + t.getDate().toString();
    }
}
