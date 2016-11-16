package com.magossi.simb.domain.matriz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RafaelMq on 20/10/2016
 */

public class Inseminacao implements Serializable {
    public String id;
    public String dataInseminacao;
    public String previsaoParto;
    public String parto;






    public Inseminacao(String id, String dataInseminacao, String previsaoParto, String parto) {

        this.id = id;
        this.dataInseminacao = dataInseminacao;
        this.previsaoParto = previsaoParto;
        this.parto = parto;
    }

    public static List<Inseminacao> getInseminacoes() {

        List<Inseminacao> inseminacaos = new ArrayList<Inseminacao>();
        inseminacaos.add(new Inseminacao("1", "10/10/2016" , "10/08/2017" , "NÃO"));
        inseminacaos.add(new Inseminacao("2", "10/10/2016" , "10/08/2017" , "SIM"));
        inseminacaos.add(new Inseminacao("3", "10/10/2016" , "10/08/2017" , "SIM"));
        inseminacaos.add(new Inseminacao("4", "10/10/2016" , "10/08/2017" , "SIM"));
        inseminacaos.add(new Inseminacao("5", "10/10/2016" , "10/08/2017" , "SIM"));
        inseminacaos.add(new Inseminacao("6", "10/10/2016" , "10/08/2017" , "SIM"));
        inseminacaos.add(new Inseminacao("7", "10/10/2016" , "10/08/2017" , "SIM"));
        inseminacaos.add(new Inseminacao("8", "10/10/2016" , "10/08/2017" , "SIM"));
        inseminacaos.add(new Inseminacao("9", "10/10/2016" , "10/08/2017" , "SIM"));
        return inseminacaos;
    }
}