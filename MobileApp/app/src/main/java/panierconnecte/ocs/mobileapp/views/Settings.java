package panierconnecte.ocs.mobileapp.views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import panierconnecte.ocs.mobileapp.R;
import panierconnecte.ocs.mobileapp.utilities.ApiCaller;

public class Settings extends AppCompatActivity {

    private EditText userEdittext;
    private EditText passwordEdittext;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        userEdittext = (EditText) findViewById(R.id.userEdittext);
        passwordEdittext = (EditText) findViewById(R.id.passwordEdittext);

        sharedPreferences = getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE);
        userEdittext.setText(sharedPreferences.getString("USERNAME", ""));


    }
}
