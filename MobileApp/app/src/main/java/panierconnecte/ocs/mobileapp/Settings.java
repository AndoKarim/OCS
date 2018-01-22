package panierconnecte.ocs.mobileapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

public class Settings extends AppCompatActivity {

    private Button serverButton;
    private EditText serverEditText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        serverButton = (Button) findViewById(R.id.serverAdressValidate);
        serverEditText = (EditText) findViewById(R.id.editTextAddress);
        sharedPreferences = getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE);
        serverEditText.setText(sharedPreferences.getString("ADDRESS", ""));

        serverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String address = serverEditText.getText().toString();
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                editor.putString("TOKEN", refreshedToken);
                if (!address.equals(""))
                    editor.putString("ADDRESS", address);
                else
                    Toast.makeText(Settings.this, "Enter a valid adress", Toast.LENGTH_SHORT).show();

                editor.commit();
                finish();

            }
        });
    }
}
