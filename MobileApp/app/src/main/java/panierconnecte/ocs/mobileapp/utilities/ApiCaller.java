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

import org.json.JSONArray;
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
    static final String IP_ADDRESS = "5.135.152.200";


    public static String callWeightAPI(Context c) throws IOException {
        sharedPreferences = c.getSharedPreferences("prefs", c.MODE_PRIVATE);


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(c);

        String address = IP_ADDRESS
                + "/poids?name=" + sharedPreferences.getString("USERNAME", "")
                + "&token=" + sharedPreferences.getString("TOKEN", ")");

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, address,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseApi = response;
                        try {
                            JSONObject jsonObject = new JSONObject(responseApi);
                            JSONArray paniers = jsonObject.getJSONArray("paniers");
                            //Créer l'arrayList et populate la listView avec cette arrayList

                            //float poids = Float.parseFloat(jsonObject.get("message").toString());
                            //int valEntiere = (int) poids;
                            //String msg = getWeight(valEntiere);
                            //MainActivity.apiArea.setText(msg);
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

    private static String getWeight(int valEntiere) {
        return valEntiere > 1000 ? valEntiere * 0.001 + "Kg" : valEntiere + "g";

    }

    public static void loginAPI(Context c, String username, String password, String firebaseToken) {
        RequestQueue queue = Volley.newRequestQueue(c);
        String address = IP_ADDRESS + "/login?name=" + username + "&password=" + password + "fcmToken=" + firebaseToken;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, address,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseApi = response;
                        try {
                            JSONObject jsonObject = new JSONObject(responseApi);
                            JSONArray paniers = jsonObject.getJSONArray("paniers");
                            SharedPreferences.Editor sharedEditor = sharedPreferences.edit();
                            sharedEditor.putString("PANIERS", paniers.toString());
                            sharedEditor.commit();
                            //Créer l'arrayList et populate la listView avec cette arrayList



                            //float poids = Float.parseFloat(jsonObject.get("message").toString());
                            //int valEntiere = (int) poids;
                            //String msg = getWeight(valEntiere);
                            //MainActivity.apiArea.setText(msg);
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

    }

    public static void addPanier(String ip, String name) {

    }
}
