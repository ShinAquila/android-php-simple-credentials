package com.example.simplecredentials;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class retrieve extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        EditText txt_id = findViewById(R.id.txt_id);
        EditText txt_uname = findViewById(R.id.txt_uname);
        EditText txt_pword = findViewById(R.id.txt_pword);
        EditText txt_email = findViewById(R.id.txt_email);
        Button btn_retrieve = findViewById(R.id.btn_retrieve);

        btn_retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = txt_id.getText().toString();
                String uname = txt_uname.getText().toString();
                String pword = txt_pword.getText().toString();
                String email = txt_email.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://192.168.1.53/android_crud/retrieve.php";
                //local ip/folder name from htdocs/php create file

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);

                                    for (int i = 0; i < jsonArray.length(); i++){
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        String user_name = jsonObject.getString("user_uname");
                                        String user_pass = jsonObject.getString("user_pword");
                                        String user_email = jsonObject.getString("user_email");

                                        txt_uname.setText(user_name);
                                        txt_pword.setText(user_pass);
                                        txt_email.setText(user_email);
                                    }
                                    Toast.makeText(retrieve.this, "Data Retrieved", Toast.LENGTH_SHORT).show();
                                } catch(JSONException e){
                                    e.printStackTrace();
                                    Toast.makeText(retrieve.this, response, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(retrieve.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("id", id);
                        paramV.put("uname", uname);
                        paramV.put("pword", pword);
                        paramV.put("email", email);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }
}