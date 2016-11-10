package com.magossi.simb.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by RafaelMq on 08/11/2016.
 */
public class Raca implements Serializable {


    private Long idRaca;

    private String nomeRaca;

    private Date dataInclusao = new Date();

    private Boolean status;


    public Long getIdRaca() {
        return idRaca;
    }

    public void setIdRaca(Long idRaca) {
        this.idRaca = idRaca;
    }

    public String getNomeRaca() {
        return nomeRaca;
    }

    public void setNomeRaca(String nomeRaca) {
        this.nomeRaca = nomeRaca;
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
