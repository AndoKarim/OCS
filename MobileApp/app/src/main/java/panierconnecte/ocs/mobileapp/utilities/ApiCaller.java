package panierconnecte.ocs.mobileapp.utilities;


import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import panierconnecte.ocs.mobileapp.views.adapter.PanierAdapter;

/**
 * Created by Karim on 17/10/2017.
 */

public class ApiCaller {

    static SharedPreferences sharedPreferences;
    private static String responseApi = "TEST";


    private static void postCall(String path, final HashMap<String, String> parameters, Context c) {
        sharedPreferences = c.getSharedPreferences("prefs", c.MODE_PRIVATE);

        String ipAddress = sharedPreferences.getString("BoxIP", "");
        String address = "http://" + ipAddress + path;

        RequestQueue queue = Volley.newRequestQueue(c);
        final SharedPreferences.Editor editor = sharedPreferences.edit();


        StringRequest postRequest = new StringRequest(Request.Method.POST, address,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                return parameters;
            }
        };
        queue.add(postRequest);

    }


    public static void addPanier(final HashMap<String, String> params, final Context c) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        String path = ":3000/addDevice";
        String name = params.get("name");

        HashSet<String> paniers = (HashSet<String>) sharedPreferences.getStringSet("baskets", null);
        if (paniers == null) {
            ArrayList<String> paniersList = new ArrayList<>();
            paniersList.add(name);
            paniers = new HashSet<>(paniersList);
        } else {
            if (!paniers.contains(name)) {
                paniers.add(name);
                postCall(path, params, c);
            } else
                Toast.makeText(c, "Le nom de panier existe déjà, veuillez en trouver un autre", Toast.LENGTH_SHORT).show();
        }
        editor.putStringSet("paniers", paniers);
        editor.apply();
    }

    public static void removePanier(final HashMap<String, String> params, Context context) {
        String path = ":3000/removeDevice";
        postCall(path, params, context);


    }

    public static void sendFCM(final HashMap<String, String> params, Context c) {
        String path = ":3000/setFCMToken";
        postCall(path, params, c);
    }

    public static void refreshWeight(String name, final TextView textViewWeight, Context c) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(c);
        sharedPreferences = c.getSharedPreferences("prefs", c.MODE_PRIVATE);

        String ipAddress = sharedPreferences.getString("BoxIP", "");

        String address = "http://" + ipAddress + ":3000/infoDevice?name=" + name;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, address,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseApi = response;
                        try {
                            JSONObject jsonObject = new JSONObject(responseApi);
                            String weightReceived = (String) jsonObject.get("weight");
                            String weightConverted = PanierAdapter.getWeight(Integer.valueOf(weightReceived));
                            textViewWeight.setText(weightConverted);

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


}
