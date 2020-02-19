package com.example.mystore;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;


import com.example.mystore.CRUD_SQL.MantenimientoProducto;

import java.util.ArrayList;
import java.util.HashMap;

public class ListarProductosActivity extends AppCompatActivity {


    MantenimientoProducto myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_productos);


        myDb= new MantenimientoProducto(this);
        final ListView mlvProd;
        final ArrayList<HashMap<String, String>> arrayList;

        mlvProd=findViewById(R.id.lvProductos);
        Cursor cursor= myDb.getAllData();
        arrayList= new ArrayList<>();

        if(cursor!=null){
            if (cursor.moveToFirst()){
                do {
                    String id3=cursor.getString(0);
                    String marca3=cursor.getString(1);
                    String pre3=cursor.getString(2);
                    String pre_ref3=cursor.getString(3);
                    String pre_tie3=cursor.getString(4);
                    String cant3=cursor.getString(5);
                    String prove3=cursor.getString(6);
                    String cat3=cursor.getString(7);


                    HashMap<String, String> map= new HashMap<>();
                    map.put("ID",id3);
                    map.put("MARCA",marca3);
                    map.put("PRESENTACION",pre3);
                    map.put("PRECIO_REF",pre_ref3);
                    map.put("PRECIO_TIENDA",pre_tie3);
                    map.put("PROVEEDOR",prove3);
                    map.put("CATEGORIA",cat3);
                    map.put("CANTIDAD",cant3);

                    arrayList.add(map);
                }while (cursor.moveToNext());

                ListAdapter listAdapter=  new SimpleAdapter(getApplicationContext(),arrayList,R.layout.item_producto,
                        new String[]{"MARCA","PRESENTACION","CANTIDAD"},
                        new int[]{R.id.txtMarca, R.id.txtPresentacion,R.id.txtCategoria}
                );
                mlvProd.setAdapter(listAdapter);


                mlvProd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                        HashMap<String, String> map=arrayList.get(i);
                        Bundle bundle = new Bundle();
                        bundle.putString("a",map.get("ID"));
                        bundle.putString("b",map.get("MARCA"));
                        bundle.putString("c",map.get("PRESENTACION"));
                        bundle.putString("d",map.get("PRECIO_REF"));
                        bundle.putString("e",map.get("PRECIO_TIENDA"));
                        bundle.putString("f",map.get("PROVEEDOR"));
                        bundle.putString("g",map.get("CATEGORIA"));
                        bundle.putString("h",map.get("CANTIDAD"));

                        Intent intent = new Intent(ListarProductosActivity.this,DetalleProductoActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);


                    }
                });
            }
        }
        mlvProd.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;

            }
        });
    }

}
