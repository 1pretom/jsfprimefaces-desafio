package com.desafioPrimefaces.bean;

import com.desafioPrimefaces.dao.OcorrenciaDAO;
import com.desafioPrimefaces.entidade.Ocorrencia;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class OcorrenciaBean extends CrudBean<Ocorrencia, OcorrenciaDAO> {
    private OcorrenciaDAO ocorrenciaDAO;
    @Override
    public OcorrenciaDAO getDao() {
        if (ocorrenciaDAO == null){
            ocorrenciaDAO = new OcorrenciaDAO();
        }
            return ocorrenciaDAO;
    }
    @Override
    public Ocorrencia criarNovaEntidade() {
        return new Ocorrencia();
    }
}
