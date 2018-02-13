package panierconnecte.ocs.mobileapp.models;

/**
 * Created by Karim on 10/02/2018.
 */

public class Panier {
    private String name;
    private float weight;
    private String ipAddress;

    public Panier(String name, float weight, String ipAddress) {
        this.name = name;
        this.weight = weight;
        this.ipAddress = ipAddress;
    }

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }


    public String getIpAddress() {
        return ipAddress;
    }
}
