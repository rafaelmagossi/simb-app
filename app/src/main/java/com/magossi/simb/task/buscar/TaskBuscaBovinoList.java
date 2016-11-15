package com.magossi.simb.task.buscar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.magossi.simb.domain.bovino.Bovino;
import com.magossi.simb.domain.bovino.Ecc;
import com.magossi.simb.extra.MainConfig;
import com.magossi.simb.interfaces.BovinoListInterface;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by RafaelMq on 13/11/2016.
 */
public class TaskBuscaBovinoList extends AsyncTask<String, String, List<Bovino>> {


    private HttpClientErrorException erro;
    private Context context;
    private BovinoListInterface bovinoListInterface;
    private ProgressDialog progress;
    private Activity activity;


    public TaskBuscaBovinoList(Context context, Activity activity, BovinoListInterface bovinoListInterface) {
        this.context = context;
        this.activity = activity;
        this.bovinoListInterface = bovinoListInterface;



    }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setMessage("Carregando Dados");
        progress.show();

        //super.onPreExecute();
    }


    @Override
    protected List<Bovino> doInBackground(String... params) {
        List<Bovino> bovinos = null;
        Bovino[] bovinos1;
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
            bovinos1 = restTemplate.getForObject(url, Bovino[].class);
            bovinos = Arrays.asList(bovinos1);

            publishProgress("Pronto");

        } catch (HttpClientErrorException e) {
            Log.e("http", e.getStatusCode().toString());
            Log.e("http", e.getResponseBodyAsString(), e);
            erro = e;
        } catch (Exception ex) {
            Log.e("http", ex.getMessage(), ex);
        }

        return (bovinos);

    }


    @Override
    protected void onProgressUpdate(String... values) {
        progress.setMessage(values[0]);
    }

    @Override
    protected void onPostExecute(List<Bovino> params) {

        progress.setMessage("Pronto");

        if(params != null && erro == null){
            bovinoListInterface.depoisBuscaBovinos(params,null);
            progress.dismiss();

        }else if(params == null && erro != null){
            bovinoListInterface.depoisBuscaBovinos(params,erro.getStatusCode().toString());
            progress.dismiss();
            //Toast.makeText(this.context, "Nao Encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}
