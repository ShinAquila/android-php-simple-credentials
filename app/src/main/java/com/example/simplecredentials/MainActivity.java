package com.example.simplecredentials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.android.material.internal.EdgeToEdgeUtils;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txt_uname = findViewById(R.id.txt_uname);
        EditText txt_pword = findViewById(R.id.txt_pword);
        EditText txt_email = findViewById(R.id.txt_email);
        Button btn_save = findViewById(R.id.btn_save);

        Button btn_toUpdate = findViewById(R.id.btn_toUpdate);
        Button btn_toRetrieve = findViewById(R.id.btn_toRetrieve);
        Button btn_toDelete = findViewById(R.id.btn_toDelete);
        btn_toUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpdate();
            }
        });
        btn_toRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRetrieve();
            }
        });
        btn_toDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDelete();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = txt_uname.getText().toString();
                String pword = txt_pword.getText().toString();
                String email = txt_email.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://192.168.1.53/android_crud/create.php";
                //local ip/folder name from htdocs/php create file

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Success")){
                                    Toast.makeText(MainActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Data Failed to Add to Database", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
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

    public void openUpdate(){
        Intent intent = new Intent(this, update.class);
        startActivity(intent);
    }
    public void openRetrieve(){
        Intent intent = new Intent(this, retrieve.class);
        startActivity(intent);
    }

    public void openDelete(){
        Intent intent = new Intent(this, delete.class);
        startActivity(intent);
    }
}