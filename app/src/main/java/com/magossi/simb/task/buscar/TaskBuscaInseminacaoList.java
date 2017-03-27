package com.magossi.simb.task.buscar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magossi.simb.domain.matriz.Inseminacao;
import com.magossi.simb.extra.MainConfig;

import com.magossi.simb.interfaces.buscar.InseminacaoListInterface;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by RafaelMq on 13/11/2016.
 */
public class TaskBuscaInseminacaoList extends AsyncTask<String, String, List<Inseminacao>> {


    private HttpClientErrorException erro;
    private Context context;
    private InseminacaoListInterface inseminacaoListInterface;
    private ProgressDialog progress;
    private Activity activity;


    public TaskBuscaInseminacaoList(Context context, Activity activity, InseminacaoListInterface inseminacaoListInterface) {
        this.context = context;
        this.activity = activity;
        this.inseminacaoListInterface = inseminacaoListInterface;



    }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setMessage("Carregando Dados");
        progress.show();

        //super.onPreExecute();
    }


    @Override
    protected List<Inseminacao> doInBackground(String... params) {
        List<Inseminacao> inseminacaos = null;
        Inseminacao[] inseminacaosAux;
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
            inseminacaosAux = restTemplate.getForObject(url, Inseminacao[].class);
            inseminacaos = Arrays.asList(inseminacaosAux);

            publishProgress("Pronto");

        } catch (HttpClientErrorException e) {
            Log.e("http", e.getStatusCode().toString());
            Log.e("http", e.getResponseBodyAsString(), e);
            erro = e;
        } catch (Exception ex) {
            Log.e("http", ex.getMessage(), ex);
        }

        return (inseminacaos);

    }


    @Override
    protected void onProgressUpdate(String... values) {
        progress.setMessage(values[0]);
    }

    @Override
    protected void onPostExecute(List<Inseminacao> params) {

        progress.setMessage("Pronto");

        if(params != null && erro == null){
            inseminacaoListInterface.depoisBuscaInseminacoes(params,null);
            progress.dismiss();

        }else if(params == null && erro != null){
            inseminacaoListInterface.depoisBuscaInseminacoes(params,erro.getStatusCode().toString());
            progress.dismiss();
            //Toast.makeText(this.context, "Nao Encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}
