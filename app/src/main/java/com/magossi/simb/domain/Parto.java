package com.magossi.simb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RafaelMq on 23/10/2016.
 */
public class Parto implements Serializable {

    public String id;
    public String dataParto;
    public String bovino;


    public Parto(String id, String dataParto, String bovino) {

        this.id = id;
        this.dataParto = dataParto;
        this.bovino = bovino;
    }

    public static List<Parto> getPartos() {

        List<Parto> partos = new ArrayList<Parto>();
        partos.add(new Parto("1", "10/10/2016" , "Aisha 16 DC TE"));
        partos.add(new Parto("2", "10/11/2016" , "Rick 16 DC TE"));
        partos.add(new Parto("3", "10/12/2016" , "Glen 16 DC TE"));
        partos.add(new Parto("4", "10/01/2016" , "Megg 16 DC TE"));
        partos.add(new Parto("5", "10/02/2016" , "Tyrick 16 DC TE"));
        partos.add(new Parto("6", "10/03/2016" , "Erick 16 DC TE"));
        partos.add(new Parto("7", "10/04/2016" , "Osvald 16 DC TE"));
        partos.add(new Parto("8", "10/05/2016" , "Bruce 16 DC TE"));
        partos.add(new Parto("9", "10/06/2016" , "Micha 16 DC TE"));
        partos.add(new Parto("10", "10/07/2016" , "Tomas 16 DC TE"));

        return partos;
    }
}
