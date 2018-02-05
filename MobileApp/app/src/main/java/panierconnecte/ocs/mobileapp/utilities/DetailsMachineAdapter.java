package panierconnecte.ocs.mobileapp.utilities;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import panierconnecte.ocs.mobileapp.R;
import panierconnecte.ocs.mobileapp.models.Machine;

/**
 * Created by user on 05/02/2018.
 */

public class DetailsMachineAdapter extends ArrayAdapter<Machine> {
    Context context;

    public DetailsMachineAdapter(Context context, ArrayList<Machine> devices) {
        super(context, 0, devices);
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Machine machine = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_machine, parent, false);
        }
        TextView machineNum = (TextView) convertView.findViewById(R.id.machine_numero);
        TextView machineStatut = (TextView) convertView.findViewById(R.id.machine_statut);
        TextView machineTemps = (TextView) convertView.findViewById(R.id.machine_tempsrestee);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.machine_imageView);
        Picasso.with(context).load(Uri.parse(machine.getMachineImage())).into(imageView);
        machineNum.setText("Machine numéro "+machine.getMachineId());
        return convertView;
    }
}

