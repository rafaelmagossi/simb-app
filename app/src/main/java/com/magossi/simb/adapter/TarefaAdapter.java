package com.magossi.simb.adapter;

/**
 * Created by RafaelMq on 26/09/2016.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.magossi.simb.R;
import com.magossi.simb.domain.tarefas.Tarefa;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;


public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>{

    private final List<Tarefa> tarefas;
    private final Context context;

    private TarefaOnClickListener tarefaOnClickListener;

    public TarefaAdapter(Context context, List<Tarefa> tarefas, TarefaOnClickListener tarefaOnClickListener) {
        this.context = context;
        this.tarefas = tarefas;
        this.tarefaOnClickListener = tarefaOnClickListener;
    }

    @Override
    public int getItemCount() {
        return tarefas.size() ;
    }

    @Override
    public TarefaViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Log.i("LOG", "onCreateViewHolder()");

        // Infla a view do layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_tarefa, viewGroup, false);

        CardView cardView = (CardView) view.findViewById(R.id.cardView_itemTarefa); // fragment_layout_tarefa

        // Cria o ViewHolder
        TarefaViewHolder holder = new TarefaViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TarefaViewHolder holder, final int position) {

        Log.i("LOG", "onBindViewHolder()");

        // Atualiza a view
        Tarefa t = tarefas.get(position);
        holder.textview_nomeBovino.setText(t.getBovinoMatriz().getNomeBovino());
        holder.textview_nomeTarefa.setText(t.getTipoTarefa().toString());
        holder.progress.setVisibility(View.VISIBLE);

        Picasso.with(context).load(t.getBovinoMatriz().getUrlFoto()).fit().into(holder.img_tarefa, new Callback() {
            @Override
            public void onSuccess() {
                holder.progress.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                holder.progress.setVisibility(View.GONE);
            }
        });

        // Click
        if (tarefaOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tarefaOnClickListener.onClickTarefa(holder.itemView, position); // A variável position é final
                }
            });
        }
    }

    public interface TarefaOnClickListener {
        public void onClickTarefa(View view, int idx);
    }

    public void addListItem(Tarefa t, int position){
        tarefas.add(t);
        notifyItemInserted(position);
    }


    public void removeListItem(int position){
        tarefas.remove(position);
        notifyItemRemoved(position);
    }

    // ViewHolder com as views
    public static class TarefaViewHolder extends RecyclerView.ViewHolder {
        public TextView textview_nomeBovino;
        public TextView textview_nomeTarefa;
        public ImageView img_tarefa;
        public ProgressBar progress;

        public TarefaViewHolder(View view) {
            super(view);
            // Cria as views para salvar no ViewHolder

            img_tarefa = (ImageView) view.findViewById(R.id.img_tarefa);
            progress = (ProgressBar) view.findViewById(R.id.progressImg);
            textview_nomeBovino = (TextView) view.findViewById(R.id.textview_item_tarefa_nomeBovino);
            textview_nomeTarefa = (TextView) view.findViewById(R.id.textview_item_tarefa_nomeTarefa);
        }
    }
}
