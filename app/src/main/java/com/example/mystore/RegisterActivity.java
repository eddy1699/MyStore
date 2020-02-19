package com.example.mystore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.mystore.Request.RegistroRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    EditText etNombres,etApellidos,etUsuario,etPass,etPass2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        etNombres=findViewById(R.id.etregNombres);
        etApellidos=findViewById(R.id.etregApellidos);
        etUsuario=findViewById(R.id.etregUser);
        etPass=findViewById(R.id.etregPass);
        etPass2=findViewById(R.id.etregPass2);







        Button registro =findViewById(R.id.btnregRegistrar);

        registro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String nom= etNombres.getText().toString();
                String ape= etApellidos.getText().toString();
                String user= etUsuario.getText().toString();
                String pass=etPass.getText().toString();
                String pass2=etPass2.getText().toString();

                registrarUsuario(user,pass,nom,ape);




            }


        });






    }

    private void registrarUsuario(String usuario , String password, String nombres, String apellidos) {


        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);



        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Registrando...");
        progressDialog.show();



        Response.Listener<String> respuesta = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                        try {
                            JSONObject jsonres= new JSONObject(response);
                            boolean ok=jsonres.getBoolean("success");

                            if (ok==true){



                                Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(login);
                                finish();
                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivity.this,"Registrado con exito", Toast.LENGTH_LONG).show();

                            }else {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(RegisterActivity.this);
                                alerta.setMessage("Fallo Registro").setNegativeButton("Reintentar",null).create().show();
                                etNombres.setText("");
                                etApellidos.setText("");
                                etUsuario.setText("");
                                etPass.setText("");
                                etPass2.setText("");
                            }

                        }catch (JSONException e){ e.getMessage();}

            }
        };

        RegistroRequest r = new RegistroRequest(usuario,password,nombres,apellidos,respuesta);
        RequestQueue cola= Volley.newRequestQueue(RegisterActivity.this);
        cola.add(r);

    }
}
