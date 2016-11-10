package com.magossi.simb.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.magossi.simb.domain.Raca;
import com.magossi.simb.interfaces.RacaListInterface;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by RafaelMq on 08/11/2016.
 */
public class TaskBuscaRacaList extends AsyncTask<String, String, List<Raca>> {


    private HttpClientErrorException erro;
    private Context context;
    private RacaListInterface racaListInterface;
    private ProgressDialog progress;
    private Activity activity;


    public TaskBuscaRacaList(Context context, Activity activity, RacaListInterface racaListInterface) {
        this.context = context;
        this.activity = activity;
        this.racaListInterface = racaListInterface;



    }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setMessage("Carregando Dados");
        progress.show();




        //super.onPreExecute();
    }


    @Override
    protected List<Raca> doInBackground(String... params) {



        List<Raca> racas = null;
        Raca[] r;
        String URL = "http://192.168.0.100:8080/"
                + params[0]
                + "/{opcao}";

        try {
            erro = null;
            publishProgress("Aguarde");
            String url = URL.replace("{opcao}", params[1]);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            r = restTemplate.getForObject(url, Raca[].class);
            racas = Arrays.asList(r);
            publishProgress("Pronto");


        } catch (ResourceAccessException ex1){
            Log.i("http", "Sem Conexão Servidor - ");


        } catch (HttpClientErrorException ex2) {
            Log.e("http", ex2.getStatusCode().toString());
            Log.e("http", ex2.getResponseBodyAsString(), ex2);
            erro = ex2;
        } catch (Exception ex3) {
            Log.e("http", ex3.getMessage(), ex3);
        }


        Log.i("thread", "-> TaskBuscaProprietarioList - doInBackground ");
        return (racas);

    }


    @Override
    protected void onProgressUpdate(String... values) {
        progress.setMessage(values[0]);
    }

    @Override
    protected void onPostExecute(List<Raca> params) {

        progress.setMessage("Pronto");

        if(params != null && erro == null){
            racaListInterface.depoisBuscaRaca(params,null);
            //proprietarioInterface.depoisBusca(params,null);
            progress.dismiss();

        }else if(params == null && erro != null){
            racaListInterface.depoisBuscaRaca(params,erro.getStatusCode().toString());
            //proprietarioInterface.depoisBusca(params,erro.getStatusCode().toString());
            progress.dismiss();
            //Toast.makeText(this.context, "Nao Encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}
