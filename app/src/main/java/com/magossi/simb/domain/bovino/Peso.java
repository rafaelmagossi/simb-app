package com.magossi.simb.domain.bovino;




import java.io.Serializable;
import java.util.Date;

/**
 * Created by RafaelMq on 03/11/2016.
 */



public class Peso implements Serializable {


    private Long idPeso;
    private Date dataPesagem;
    private Double peso;
    private Date dataInclusao;
    private Boolean status;

    public Peso(){
        status = true;
    }


    public Long getIdPeso() {
        return idPeso;
    }

    public void setIdPeso(Long idPeso) {
        this.idPeso = idPeso;
    }

    public Date getDataPesagem() {
        return dataPesagem;
    }

    public void setDataPesagem(Date dataPesagem) {
        this.dataPesagem = dataPesagem;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
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
}
