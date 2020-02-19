package com.example.mystore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mystore.Request.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextView tvRegistro ;
    EditText etUsuario,etPassword;

    String user,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvRegistro = findViewById(R.id.tviniRegistrarse);

        tvRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerActivity);
                finish();
            }
        });



        Button Validar = (Button) findViewById(R.id.btniniIngresar);
        Validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                validarUsuario();

            }
        });




    }

    private void validarUsuario() {


        final EditText usuario = findViewById(R.id.etiniUser);
        final EditText  clave   =  findViewById(R.id.etiniPass);
        final String usu=usuario.getText().toString();
        final String cla=clave.getText().toString();
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Validadndo Informacion...");
        progressDialog.show();


        Response.Listener<String> respuesta=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonrespuesta= new JSONObject(response);
                    boolean ok =jsonrespuesta.getBoolean("success");


                    if(!usu.isEmpty() && !cla.isEmpty()){


                        if (ok==true){
                            Intent intent=new Intent(LoginActivity.this,InicioActivity.class);
                            intent.putExtra("usuario",usu);
                            finish();
                            progressDialog.dismiss();


                            startActivity(intent);
                        }else {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(LoginActivity.this);
                            alerta.setMessage("Datos incorrectos").setNegativeButton("Reintentar",null).create().show();
                            progressDialog.dismiss();
                            usuario.setText("");
                            clave.setText("");
                        }
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this,"No se permiten campos vacios",Toast.LENGTH_SHORT).show();
                    }





                }catch (JSONException e){
                    e.getMessage();

                }
            }
        };
        LoginRequest r = new LoginRequest(usu,cla,respuesta);
        RequestQueue cola= Volley.newRequestQueue(LoginActivity.this);
        cola.add(r);





    }


}