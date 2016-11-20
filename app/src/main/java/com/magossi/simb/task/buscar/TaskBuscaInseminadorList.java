package com.magossi.simb.task.buscar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magossi.simb.domain.bovino.Bovino;
import com.magossi.simb.domain.matriz.Inseminador;
import com.magossi.simb.extra.MainConfig;
import com.magossi.simb.interfaces.buscar.BovinoListInterface;
import com.magossi.simb.interfaces.buscar.InseminadorListInterface;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by RafaelMq on 13/11/2016.
 */
public class TaskBuscaInseminadorList extends AsyncTask<String, String, List<Inseminador>> {


    private HttpClientErrorException erro;
    private Context context;
    private InseminadorListInterface inseminadorListInterface;
    private ProgressDialog progress;
    private Activity activity;


    public TaskBuscaInseminadorList(Context context, Activity activity, InseminadorListInterface inseminadorListInterface) {
        this.context = context;
        this.activity = activity;
        this.inseminadorListInterface = inseminadorListInterface;



    }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setMessage("Carregando Dados");
        progress.show();

        //super.onPreExecute();
    }


    @Override
    protected List<Inseminador> doInBackground(String... params) {
        List<Inseminador> inseminadores = null;
        Inseminador[] Inseminadores1;
        //String URL = "http://192.168.0.100:8080/"
        String URL = MainConfig.getUrl()
                + params[0]
                + "/{opcao}";

        try {
            erro = null;
            publishProgress("Aguarde");
            String url = URL.replace("{opcao}", params[1]);


            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
            mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
            Inseminadores1 = restTemplate.getForObject(url, Inseminador[].class);
            inseminadores = Arrays.asList(Inseminadores1);

            publishProgress("Pronto");

        } catch (HttpClientErrorException e) {
            Log.e("http", e.getStatusCode().toString());
            Log.e("http", e.getResponseBodyAsString(), e);
            erro = e;
        } catch (Exception ex) {
            Log.e("http", ex.getMessage(), ex);
        }

        return (inseminadores);

    }


    @Override
    protected void onProgressUpdate(String... values) {
        progress.setMessage(values[0]);
    }

    @Override
    protected void onPostExecute(List<Inseminador> params) {

        progress.setMessage("Pronto");

        if(params != null && erro == null){
            inseminadorListInterface.depoisBuscaInseminadores(params,null);
            progress.dismiss();

        }else if(params == null && erro != null){
            inseminadorListInterface.depoisBuscaInseminadores(params,erro.getStatusCode().toString());
            progress.dismiss();
            //Toast.makeText(this.context, "Nao Encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}
