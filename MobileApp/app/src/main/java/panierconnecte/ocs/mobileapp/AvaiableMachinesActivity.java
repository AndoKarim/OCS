package panierconnecte.ocs.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
        JsonObjectRequest jsonObjReq  = new JsonObjectRequest(Request.Method.GET, ServiceURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray json = new JSONArray(result);
                    for(int i=0;i<json.length();i++){
                        JSONObject e = json.getJSONObject(i);
                        Machine machine = new Machine();
                        machine.setMachineId(e.getString("MachineId"));
                        machine.setStatut(e.getString("Statut"));
                        machine.setMachineImage(e.getString("MachineImage"));
                        machine.setTempsResteEnMinutes(e.getString("TempsResteEnMinutes"));
                        machines.add(machine);
                    }
                    MachineAdapter arrayAdapter = new MachineAdapter(AvaiableMachinesActivity.this, machines);
                    machinesListVeiw.setAdapter(arrayAdapter);

                } catch (JSONException e) {
                    Toast.makeText(AvaiableMachinesActivity.this, "BAD JSON ", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AvaiableMachinesActivity.this, "BAD REQUEST", Toast.LENGTH_SHORT).show();


            }
        });
        AppController.getInstance(AvaiableMachinesActivity.this).addToRequestQueue(jsonObjReq);

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
