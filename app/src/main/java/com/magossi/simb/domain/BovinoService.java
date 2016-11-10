package com.magossi.simb.domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
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

        Proprietario proprietario1 = new Proprietario();
        Proprietario proprietario2 = new Proprietario();
        Fazenda fazenda = new Fazenda();
        Pelagem pelagem = new Pelagem();
        Raca raca = new Raca();
        Date data = new Date();


        proprietario1.setNomeProprietario("Rafael Magossi");
        proprietario2.setNomeProprietario("Matheus Lino");
        data.setDate(24);
        data.setMonth(03);
        data.setYear(2013);

        List<Bovino> bovinos = new ArrayList<Bovino>();
        for (int i=qtd; i<qtd+4; i++){

            if( (i%2)==0 ) {

                Bovino b = new Bovino();
                b.setNomeBovino("Nisha 16 DC TE - B");
                b.setPai("Batoque da Floresta");
                b.setMae("Elegance I");
                b.setDataNascimento(data);
                b.setProprietario(proprietario1);
                b.setFazenda(fazenda);   /* "Santa Helena - B" + i;  */
                b.setGenero(false);
                b.setRaca(raca);   /* "Nelore - B" + i; */
                b.setPelagem(pelagem);   /* = "Branca - B" + i; */
                b.desc = "Descrição - B" + i;
                b.setUrlFoto("http://www.sonhos.com.br/wp-content/uploads/2014/09/sonhar-com-boi-149952899.jpg");
                bovinos.add(b);

            }else{

                Bovino b = new Bovino();
                b.setNomeBovino("Varman TE Angico");
                b.setPai("Batoque da Floresta");
                b.setMae("Nini TE Angico (Garimpo 707 da Goya) - B");
                b.setDataNascimento(data);
                b.setProprietario(proprietario2);
                b.setFazenda(fazenda);   /* "Santa Helena - B" + i;  */
                b.setGenero(true);
                b.setRaca(raca);   /* "Nelore - B" + i; */
                b.setPelagem(pelagem);   /* = "Branca - B" + i; */
                b.desc = "Descrição - B" + i;
                b.setUrlFoto("https://tiacarmela.files.wordpress.com/2012/06/boi-olho.jpeg");
                bovinos.add(b);


            }

        }

        return bovinos;

    }
}
