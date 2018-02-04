package panierconnecte.ocs.mobileapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import panierconnecte.ocs.mobileapp.models.Machine;

/**
 * Created by Helmi on 03/02/2018.
 */

public class MachineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);
        Intent intent = getIntent();
        Machine machine = (Machine) intent.getSerializableExtra("machine");

        setTitle("Machine numéro " + machine.getMachineId());

        ImageView imageView = (ImageView)findViewById(R.id.machine_image);
        TextView numero = (TextView)findViewById(R.id.machine_numero);
        TextView statut = (TextView)findViewById(R.id.machine_statut);
        TextView tempsreste = (TextView)findViewById(R.id.machine_tempsrestee);


        Picasso.with(getApplicationContext()).load(Uri.parse(machine.getMachineImage())).into(imageView);
        numero.setText(machine.getMachineId());
        statut.setText(machine.getStatut());
        tempsreste.setText(machine.getTempsResteEnMinutes());



    }
}
