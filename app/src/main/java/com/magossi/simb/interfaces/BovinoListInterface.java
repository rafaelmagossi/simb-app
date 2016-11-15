package com.magossi.simb.interfaces;

import com.magossi.simb.domain.bovino.Bovino;

import java.util.List;

/**
 * Created by RafaelMq on 13/11/2016.
 */
public interface BovinoListInterface {

    public void depoisBuscaBovinos(List<Bovino> bovinos, String erro);
}
