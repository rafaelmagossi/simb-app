package com.magossi.simb.task.buscar;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.magossi.simb.domain.bovino.Bovino;
import com.magossi.simb.extra.MainConfig;
import com.magossi.simb.interfaces.buscar.BovinoBuscarObjInterface;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by RafaelMq on 22/05/2016.
 */
public class TaskBuscaBovinoObj extends AsyncTask<String, String, Bovino> {

    private HttpClientErrorException erro;
    private Context context;
    private BovinoBuscarObjInterface bovinoSalvarObjInterface;
    private ProgressDialog progress;

    public TaskBuscaBovinoObj(Context context, BovinoBuscarObjInterface bovinoSalvarObjInterface){
        this.context = context;
        this.bovinoSalvarObjInterface = bovinoSalvarObjInterface;

    }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setMessage("Carregando Bovino");
        progress.show();

        //super.onPreExecute();
    }

    @Override
    protected Bovino doInBackground(String... params) {
        Bovino bovino = null;
        //String URL = "http://192.168.0.100:8080/bovino"
        String URL = MainConfig.getUrl()+"bovino"
                    +  params[0]
                    + "/{tag}";

            try{
                erro = null;
                publishProgress("Aguarde");
                String url = URL.replace("{tag}", params[1]);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                bovino = restTemplate.getForObject(url, Bovino.class);
                publishProgress("Pronto");

            }catch (HttpClientErrorException e) {
                Log.e("http", e.getStatusCode().toString());
                Log.e("http", e.getResponseBodyAsString(),e);
                erro = e;
            }catch (Exception ex){
                Log.e("http", ex.getMessage(),ex);
            }
        return(bovino);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        progress.setMessage(values[0]);
    }

    @Override
    protected void onPostExecute(Bovino params) {

        progress.setMessage("Pronto");

        if(params != null && erro == null){
            bovinoSalvarObjInterface.depoisBuscaBovino(params,null);
            progress.dismiss();

        }else if(params == null && erro != null){
            bovinoSalvarObjInterface.depoisBuscaBovino(params,erro.getStatusCode().toString());
            progress.dismiss();
            //Toast.makeText(this.context, "Nao Encontrado", Toast.LENGTH_SHORT).show();
        }



    }


}
