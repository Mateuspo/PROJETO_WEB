package br.edu.ifsul.controllers;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.dao.ProfessorDAO;
import br.edu.ifsul.util.Util;
import br.ifsul.edu.modelo.Especialidade;
import br.ifsul.edu.modelo.Professor;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mateus
 */
@ManagedBean(name = "controllerProfessor")
@SessionScoped
public class ControllerProfessor implements Serializable{
    
    private ProfessorDAO<Professor> dao;
    private Professor objeto;
    private EspecialidadeDAO<Especialidade> daoEspecialidade;
    
    public ControllerProfessor(){
        dao = new ProfessorDAO<>();
        daoEspecialidade = new EspecialidadeDAO<>();
    }
    
    public String listar(){
        return "/privado/professor/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Professor();
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

    public Professor getObjeto() {
        return objeto;
    }

    public void setObjeto(Professor objeto) {
        this.objeto = objeto;
    }

    public ProfessorDAO<Professor> getDao() {
        return dao;
    }

    public void setDao(ProfessorDAO<Professor> dao) {
        this.dao = dao;
    }

    public EspecialidadeDAO<Especialidade> getDaoEspecialidade() {
        return daoEspecialidade;
    }

    public void setDaoEspecialidade(EspecialidadeDAO<Especialidade> daoEspecialidade) {
        this.daoEspecialidade = daoEspecialidade;
    }
    
    
    
}
