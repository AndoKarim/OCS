package panierconnecte.ocs.mobileapp.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import panierconnecte.ocs.mobileapp.R;
import panierconnecte.ocs.mobileapp.models.Panier;
import panierconnecte.ocs.mobileapp.utilities.ApiCaller;

/**
 * Created by Karim on 10/02/2018.
 */


public class PanierAdapter extends ArrayAdapter<ArrayList> {
    private final Context context;
    private final ArrayList<String> paniers;

    public PanierAdapter(Context context, ArrayList paniers) {
        super(context, -1, paniers);
        this.context = context;
        this.paniers = paniers;
    }

    public static String getWeight(int valEntiere) {
        return valEntiere > 1000 ? valEntiere * 0.001 + "Kg" : valEntiere + "g";

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_panier_adapter, parent, false);

        final TextView panierNameTextView;
        final TextView panierWeight;
        panierNameTextView = rowView.findViewById(R.id.panierNameTextView);
        panierWeight = rowView.findViewById(R.id.poidsBalanceTextview);
        Button refreshButton = rowView.findViewById(R.id.refreshButton);
       // Button deleteButton = rowView.findViewById(R.id.rem);

        final String panier = this.paniers.get(position);

        panierNameTextView.setText(panier);

        panierWeight.setText("?");


        /*
        int weight = Math.round(Integer.valueOf(panier.getWeight()));
        String weightString = getWeight(weight);

        panierNameTextView.setText(panier.getName());
        panierWeight.setText(weightString);*/

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiCaller.refreshWeight(panierNameTextView.getText().toString(),panierWeight, getContext());
            }
        });

        return rowView;
    }


}
