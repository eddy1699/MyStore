package com.example.mystore.Request;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistroRequest  extends StringRequest {

    private static  final String ruta="https://apptests2020.000webhostapp.com/Registro.php";
    private Map<String, String> parametros;
    public  RegistroRequest(String user, String pass, String nom, String ape, Response.Listener<String> listener){
        super(Request.Method.POST,ruta,listener,null);
        parametros= new HashMap<>();
        parametros.put("usu_usuario",user+"");
        parametros.put("usu_password",pass+"");
        parametros.put("usu_nombres",nom+"");
        parametros.put("usu_apellidos",ape+"");


    }
    public Map<String, String> getParams() {
        return parametros;
    }





}
