package com.magossi.simb.domain.matriz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RafaelMq on 20/10/2016
 */

public class Inseminacao implements Serializable {



    private Long idInseminacao;
    private Boolean monta;
    private String touro;
    private Date dataDaInseminacao;
    private Parto parto;
    private Inseminador inseminador;
    private Date previsaoParto;
    private Date dataInclusao;
    private Boolean status;

    public Inseminacao(){
        status = true;
    }



    public Long getIdInseminacao() {
        return idInseminacao;
    }

    public void setIdInseminacao(Long idInseminacao) {
        this.idInseminacao = idInseminacao;
    }

    public Boolean getMonta() {
        return monta;
    }

    public void setMonta(Boolean monta) {
        this.monta = monta;
    }

    public String getTouro() {
        return touro;
    }

    public void setTouro(String touro) {
        this.touro = touro;
    }

    public Date getDataDaInseminacao() {
        return dataDaInseminacao;
    }

    public void setDataDaInseminacao(Date dataDaInseminacao) {
        this.dataDaInseminacao = dataDaInseminacao;
    }

    public Parto getParto() {
        return parto;
    }

    public void setParto(Parto parto) {
        this.parto = parto;
    }

    public Inseminador getInseminador() {
        return inseminador;
    }

    public void setInseminador(Inseminador inseminador) {
        this.inseminador = inseminador;
    }

    public Date getPrevisaoParto() {
        return previsaoParto;
    }

    public void setPrevisaoParto(Date previsaoParto) {
        this.previsaoParto = previsaoParto;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


//    public String id;
//    public String dataInseminacao;
//    public String previsaoParto;
//    public String parto;


//    public Inseminacao(String id, String dataInseminacao, String previsaoParto, String parto) {
//
//        this.id = id;
//        this.dataInseminacao = dataInseminacao;
//        this.previsaoParto = previsaoParto;
//        this.parto = parto;
//    }

//    public static List<Inseminacao> getInseminacoes() {
//
//        List<Inseminacao> inseminacaos = new ArrayList<Inseminacao>();
//        inseminacaos.add(new Inseminacao("1", "10/10/2016" , "10/08/2017" , "NÃO"));
//        inseminacaos.add(new Inseminacao("2", "10/10/2016" , "10/08/2017" , "SIM"));
//        inseminacaos.add(new Inseminacao("3", "10/10/2016" , "10/08/2017" , "SIM"));
//        inseminacaos.add(new Inseminacao("4", "10/10/2016" , "10/08/2017" , "SIM"));
//        inseminacaos.add(new Inseminacao("5", "10/10/2016" , "10/08/2017" , "SIM"));
//        inseminacaos.add(new Inseminacao("6", "10/10/2016" , "10/08/2017" , "SIM"));
//        inseminacaos.add(new Inseminacao("7", "10/10/2016" , "10/08/2017" , "SIM"));
//        inseminacaos.add(new Inseminacao("8", "10/10/2016" , "10/08/2017" , "SIM"));
//        inseminacaos.add(new Inseminacao("9", "10/10/2016" , "10/08/2017" , "SIM"));
//        return inseminacaos;
//    }
}