package com.example.mystore.Request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private static  final String ruta ="https://apptests2020.000webhostapp.com/Validar.php";
    private Map<String, String> parametros;

    public LoginRequest(String usuarioR, String claveR, Response.Listener<String>listener){
        super(Request.Method.POST,ruta,listener,null);
        parametros= new HashMap<>();
        parametros.put("usuario",usuarioR+"");
        parametros.put("password",claveR+"");

    }

    public Map<String, String> getParams() {
        return parametros;
    }

}
