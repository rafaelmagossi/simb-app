package com.magossi.simb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.magossi.simb.R;
import com.magossi.simb.domain.DiagnosticoGestacao;

import java.util.List;

/**
 * Created by RafaelMq on 23/10/2016.
 */
public class DiagnosticoAdapter extends BaseAdapter {

    public final Context context;
    public final List<DiagnosticoGestacao> diagnosticos;

    public DiagnosticoAdapter(Context context, List<DiagnosticoGestacao> diagnosticos) {

        this.context = context;
        this.diagnosticos = diagnosticos;
    }

    @Override
    public int getCount() {

        return diagnosticos != null ? diagnosticos.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return diagnosticos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Infla a view
        View view = LayoutInflater.from(context).inflate(R.layout.item_diagnosticogestacao, parent, false);
        // Faz findViewById das views que precisa atualizar
        TextView textView_id = (TextView) view.findViewById(R.id.textview_diagnostico_id);
        TextView textView_datadiag = (TextView) view.findViewById(R.id.textview_diagnostico_datadiag);
        TextView textView_resultado = (TextView) view.findViewById(R.id.textview_diagnostico_resultado);



        // Atualiza os valores das views
        DiagnosticoGestacao diagnostico = diagnosticos.get(position);

        textView_id.setText(diagnostico.id);
        textView_datadiag.setText(diagnostico.dataDiagnostico);
        textView_resultado.setText(diagnostico.resultado);

        // Retorna a view deste inseminacao
        return view;
    }
}
