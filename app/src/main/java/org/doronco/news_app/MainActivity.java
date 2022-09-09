package org.doronco.news_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView = findViewById(R.id.users_list);
        new MyAsyncTask(this,recyclerView).execute();
    }
    /*RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<User> users_resp = new ArrayList<>();
                try {
                    JSONArray users = new JSONArray(response);
                    for(int i=0;i<users.length();i++){
                        JSONObject user = users.getJSONObject(i);
                        users_resp.add(new User(
                                user.getInt("id"),
                                user.getString("name"),
                                user.getString("username"),
                                user.getString("email"),
                                user.getString("lat"),
                                user.getString("lng")
                        ));
                        Log.d("testtest","tst");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("testtest",error.toString());
            }
        });
        queue.add(stringRequest);
        return data;*/
}