package panierconnecte.ocs.mobileapp.utilities;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import panierconnecte.ocs.mobileapp.MainActivity;

/**
 * Created by Karim on 17/10/2017.
 */

public class ApiCaller {

    private static String responseApi = "TEST";
    static SharedPreferences sharedPreferences;


    public static String callApi(Context c) throws IOException {
        sharedPreferences = c.getSharedPreferences("prefs", c.MODE_PRIVATE);


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(c);

        String address = sharedPreferences.getString("ADDRESS", "192.168.0.1") + "/?poids";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, address,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseApi = response;
                        try {
                            JSONObject jsonObject = new JSONObject(responseApi);
                            String msg = jsonObject.get("message").toString()+"Kg";
                            MainActivity.apiArea.setText(msg);
                            MainActivity.progressBar.setVisibility(View.INVISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseApi = "Error API";
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        return responseApi;


    }
}
