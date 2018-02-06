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
 * Created by Helmi on 03/02/2018.
 */

public class MachineAdapter extends ArrayAdapter<Machine> {

    Context context;
    public MachineAdapter(Context context, ArrayList<Machine> machines) {
        super(context, 0, machines);
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Machine machine = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_machine, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.machine_name);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.machine_image);
        Picasso.with(context).load(Uri.parse(machine.getMachineImage())).into(imageView);
        tvName.setText("La machine numero "+machine.getMachineId());
        return convertView;
    }

}
