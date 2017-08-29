package com.magossi.simb.task.salvar;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.magossi.simb.domain.bovino.Bovino;
import com.magossi.simb.extra.MainConfig;
import com.magossi.simb.interfaces.salvar.EccBovinoObjInterface;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by RafaelMq on 11/11/2016.
 */
public class TaskSalvaEccBovinoObj extends AsyncTask<Bovino, String, String> {

    private HttpClientErrorException erroHttp;
    private Exception erro;
    private String urlBovino;

    private Context context;
    private EccBovinoObjInterface eccBovinoObjInterface;
    private ProgressDialog progress;


    public TaskSalvaEccBovinoObj(Context context, EccBovinoObjInterface eccBovinoObjInterface){
        this.context = context;
        this.eccBovinoObjInterface = eccBovinoObjInterface;
    }



    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setMessage("Salvando");
        progress.show();
    }

    @Override
    protected String doInBackground(Bovino... params) {
        String urlBovino = null;
        //String URL = "http://192.168.0.100:8080/bovino";
        String URL = MainConfig.getUrl()+
                "bovino/"
                + "{id}"
                +"/ecc";

        try{
            erro = null;
            urlBovino = null;
            publishProgress("Aguarde");
            String url = URL.replace("{id}", params[0].getIdBovino().toString());
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            //urlBovino = restTemplate.postForObject(URL,params[0],String.class);
            urlBovino = restTemplate.postForLocation(url,params[0].getEcc().get(params[0].getEcc().size()-1),String.class).toString();
            publishProgress("Salvo");

        }catch (HttpClientErrorException e) {
            Log.e("http", e.getStatusCode().toString());
            Log.e("http", e.getResponseBodyAsString(),e);
            erroHttp = e;
        }catch (Exception ex){
            Log.e("http", ex.getMessage(),ex);
            erro = ex;
        }
        return(urlBovino);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        progress.setMessage(values[0]);
    }

    @Override
    protected void onPostExecute(String params) {
        progress.setMessage("Salvo");

        if(params != null ){
            eccBovinoObjInterface.depoisAlteraEccBovino(params, null);
            progress.dismiss();
            Toast.makeText(this.context, "Salvo", Toast.LENGTH_SHORT).show();

        }else if(erro != null){
            progress.dismiss();
            Toast.makeText(this.context, "Erro ao Salvar", Toast.LENGTH_SHORT).show();
        }else if(erroHttp != null){
            eccBovinoObjInterface.depoisAlteraEccBovino(null, erroHttp.getStatusCode().toString());
            progress.dismiss();
            Toast.makeText(this.context, "Erro ao Salvar", Toast.LENGTH_SHORT).show();
        }
    }
}
