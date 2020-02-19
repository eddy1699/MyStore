package com.example.mystore;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mystore.CRUD_SQL.MantenimientoProducto;

public class DetalleProductoActivity extends AppCompatActivity {

    EditText etPrecioTiendaDetalle,etCantidadDetalle;
    TextView tvCantBase,tvId,etMarcaDetalle,etPresentacionDetalle;

    MantenimientoProducto myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        myDb= new MantenimientoProducto(this);
        etMarcaDetalle= findViewById(R.id.etMarcaDet);
        etPresentacionDetalle= findViewById(R.id.etPresentacionDet);
        etPrecioTiendaDetalle=findViewById(R.id.etPreTiendaDet);
        etCantidadDetalle= findViewById(R.id.etCantidadDet);
tvCantBase=findViewById(R.id.tvCantiBase);
tvId=findViewById(R.id.tvID);
        Button btnVenderProd = findViewById(R.id.btnVender);

        btnVenderProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizarCantidad();



            }
        });

        Bundle bundle= getIntent().getExtras();
        String idDetalle =bundle.getString("a");
        String marcaDetalle =bundle.getString("b");
        String presentDetalle =bundle.getString("c");
        String prerefDetalle =bundle.getString("d");
        String preTiendaDetalle =bundle.getString("e");
        String proveeDetalle =bundle.getString("f");
        String cateDetalle =bundle.getString("g");
        String cantDetalle= bundle.getString("h");
        etMarcaDetalle.setText("Marca: "+marcaDetalle);
        etPresentacionDetalle.setText("Presentacion:"+presentDetalle);
        etPrecioTiendaDetalle.setText(""+preTiendaDetalle);
        tvCantBase.setText(""+cantDetalle);
        tvId.setText(""+idDetalle);
    }

    private void actualizarCantidad() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Procesando Venta");
        progressDialog.show();


        Integer cantidad_salida = Integer.parseInt(etCantidadDetalle.getText().toString());
        Integer cantidad_base= Integer.parseInt(tvCantBase.getText().toString());
        String id=tvId.getText().toString();

        Integer restarCanti =cantidad_base-cantidad_salida;
        String nuevaCantidad=restarCanti.toString();


       boolean isInserted = myDb.updateCantidad(nuevaCantidad, id);
        if(isInserted == true){


            progressDialog.dismiss();
            Toast.makeText(DetalleProductoActivity.this,"Venta Exitosa", Toast.LENGTH_LONG).show();

        }

        else{

            progressDialog.dismiss();
            Toast.makeText(DetalleProductoActivity.this,"No se pudo realizar la venta,Contacte al administrador", Toast.LENGTH_LONG).show();
        }

    }
}
