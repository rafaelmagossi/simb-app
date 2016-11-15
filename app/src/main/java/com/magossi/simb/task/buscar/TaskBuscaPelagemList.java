package com.magossi.simb.task.buscar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.magossi.simb.domain.bovino.Pelagem;
import com.magossi.simb.extra.MainConfig;
import com.magossi.simb.interfaces.PelagemListInterface;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by RafaelMq on 08/11/2016.
 */
public class TaskBuscaPelagemList extends AsyncTask<String, String, List<Pelagem>> {


    private HttpClientErrorException erro;
    private Context context;
    private PelagemListInterface pelagemListInterface;
    private ProgressDialog progress;
    private Activity activity;


    public TaskBuscaPelagemList(Context context, Activity activity, PelagemListInterface pelagemListInterface) {
        this.context = context;
        this.activity = activity;
        this.pelagemListInterface = pelagemListInterface;



    }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setMessage("Carregando Dados");
        progress.show();

        //super.onPreExecute();
    }


    @Override
    protected List<Pelagem> doInBackground(String... params) {
        List<Pelagem> pelagens = null;
        Pelagem[] p;
        //String URL = "http://192.168.0.100:8080/"
        String URL = MainConfig.getUrl()
                + params[0]
                + "/{opcao}";

        try {
            erro = null;
            publishProgress("Aguarde");
            String url = URL.replace("{opcao}", params[1]);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            p = restTemplate.getForObject(url, Pelagem[].class);
            pelagens = Arrays.asList(p);
            publishProgress("Pronto");

        } catch (HttpClientErrorException e) {
            Log.e("http", e.getStatusCode().toString());
            Log.e("http", e.getResponseBodyAsString(), e);
            erro = e;
        } catch (Exception ex) {
            Log.e("http", ex.getMessage(), ex);
        }

        return (pelagens);

    }


    @Override
    protected void onProgressUpdate(String... values) {
        progress.setMessage(values[0]);
    }

    @Override
    protected void onPostExecute(List<Pelagem> params) {

        progress.setMessage("Pronto");

        if(params != null && erro == null){
            pelagemListInterface.depoisBuscaPelagens(params,null);
            progress.dismiss();

        }else if(params == null && erro != null){
            pelagemListInterface.depoisBuscaPelagens(params,erro.getStatusCode().toString());
            progress.dismiss();
            //Toast.makeText(this.context, "Nao Encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}
