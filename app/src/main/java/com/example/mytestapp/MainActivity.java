package com.example.mytestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button show;

    RequestQueue requestQueue;
    String url="https://run.mocky.io/v3/a6e4f675-7f5a-47b8-914a-1fd6f68b717f";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.sample);
        show=findViewById(R.id.button);


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestQueue= Volley.newRequestQueue(MainActivity.this);

                JsonObjectRequest jsonObject=new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray=response.getJSONArray("users");
                            JSONObject jsonObject1=jsonArray.getJSONObject(0);
                            JSONObject jsonObject2=jsonObject1.getJSONObject("contact");
                            textView.setText(jsonObject2.getString("mobile"));
                            /*for (int i=0;i<jsonArray.length();i++){
                                textView.setText(jsonArray.getString());
                            }*/
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsonObject);
            }
        });
    }
}