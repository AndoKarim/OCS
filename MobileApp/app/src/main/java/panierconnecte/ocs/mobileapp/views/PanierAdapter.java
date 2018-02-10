package panierconnecte.ocs.mobileapp.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import panierconnecte.ocs.mobileapp.R;

/**
 * Created by Karim on 10/02/2018.
 */


public class PanierAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public PanierAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_panier_adapter, parent, false);
        return rowView;
    }
}
