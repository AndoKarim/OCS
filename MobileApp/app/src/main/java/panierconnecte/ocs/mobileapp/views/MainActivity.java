package panierconnecte.ocs.mobileapp.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.io.IOException;
import java.util.HashSet;

import panierconnecte.ocs.mobileapp.R;
import panierconnecte.ocs.mobileapp.utilities.ApiCaller;

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

        apiArea = (TextView) findViewById(R.id.resultApi);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //getWeightButton = (Button) findViewById(R.id.button);
        listPaniers = (ListView) findViewById(R.id.listPaniers);

        sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);

        //listPaniers.setAdapter(new ArrayAdapter<String>());


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
            case R.id.settings:
                Intent i = new Intent(this, Settings.class);
                startActivity(i);
                return true;
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

                    }
                }

            }

            @Override
            public void OnError(Exception e) {
                Log.d("UPnPDiscovery", "Error: " + e.getLocalizedMessage());
            }
        });

    }


    private void fetchApi() {

        try {
            ApiCaller.callWeightAPI(this);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
