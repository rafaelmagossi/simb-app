package com.magossi.simb.extra;


/**
 * Created by RafaelMq on 11/11/2016.
 */
public class MainConfig {

    private static final String UrlLocal = "http://192.168.0.100:8080/" ;
    private static final String UrlExterna = "http://api-simb.magossi.com/" ;
    private static String Url;


    public static String[] getServidores(){
        String[] servidores = {"Servidor Local","AppServer"};
        return servidores;
    }

    public static String getServidor(String servidor){

        String auxString="";

        if(servidor == "Servidor Local" ){
            auxString = "http://192.168.0.100:8080/" ;
        }else if(servidor == "AppServer" ){
            auxString = "http://api-simb.magossi.com/" ;
        }
        return auxString;
    }

    public static String getUrlLocal() {
        return UrlLocal;
    }

    public static String getUrlExterna() {
        return UrlExterna;
    }

    public static String getUrl() {
        return Url;
    }

    public static void setUrl(String url) {
        Url = url;
    }
}
