package panierconnecte.ocs.mobileapp.views;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import panierconnecte.ocs.mobileapp.AppController;
import panierconnecte.ocs.mobileapp.R;
import panierconnecte.ocs.mobileapp.models.Machine;
import panierconnecte.ocs.mobileapp.utilities.MachineAdapter;


/**
 * Created by Helmi on 30/01/2018.
 */

public class AvaiableMachinesActivity extends AppCompatActivity  {
    ListView machinesListVeiw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availablemachines);

        String ServiceURL = "https://webservicesocs.herokuapp.com/api/DispoMachines";
        machinesListVeiw = (ListView) findViewById(R.id.machines_lv);
        final String result = "";
        final ArrayList<Machine> machines = new ArrayList<>();
        JsonArrayRequest req = new JsonArrayRequest(ServiceURL,
                new Response.Listener<JSONArray>() {
                    @TargetApi(Build.VERSION_CODES.M)
                    @Override
                    public void onResponse(JSONArray response) {

                        try
                        {

                            for(int i=0;i<response.length();i++){

                                JSONObject e= (JSONObject) response.get(i);
                                Machine machine = new Machine();
                                System.out.println("machine id mte3i est : " + e.getString("MachineId"));
                                machine.setMachineId(e.getString("MachineId"));
                                machine.setStatut(e.getString("Staut"));
                                machine.setMachineImage(e.getString("MachineImage"));
                                machine.setTempsResteEnMinutes(e.getString("TempsResteEnMinutes"));
                                machines.add(machine);
                            }
                            MachineAdapter arrayAdapter = new MachineAdapter(AvaiableMachinesActivity.this, machines);
                            machinesListVeiw.setAdapter(arrayAdapter);


                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AvaiableMachinesActivity.this, "BAD JSON ", Toast.LENGTH_SHORT).show();


                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AvaiableMachinesActivity.this, "BAD REQUEST", Toast.LENGTH_SHORT).show();
            }
        });
        // Adding request to request queue
        AppController.getInstance(AvaiableMachinesActivity.this).addToRequestQueue(req);
        machinesListVeiw.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;
                // ListView Clicked item value
                Machine machine = (Machine) machinesListVeiw.getItemAtPosition(position);

                Intent intent = new Intent(AvaiableMachinesActivity.this,MachineActivity.class);
                intent.putExtra("machine",machine);
                startActivity(intent);
            }

        });



    }

}
