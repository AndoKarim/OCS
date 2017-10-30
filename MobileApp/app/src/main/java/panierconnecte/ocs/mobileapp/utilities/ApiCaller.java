package panierconnecte.ocs.mobileapp.utilities;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Karim on 17/10/2017.
 */

public class ApiCaller {

    private static String responseApi = "TEST";

    public static String callApi(String urlString, Context c) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(c);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseApi = response;
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
