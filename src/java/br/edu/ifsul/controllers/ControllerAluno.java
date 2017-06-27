package br.edu.ifsul.controllers;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.modelo.Aluno;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mateus
 */
@ManagedBean(name = "controllerAluno")
@SessionScoped
public class ControllerAluno implements Serializable{
    
    private AlunoDAO<Aluno> dao;
    private Aluno objeto;
    
    public ControllerAluno(){
        dao = new AlunoDAO<>();
    }
    
    public String listar(){
        return "/privado/aluno/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Aluno();
        return "formulario";
    }
    
    public String salvar(){
        boolean persistiu;
        
        if(objeto.getId() == null){
            persistiu = getDao().persist(objeto);
        }else{
            persistiu = getDao().merge(objeto);
        }
        
        if(persistiu){
            Util.mensagemInformacao(getDao().getMensagem());
            return "listar";
        }else{
            Util.mensagemErro(getDao().getMensagem());
            return "formulario";
        }
    }
    
    public String cancelar(){
        return "listar";
    }
    
    public String editar(Integer id){
        
        try {
            objeto = getDao().localizar(id);
            return "formulario";
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
            return "listar";
        }
    }
    
    public void remover(Integer id){
        objeto = getDao().localizar(id);
        if(getDao().remover(objeto)){
            Util.mensagemInformacao(getDao().getMensagem());
        }else{
            Util.mensagemErro(getDao().getMensagem());
        }
    }

    public Aluno getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluno objeto) {
        this.objeto = objeto;
    }

    public AlunoDAO<Aluno> getDao() {
        return dao;
    }

    public void setDao(AlunoDAO<Aluno> dao) {
        this.dao = dao;
    }
    
    
    
}
