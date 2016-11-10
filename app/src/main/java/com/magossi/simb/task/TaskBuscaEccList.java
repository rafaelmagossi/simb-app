package com.magossi.simb.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.magossi.simb.domain.Ecc;
import com.magossi.simb.interfaces.EccListInterface;
import com.magossi.simb.interfaces.RacaListInterface;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by RafaelMq on 08/11/2016.
 */
public class TaskBuscaEccList extends AsyncTask<String, String, List<Ecc>> {


    private HttpClientErrorException erro;
    private Context context;
    private EccListInterface eccListInterface;
    private ProgressDialog progress;
    private Activity activity;


    public TaskBuscaEccList(Context context, Activity activity, EccListInterface eccListInterface) {
        this.context = context;
        this.activity = activity;
        this.eccListInterface = eccListInterface;



    }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setMessage("Carregando Dados");
        progress.show();

        //super.onPreExecute();
    }


    @Override
    protected List<Ecc> doInBackground(String... params) {
        List<Ecc> eccs = null;
        Ecc[] ec;
        String URL = "http://192.168.0.100:8080/"
                + params[0]
                + "/{opcao}";

        try {
            erro = null;
            publishProgress("Aguarde");
            String url = URL.replace("{opcao}", params[1]);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ec = restTemplate.getForObject(url, Ecc[].class);
            eccs = Arrays.asList(ec);
            publishProgress("Pronto");

        } catch (HttpClientErrorException e) {
            Log.e("http", e.getStatusCode().toString());
            Log.e("http", e.getResponseBodyAsString(), e);
            erro = e;
        } catch (Exception ex) {
            Log.e("http", ex.getMessage(), ex);
        }

        return (eccs);

    }


    @Override
    protected void onProgressUpdate(String... values) {
        progress.setMessage(values[0]);
    }

    @Override
    protected void onPostExecute(List<Ecc> params) {

        progress.setMessage("Pronto");

        if(params != null && erro == null){
            eccListInterface.depoisBuscaEccs(params,null);
            progress.dismiss();

        }else if(params == null && erro != null){
            eccListInterface.depoisBuscaEccs(params,erro.getStatusCode().toString());
            progress.dismiss();
            //Toast.makeText(this.context, "Nao Encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}
