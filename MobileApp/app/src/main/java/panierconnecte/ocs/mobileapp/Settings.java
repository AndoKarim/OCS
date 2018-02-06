package panierconnecte.ocs.mobileapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import panierconnecte.ocs.mobileapp.utilities.ApiCaller;

public class Settings extends AppCompatActivity {

    private Button serverButton;
    private EditText userEdittext;
    private EditText passwordEdittext;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        serverButton = (Button) findViewById(R.id.buttonValidate);
        userEdittext = (EditText) findViewById(R.id.userEdittext);
        passwordEdittext = (EditText) findViewById(R.id.passwordEdittext);

        sharedPreferences = getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE);
        userEdittext.setText(sharedPreferences.getString("USERNAME", ""));

        serverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String username = userEdittext.getText().toString();
                String password = passwordEdittext.getText().toString();
                String firebaseToken = FirebaseInstanceId.getInstance().getToken();
                editor.putString("TOKEN", firebaseToken);
                Log.d(" TOKEN", firebaseToken);
                if (!username.equals("")) {
                    editor.putString("USERNAME", username);
                    if(!password.equals("")) {
                        //Encrypter le mot de passe
                        ApiCaller.loginAPI(getApplicationContext(),username, password,firebaseToken);
                    }
                }
                else
                    Toast.makeText(Settings.this, "Enter a valid address", Toast.LENGTH_SHORT).show();

                editor.commit();
                finish();

            }
        });

        
    }
}
