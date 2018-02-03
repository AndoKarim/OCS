package panierconnecte.ocs.mobileapp.models;

import java.io.Serializable;

/**
 * Created by Helmi on 03/02/2018.
 */

public class Machine implements Serializable {
    private String MachineId;
    private String Statut;
    private String TempsResteEnMinutes;

    public Machine() {
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
                ", Statut='" + Statut + '\'' +
                ", TempsResteEnMinutes='" + TempsResteEnMinutes + '\'' +
                '}';
    }
}
