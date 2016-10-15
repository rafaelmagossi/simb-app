package com.magossi.simb.domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RafaelMq on 26/09/2016.
 */
public class BovinoService {




    private List<Bovino> bovinos;
    private int qtd = 0;

    public int getQtd() {
        return qtd;
    }



    public static List<Bovino> getBovinos(Context context, int qtd){

        List<Bovino> bovinos = new ArrayList<Bovino>();
        for (int i=qtd; i<qtd+4; i++){

            Bovino b = new Bovino();
            b.nome = "Vaca " + i;
            b.desc = "Descrição " + i;
            b.urlFoto = "http://www.sonhos.com.br/wp-content/uploads/2014/09/sonhar-com-boi-149952899.jpg";
            bovinos.add(b);

        }

        return bovinos;

    }
}
