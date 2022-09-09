package org.doronco.news_app;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MyAsyncTask extends AsyncTask<String,Integer,String> {

    private Context context;
    private final String URL = "https://newsapi.org/v2/top-headlines?country=fr&apiKey=ef63300956114525812a43263230a2dd";
    String data = "";

    public MyAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                data = response;
                Log.d("testtest",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("testtest",error.toString());
            }
        });
        queue.add(stringRequest);
        return data;
    }
}
