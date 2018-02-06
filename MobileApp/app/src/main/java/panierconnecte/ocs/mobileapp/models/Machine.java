package panierconnecte.ocs.mobileapp.models;

import java.io.Serializable;

/**
 * Created by Helmi on 03/02/2018.
 */

public class Machine implements Serializable {
    private String MachineId;
    private String MachineName;
    private String Statut;
    private String TempsResteEnMinutes;
    private String MachineImage;

    public Machine() {
    }

    public String getMachineName() {
        return MachineName;
    }

    public void setMachineName(String machineName) {
        MachineName = machineName;
    }

    public String getMachineImage() {
        return MachineImage;
    }

    public void setMachineImage(String machineImage) {
        MachineImage = machineImage;
    }

    public String getMachineId() {
        return MachineId;
    }

    public void setMachineId(String machineId) {
        MachineId = machineId;
    }

    public String getStatut() {
        return Statut;
    }

    public void setStatut(String statut) {
        Statut = statut;
    }

    public String getTempsResteEnMinutes() {
        return TempsResteEnMinutes;
    }

    public void setTempsResteEnMinutes(String tempsResteEnMinutes) {
        TempsResteEnMinutes = tempsResteEnMinutes;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "MachineId='" + MachineId + '\'' +
                ", MachineName='" + MachineName + '\'' +
                ", Statut='" + Statut + '\'' +
                ", TempsResteEnMinutes='" + TempsResteEnMinutes + '\'' +
                ", MachineImage='" + MachineImage + '\'' +
                '}';
    }
}
