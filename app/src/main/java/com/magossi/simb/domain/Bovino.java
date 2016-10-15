package com.magossi.simb.domain;

import java.io.Serializable;

/**
 * Created by RafaelMq on 26/09/2016.
 */
public class Bovino implements Serializable{
    private static final long serialVerionUID = 6601006766832473959L;

    public long id;
    public String tag;
    public String nome;
    public String desc;
    public String urlFoto;
    public String urlInfo;

    @Override
    public String toString() {
        return "Bovino{" + "nome='" + nome + '\'' + '}';
    }
}
