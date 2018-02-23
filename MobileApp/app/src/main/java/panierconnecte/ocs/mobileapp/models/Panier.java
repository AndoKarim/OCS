package panierconnecte.ocs.mobileapp.models;

/**
 * Created by Karim on 10/02/2018.
 */

public class Panier {
    private String name;
    private String weight;
    private String ipAddress;

    public Panier(String name, String weight, String ipAddress) {
        this.name = name;
        this.weight = weight;
        this.ipAddress = ipAddress;
    }

    public String getName() {
        return name;
    }

    public String getWeight() {
        return weight;
    }


    public String getIpAddress() {
        return ipAddress;
    }
}
