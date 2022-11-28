package com.desafioPrimefaces.bean;

import com.desafioPrimefaces.dao.CrudDAO;
import com.desafioPrimefaces.util.exception.ErroSistema;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CrudBean <E, D extends CrudDAO>{
    private String estadoTela = "buscar";

    private E entidade;
    private List<E> entidades;

    public void novo(){
        entidade = criarNovaEntidade();
        mudarParaInseri();
    }
    public void salvar(){
        try {
            getDao().salvar(entidade);
            entidade = criarNovaEntidade();
            adicionarMensagem("Salvo com sucesso", FacesMessage.SEVERITY_INFO);
            mudarParabusca();
        } catch (ErroSistema e) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, e);
            adicionarMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    public void editar(E entidade){
        this.entidade = entidade;
        mudarParaEdita();
    }
    public void deletar(E entidade){
        try {
            getDao().deletar(entidade);
            entidades.remove(entidade);
            adicionarMensagem("Deletado com sucesso", FacesMessage.SEVERITY_WARN);
            mudarParabusca();
        } catch (ErroSistema e) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, e);
            adicionarMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    public void buscar(){
        if (!isBusca()){
            return;
        }
        try{
            entidades = getDao().buscar();
            if (entidades == null || entidades.size() < 1){
                adicionarMensagem("Não temos nada cadastrado", FacesMessage.SEVERITY_WARN);
            }
        } catch (ErroSistema e){
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, e);
            adicionarMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro){
        FacesMessage facesMessage = new FacesMessage(tipoErro, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null,facesMessage);
    }

    public E getEntidade() {
        return entidade;
    }
    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }
    public List<E> getEntidades() {
        return entidades;
    }
    public void setEntidades(List<E> entidades) {
        this.entidades = entidades;
    }

    //Responsável por criar os métodos nas classes bean
    public abstract D getDao();
    public abstract E criarNovaEntidade();

    //metodos para controle da tela
    public boolean isInseri(){
        return "inserir".equals(estadoTela);
    }
    public boolean isEdita(){
        return "editar".equals(estadoTela);
    }
    public boolean isBusca(){
        return "buscar".equals(estadoTela);
    }

    //mudar estado da tela
    public void mudarParaInseri(){
        estadoTela = "inserir";
    }
    public void mudarParaEdita(){
        estadoTela = "editar";
    }
    public void mudarParabusca(){
        estadoTela = "buscar";
    }
}
