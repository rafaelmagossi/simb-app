package com.magossi.simb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.magossi.simb.R;
import com.magossi.simb.domain.matriz.Parto;

import java.util.List;

/**
 * Created by RafaelMq on 23/10/2016.
 */
public class PartoAdapter extends BaseAdapter {

    public final Context context;
    public final List<Parto> partos;

    public PartoAdapter(Context context, List<Parto> partos) {

        this.context = context;
        this.partos = partos;
    }

    @Override
    public int getCount() {

        return partos != null ? partos.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return partos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Infla a view
        View view = LayoutInflater.from(context).inflate(R.layout.item_parto, parent, false);
        // Faz findViewById das views que precisa atualizar
        TextView textView_id = (TextView) view.findViewById(R.id.textview_parto_id);
        TextView textView_dataparto = (TextView) view.findViewById(R.id.textview_parto_dataparto);
        TextView textView_bovino = (TextView) view.findViewById(R.id.textview_parto_bovino);



        // Atualiza os valores das views
        Parto parto = partos.get(position);

        textView_id.setText(parto.id);
        textView_dataparto.setText(parto.dataParto);
        textView_bovino.setText(parto.bovino);

        // Retorna a view deste inseminacao
        return view;
    }
}
