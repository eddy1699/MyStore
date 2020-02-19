package com.example.mystore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mystore.CRUD_SQL.MantenimientoProducto;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroManualActivity extends AppCompatActivity {

    private EditText codBar,marca,present,prec_ref,prov,cat,cant,pre_tienda;
    private Button btnScanner,btnBuscar,btnGuardar;

    MantenimientoProducto myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_manual);
        myDb= new MantenimientoProducto(this);
        codBar= findViewById(R.id.etCodBarra);
        marca= findViewById(R.id.etMarca);
        present= findViewById(R.id.etPresentacion);
        prec_ref= findViewById(R.id.etPrecioRef);
        prov=findViewById(R.id.etProveedor);
        cant=findViewById(R.id.etCantidad);
        cat=findViewById(R.id.etCategoria);
        pre_tienda=findViewById(R.id.etPreTienda);
        btnScanner = findViewById(R.id.btnBuscarProducto);
        btnBuscar = findViewById(R.id.btnBuscarCodBarBD);
        btnGuardar=findViewById(R.id.btnRegistrarSQL);
        AddData();



        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo=codBar.getText().toString();
                leerQR(codigo);
            }
        });


        btnScanner.setOnClickListener(monClickListener);



    }

    private void AddData() {

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marca2 =marca.getText().toString();
                String presentacion2=present.getText().toString();
                String pre_ref2 =prec_ref.getText().toString();
                String pre_tienda2 =pre_tienda.getText().toString();
                String cantidad2 =cant.getText().toString();
                String proveedor2 =prov.getText().toString();
                String cat2 =cat.getText().toString();

                boolean isInserted = myDb.insertData(marca2,presentacion2,pre_ref2,pre_tienda2,cantidad2,proveedor2,cat2 );
                if(isInserted == true)
                    Toast.makeText(RegistroManualActivity.this,"Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(RegistroManualActivity.this,"Data not Inserted", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void leerQR(final String codebar) {
        RequestQueue queue = Volley.newRequestQueue(RegistroManualActivity.this);
        final String codebarqr =codBar.getText().toString();
        String url="http://apptests2020.000webhostapp.com/validar_producto.php?bar_code="+codebarqr;

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                                int index=response.indexOf("{");
                                String js= response.substring(index);
                            JSONObject jsonObject = new JSONObject(js);
                                String marca1 = jsonObject.getString("marca");

                                String presentacion1 = jsonObject.getString("presentacion");

                                String pre_ini_ref1 = jsonObject.getString("pre_ini_ref");
                            String prove = jsonObject.getString("proveedor");
                            String cate = jsonObject.getString("categoria");

                                marca.setText(""+marca1);

                                present.setText(""+presentacion1);

                             prec_ref.setText(""+pre_ini_ref1);
                            prov.setText(""+prove);
                            cat.setText(""+cate);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getActivity(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
                Log.d("ERROR CONEXION",error.getMessage().toString());
            }
        });
        queue.add(stringRequest);

    }













    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult  result= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if (result.getContents() != null){
                codBar.setText(""+result.getContents());
            }else {
                Toast.makeText(RegistroManualActivity.this,"Error al escanear el codigo de barra", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private  View.OnClickListener monClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnBuscarProducto:
                    new IntentIntegrator(RegistroManualActivity.this).initiateScan();
                    break;

            }
        }
    };

}
