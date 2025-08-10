/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Animals;

/**
 *
 * @author jprod
 */
public enum Zone {
    FARM("Farm"),
    PETTING ("Petting"),
    FEEDING("Feeding"),
    CONSERVATION_AND_RESCUE("Conservation And Rescue"),
    NIGHT_TUNNEL("Night Tunnel"),
    LOCAL_BIODIVERSITY("Local Biodiversity");

    private final String zone;

    Zone(String zone) {
        this.zone = zone;
    }

    public String getZone() {
        return zone;
    }

    @Override
    public String toString() {
        return zone;
    }
}
