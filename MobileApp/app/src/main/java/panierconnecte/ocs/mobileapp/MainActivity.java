package panierconnecte.ocs.mobileapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import panierconnecte.ocs.mobileapp.utilities.ApiCaller;

public class MainActivity extends AppCompatActivity {

    private TextView apiArea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiArea = (TextView) findViewById(R.id.resultApi);
        fetchApi();
    }


    private void fetchApi(){
        String result;

        result = ApiCaller.callApi("https://jsonplaceholder.typicode.com/posts", this);

        apiArea.setText(result);



    }


}
