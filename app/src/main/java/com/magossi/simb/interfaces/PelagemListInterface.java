package com.magossi.simb.interfaces;

import com.magossi.simb.domain.bovino.Pelagem;

import java.util.List;

/**
 * Created by RafaelMq on 08/11/2016.
 */
public interface PelagemListInterface {

    public void depoisBuscaPelagens(List<Pelagem> pelagens, String erro);

}
