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


import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import com.magossi.simb.domain.Bovino;
import com.magossi.simb.R;

import java.util.List;



public class BovinoAdapter extends RecyclerView.Adapter<BovinoAdapter.BovinoViewHolder>{

    protected static final String TAG = "simb";
    private final List<Bovino> bovinos;
    private final Context context;

    private BovinoOnClickListener bovinoOnClickListener;

    public BovinoAdapter(Context context, List<Bovino> bovinos, BovinoOnClickListener bovinoOnClickListener) {
        this.context = context;
        this.bovinos = bovinos;
        this.bovinoOnClickListener = bovinoOnClickListener;
    }

    @Override
    public int getItemCount() {
        return bovinos.size() ;
    }

    @Override
    public BovinoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Log.i("LOG", "onCreateViewHolder()");

        // Infla a view do layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_bovino, viewGroup, false);

        CardView cardView = (CardView) view.findViewById(R.id.card_view);

        // Cria o ViewHolder
        BovinoViewHolder holder = new BovinoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final BovinoViewHolder holder, final int position) {

        Log.i("LOG", "onBindViewHolder()");

        // Atualiza a view
        Bovino c = bovinos.get(position);

        holder.tNome.setText(c.nome);
        holder.progress.setVisibility(View.VISIBLE);

        Picasso.with(context).load(c.urlFoto).fit().into(holder.img, new Callback() {
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
        if (bovinoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bovinoOnClickListener.onClickBovino(holder.itemView, position); // A variável position é final
                }
            });
        }
    }

    public interface BovinoOnClickListener {
        public void onClickBovino(View view, int idx);
    }

    public void addListItem(Bovino b, int position){
        bovinos.add(b);
        notifyItemInserted(position);
    }


    public void removeListItem(int position){
        bovinos.remove(position);
        notifyItemRemoved(position);
    }

    // ViewHolder com as views
    public static class BovinoViewHolder extends RecyclerView.ViewHolder {
        public TextView tNome;
        ImageView img;
        ProgressBar progress;

        public BovinoViewHolder(View view) {
            super(view);
            // Cria as views para salvar no ViewHolder
            tNome = (TextView) view.findViewById(R.id.text);
            img = (ImageView) view.findViewById(R.id.img);
            progress = (ProgressBar) view.findViewById(R.id.progressImg);
        }
    }
}
