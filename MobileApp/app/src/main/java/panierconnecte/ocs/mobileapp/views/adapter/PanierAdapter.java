package panierconnecte.ocs.mobileapp.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import panierconnecte.ocs.mobileapp.R;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_panier_adapter, parent, false);

        final TextView panierNameTextView;
        final TextView panierWeight;
        panierNameTextView = rowView.findViewById(R.id.panierNameTextView);
        panierWeight = rowView.findViewById(R.id.poidsBalanceTextview);
        Button refreshButton = rowView.findViewById(R.id.refreshButton);
        ImageButton deleteButton = rowView.findViewById(R.id.deleteButton);

        final String panier = this.paniers.get(position);

        panierNameTextView.setText(panier);

        panierWeight.setText("?");


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiCaller.removePanier(panierNameTextView.getText().toString(), getContext());
                paniers.remove(position);
                notifyDataSetChanged();
            }
        });


        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiCaller.refreshWeight(panierNameTextView.getText().toString(),panierWeight, getContext());
            }
        });

        return rowView;
    }


}
