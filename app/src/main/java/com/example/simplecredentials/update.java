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

import java.util.HashMap;
import java.util.Map;

public class update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        EditText txt_id = findViewById(R.id.txt_id);
        EditText txt_uname = findViewById(R.id.txt_uname);
        EditText txt_pword = findViewById(R.id.txt_pword);
        EditText txt_email = findViewById(R.id.txt_email);
        Button btn_update = findViewById(R.id.btn_update);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = txt_id.getText().toString();
                String uname = txt_uname.getText().toString();
                String pword = txt_pword.getText().toString();
                String email = txt_email.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://192.168.1.53/android_crud/update.php";
                //local ip/folder name from htdocs/php create file

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Update")){
                                    Toast.makeText(update.this, "Data Updated", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(update.this, "Data Failed to Update to Database", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(update.this, error.toString(), Toast.LENGTH_SHORT).show();
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