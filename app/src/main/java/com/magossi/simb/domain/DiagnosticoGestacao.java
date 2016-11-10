package com.magossi.simb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RafaelMq on 23/10/2016.
 */
public class DiagnosticoGestacao implements Serializable {

    public String id;
    public String dataDiagnostico;
    public String resultado;


    public DiagnosticoGestacao(String id, String dataDiagnostico, String resultado) {

        this.id = id;
        this.dataDiagnostico = dataDiagnostico;
        this.resultado = resultado;
    }

    public static List<DiagnosticoGestacao> getDiagnosticos() {
        // dIAGNOSTICOS
        List<DiagnosticoGestacao> diagnosticos = new ArrayList<DiagnosticoGestacao>();
        diagnosticos.add(new DiagnosticoGestacao("1", "10/10/2016" , "CHEIA"));
        diagnosticos.add(new DiagnosticoGestacao("2", "10/11/2016" , "VAZIA"));
        diagnosticos.add(new DiagnosticoGestacao("3", "10/12/2016" , "VAZIA"));
        diagnosticos.add(new DiagnosticoGestacao("4", "10/01/2016" , "VAZIA"));
        diagnosticos.add(new DiagnosticoGestacao("5", "10/02/2016" , "VAZIA"));
        diagnosticos.add(new DiagnosticoGestacao("6", "10/03/2016" , "VAZIA"));
        diagnosticos.add(new DiagnosticoGestacao("7", "10/04/2016" , "VAZIA"));
        diagnosticos.add(new DiagnosticoGestacao("8", "10/05/2016" , "VAZIA"));
        diagnosticos.add(new DiagnosticoGestacao("9", "10/06/2016" , "VAZIA"));
        diagnosticos.add(new DiagnosticoGestacao("10", "10/07/2016" , "VAZIA"));

        return diagnosticos;
    }
}
