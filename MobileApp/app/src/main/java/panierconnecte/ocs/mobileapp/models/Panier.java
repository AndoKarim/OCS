package panierconnecte.ocs.mobileapp.models;

/**
 * Created by Karim on 10/02/2018.
 */

public class Panier {
    private String name;


    private float weight;

    public Panier(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }


}
