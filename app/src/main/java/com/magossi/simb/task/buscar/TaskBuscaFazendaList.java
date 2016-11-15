package com.magossi.simb.task.buscar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.magossi.simb.domain.bovino.Fazenda;
import com.magossi.simb.extra.MainConfig;
import com.magossi.simb.interfaces.FazendaListInterface;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by RafaelMq on 06/11/2016.
 */
public class TaskBuscaFazendaList extends AsyncTask<String, String, List<Fazenda>> {


    private HttpClientErrorException erro;
    private Context context;
    private FazendaListInterface fazendaListInterface;
    private ProgressDialog progress;
    private Activity activity;


    public TaskBuscaFazendaList(Context context, Activity activity, FazendaListInterface fazendaListInterface) {
        this.context = context;
        this.activity = activity;
        this.fazendaListInterface = fazendaListInterface;



    }

    @Override
    protected void onPreExecute() {

            progress = new ProgressDialog(context);
            progress.setMessage("Carregando Dados");
            progress.show();

        //super.onPreExecute();
    }


    @Override
    protected List<Fazenda> doInBackground(String... params) {
        List<Fazenda> fazendas = null;


            Fazenda[] f;
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
                f = restTemplate.getForObject(url, Fazenda[].class);
                fazendas = Arrays.asList(f);
                publishProgress("Pronto");

            } catch (HttpClientErrorException e) {
                Log.e("http", e.getStatusCode().toString());
                Log.e("http", e.getResponseBodyAsString(), e);
                erro = e;
            } catch (Exception ex) {
                Log.e("http", ex.getMessage(), ex);
            }
            Log.i("thread", "-> TaskBuscaFAzendasList - doInBackground ");


        return (fazendas);

    }


    @Override
    protected void onProgressUpdate(String... values) {
        progress.setMessage(values[0]);
    }

    @Override
    protected void onPostExecute(List<Fazenda> params) {

        progress.setMessage("Pronto");

        if(params != null && erro == null){
            fazendaListInterface.depoisBuscaFazendas(params,null);
            progress.dismiss();

        }else if(params == null && erro != null){
            fazendaListInterface.depoisBuscaFazendas(params,erro.getStatusCode().toString());
            progress.dismiss();
            //Toast.makeText(this.context, "Nao Encontrado", Toast.LENGTH_SHORT).show();

        }
    }

//    public interface ProprietarioInterface {
//
//        public void depoisBusca(List<Proprietario> proprietarios, String erro);
//
//
//
//    }

}
