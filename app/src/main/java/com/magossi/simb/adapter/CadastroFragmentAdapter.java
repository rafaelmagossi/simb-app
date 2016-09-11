package com.magossi.simb.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.magossi.simb.fragment.CadastroFragment1;
import com.magossi.simb.fragment.CadastroFragment2;
import com.magossi.simb.fragment.CadastroFragment3;

/**
 * Created by RafaelMq on 09/09/2016.
 */
public class CadastroFragmentAdapter extends FragmentPagerAdapter {

    private int idAtual;
    private int maxFragments = 3;
    private int minFragments = 1;

    public void setIdAtual(int idAtual) {
        this.idAtual = idAtual;
    }

    public CadastroFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    public int getProximo(){
        if(idAtual == 1){
            return 2;
        }else if(idAtual == 2){
            return 3;
        }return -1;
    }


    public int getAnterior(){
        if(idAtual == 3){
            return 2;
        }else if(idAtual == 2){
            return 1;
        }return -1;
    }

    @Override
    public int getCount() {
        return idAtual;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new CadastroFragment1();
        } else if (position == 2) {
            return new CadastroFragment2();
        }
            return new CadastroFragment3();
    }

}
