package com.magossi.simb.task.buscar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.magossi.simb.domain.bovino.Proprietario;
import com.magossi.simb.extra.MainConfig;
import com.magossi.simb.interfaces.ProprietarioListInterface;


import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

/**
 * Created by RafaelMq on 06/11/2016.
 */
public class TaskBuscaProprietarioList extends AsyncTask<String, String, List<Proprietario>> {


    private HttpClientErrorException erro;
    private Context context;
    private ProprietarioListInterface proprietarioListInterface;
    private ProgressDialog progress;
    private Activity activity;
    boolean cancelado = false;


    public TaskBuscaProprietarioList(Context context, Activity activity, ProprietarioListInterface proprietarioListInterface) {
        this.context = context;
        this.activity = activity;
        this.proprietarioListInterface = proprietarioListInterface;



    }

    @Override
    protected void onPreExecute() {
            progress = new ProgressDialog(context);
            progress.setMessage("Carregando Dados");
            progress.show();
        //super.onPreExecute();
    }




    @Override
    protected List<Proprietario> doInBackground(String... params) {

        List<Proprietario> proprietarios = null;

            Proprietario[] p;
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
                p = restTemplate.getForObject(url, Proprietario[].class);
                proprietarios = Arrays.asList(p);
                publishProgress("Pronto");


            } catch (ResourceAccessException ex1) {
                Log.i("http", "Sem Conexão Servidor - ");


            } catch (HttpClientErrorException ex2) {
                Log.e("http", ex2.getStatusCode().toString());
                Log.e("http", ex2.getResponseBodyAsString(), ex2);
                erro = ex2;
            } catch (Exception ex3) {
                Log.e("http", ex3.getMessage(), ex3);
            }


            Log.i("thread", "-> TaskBuscaProprietarioList - doInBackground ");


        return (proprietarios);
    }


    @Override
    protected void onProgressUpdate(String... values) {
        progress.setMessage(values[0]);
    }

    @Override
    protected void onPostExecute(List<Proprietario> params) {

        progress.setMessage("Pronto");

        if(params != null && erro == null){
            proprietarioListInterface.depoisBuscaProp(params,null);
            //proprietarioInterface.depoisBusca(params,null);
            progress.dismiss();

        }else if(params == null && erro != null){
            proprietarioListInterface.depoisBuscaProp(params,erro.getStatusCode().toString());
            //proprietarioInterface.depoisBusca(params,erro.getStatusCode().toString());
            progress.dismiss();
            //Toast.makeText(this.context, "Nao Encontrado", Toast.LENGTH_SHORT).show();


        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

//    public interface ProprietarioInterface {
//
//        public void depoisBusca(List<Proprietario> proprietarios, String erro);
//
//
//
//    }

}
