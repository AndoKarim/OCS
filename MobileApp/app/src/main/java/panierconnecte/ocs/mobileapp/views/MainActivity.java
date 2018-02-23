package panierconnecte.ocs.mobileapp.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.ArraySet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com._8rine.upnpdiscovery.UPnPDevice;
import com._8rine.upnpdiscovery.UPnPDiscovery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import panierconnecte.ocs.mobileapp.R;
import panierconnecte.ocs.mobileapp.utilities.ApiCaller;
import panierconnecte.ocs.mobileapp.views.adapter.PanierAdapter;
import panierconnecte.ocs.mobileapp.views.slide.SlideActivity;

public class MainActivity extends AppCompatActivity {

    public static TextView apiArea;
    public static ProgressBar progressBar;
    public static Button getWeightButton;
    public static ListView listPaniers;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiArea = findViewById(R.id.resultApi);
        progressBar = findViewById(R.id.progressBar);
        //getWeightButton =  findViewById(R.id.button);
        listPaniers = findViewById(R.id.listPaniers);

        sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);

        Set setList = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            setList = sharedPreferences.getStringSet("paniers", new ArraySet<String>());
        }
        ArrayList list = new ArrayList();
        list.addAll(setList);

        PanierAdapter panierAdapter = new PanierAdapter(getApplicationContext(), list);
        listPaniers.setAdapter(panierAdapter);


      /*  getWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                fetchApi();
            }
        });*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.availableMachine:
                Intent j = new Intent(this, AvaiableMachinesActivity.class);
                startActivity(j);
                return true;
            case R.id.refreshBox:
                refreshBalances();
                return true;
            case R.id.addBalance:
                Intent tutorial = new Intent(MainActivity.this, SlideActivity.class);
                startActivity(tutorial);
            default:
                return false;
        }
    }

    private void refreshBalances() {
        UPnPDiscovery.discoveryDevices(MainActivity.this, new UPnPDiscovery.OnDiscoveryListener() {
            @Override
            public void OnStart() {
            }

            @Override
            public void OnFoundNewDevice(UPnPDevice device) {
            }

            @Override
            public void OnFinish(HashSet<UPnPDevice> devices) {
                for (UPnPDevice device : devices) {
                    if (device.getFriendlyName().equals("BoxBalance")) {
                        SharedPreferences.Editor e = sharedPreferences.edit();
                        e.putString("BoxIP", device.getHostAddress());
                        e.commit();
                        Log.d("BoxIP", "TROUVE");
                        String token = sharedPreferences.getString("FCM", "0");
                        while (token.equals("0"))
                            token = sharedPreferences.getString("FCM", "0");
                        ApiCaller.sendFCM(device.getHostAddress(), token, getApplicationContext());

                    }
                }

            }

            @Override
            public void OnError(Exception e) {
                Log.d("UPnPDiscovery", "Error: " + e.getLocalizedMessage());
            }
        });

    }


}
