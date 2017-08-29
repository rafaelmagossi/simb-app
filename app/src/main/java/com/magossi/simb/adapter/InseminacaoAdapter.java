package com.magossi.simb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.magossi.simb.R;
import com.magossi.simb.domain.matriz.Inseminacao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by RafaelMq on 20/10/2016
 */
public class InseminacaoAdapter extends BaseAdapter {
    public final Context context;
    public final List<Inseminacao> inseminacaos;

    public InseminacaoAdapter(Context context, List<Inseminacao> inseminacaos) {

        this.context = context;
        this.inseminacaos = inseminacaos;
    }

    @Override
    public int getCount() {

        return inseminacaos != null ? inseminacaos.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return inseminacaos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Infla a view
        View view = LayoutInflater.from(context).inflate(R.layout.item_inseminacao, parent, false);
        // Faz findViewById das views que precisa atualizar
        TextView textView_id = (TextView) view.findViewById(R.id.textview_inseminacao_id);
        TextView textView_datainseminacao = (TextView) view.findViewById(R.id.textview_inseminacao_datainseminacao);
        TextView textView_previsaoparto = (TextView) view.findViewById(R.id.textview_inseminacao_previsaoparto);
        TextView textView_parto = (TextView) view.findViewById(R.id.textview_inseminacao_parto);


        // Atualiza os valores das views

        Inseminacao inseminacao = inseminacaos.get(position);
        String auxPrevisaoParto = "";
        String auxDataDaInseminacao = "";

        try {
            auxDataDaInseminacao = formataDateToString(inseminacao.getDataDaInseminacao());
        } catch (Exception e) {
            e.printStackTrace();
        }




        textView_id.setText(inseminacao.getIdInseminacao().toString());
        textView_datainseminacao.setText(auxDataDaInseminacao);

        if(inseminacao.getPrevisaoParto() == null){
            textView_previsaoparto.setText("                     ");
        }else {

            try {
                auxPrevisaoParto = formataDateToString(inseminacao.getPrevisaoParto());
            } catch (Exception e) {
                e.printStackTrace();
            }

            textView_previsaoparto.setText(auxPrevisaoParto);
        }
        if(inseminacao.getParto() == null){
            textView_parto.setText(" NAO");
        }else{
            textView_parto.setText(" SIM");
        }

        // Retorna a view deste inseminacao
        return view;
    }


    public static String formataDateToString(Date data) throws Exception {
        if (data == null || data.equals(""))
            return null;
        String Auxdate = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Auxdate = formatter.format(data);
        } catch (Exception e) {
            throw e;
        }
        return Auxdate;
    }
}
