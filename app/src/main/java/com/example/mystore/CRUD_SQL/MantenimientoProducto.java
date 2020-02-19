package com.example.mystore.CRUD_SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MantenimientoProducto extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Producto.db";
    public  static  final String TABLE_NAME= "PRODUCTO";
    public  static final String COL0= "ID";
    public  static  final String COL1= "MARCA";
    public  static  final String COL2= "PRESENTACION";
    public  static  final String COL3= "PRECIO_REFERENCIAL";
    public  static   final String COL4= "PRECIO_TIENDA";
    public  static  final String COL5= "CANTIDAD";
    public  static  final String COL6= "PROVEEDOR";
    public  static final String COL7= "CATEGORIA";
    public String qrcreatedb="(ID INTEGER PRIMARY KEY AUTOINCREMENT,MARCA TEXT,PRESENTACION TEXT,PRECIO_REFERENCIAL TEXT,PRECIO_TIENDA TEXT ,CANTIDAD TEXT,PROVEEDOR TEXT, CATEGORIA TEXT)";


    public MantenimientoProducto(Context context){
        super(context,DATABASE_NAME ,null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + qrcreatedb);


    }

   public boolean insertData(String marca, String pres, String pre_ref, String pre_tienda , String cantidad , String proveedor, String categoria ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,marca);
       contentValues.put(COL2,pres);
       contentValues.put(COL3,pre_ref);
       contentValues.put(COL4,pre_tienda);
       contentValues.put(COL5,cantidad);
       contentValues.put(COL6,proveedor);
       contentValues.put(COL7,categoria);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }



    public boolean updateCantidad(String newCant, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL5,newCant);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PRODUCTO");
        onCreate(sqLiteDatabase);

    }
}
