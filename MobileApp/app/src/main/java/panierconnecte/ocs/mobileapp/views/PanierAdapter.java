package panierconnecte.ocs.mobileapp.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import panierconnecte.ocs.mobileapp.R;
import panierconnecte.ocs.mobileapp.models.Panier;

/**
 * Created by Karim on 10/02/2018.
 */


public class PanierAdapter extends ArrayAdapter<ArrayList> {
    private final Context context;
    private final ArrayList<Panier> paniers;

    public PanierAdapter(Context context, ArrayList paniers) {
        super(context, -1, paniers);
        this.context = context;
        this.paniers = paniers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_panier_adapter, parent, false);

        Panier panier = paniers.get(position);

        TextView panierNameTextView = convertView.findViewById(R.id.panierNameTextView);
        TextView panierWeight = convertView.findViewById(R.id.poidsBalanceTextview);

        panierNameTextView.setText(panier.getName());
        panierWeight.setText(String.valueOf(panier.getWeight()));

        return rowView;
    }
}
