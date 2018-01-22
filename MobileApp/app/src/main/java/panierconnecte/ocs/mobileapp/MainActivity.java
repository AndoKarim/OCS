package panierconnecte.ocs.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

import panierconnecte.ocs.mobileapp.utilities.ApiCaller;

public class MainActivity extends AppCompatActivity {

    public static TextView apiArea;
    public static ProgressBar progressBar;
    public static Button getWeightButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiArea = (TextView) findViewById(R.id.resultApi);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        getWeightButton = (Button) findViewById(R.id.button);

        getWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                fetchApi();
            }
        });


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
            default:
                return false;
        }
    }


    private void fetchApi() {

        try {
            ApiCaller.callApi(this);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
