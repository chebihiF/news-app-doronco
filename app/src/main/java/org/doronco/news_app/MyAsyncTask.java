package org.doronco.news_app;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.doronco.news_app.adapter.UserAdapter;
import org.doronco.news_app.model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyAsyncTask extends AsyncTask<String,Integer,String> {

    private Context context;
    RecyclerView recyclerView;

    public MyAsyncTask(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder data = new StringBuilder();
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/users");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(1500);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect(); // send Http Request
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line ;
                while ((line=reader.readLine())!=null){
                    data.append(line);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        List<User> users_resp = new ArrayList<>();
        try {
            JSONArray users = new JSONArray(s);
            for(int i=0;i<users.length();i++){
                JSONObject user = users.getJSONObject(i);
                JSONObject address = user.getJSONObject("address");
                JSONObject geo = address.getJSONObject("geo");
                users_resp.add(new User(
                        user.getInt("id"),
                        user.getString("name"),
                        user.getString("username"),
                        user.getString("email"),
                        geo.getString("lat"),
                        geo.getString("lng")
                ));
            }

            LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(layoutManager);
            UserAdapter adapter = new UserAdapter(users_resp);
            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
