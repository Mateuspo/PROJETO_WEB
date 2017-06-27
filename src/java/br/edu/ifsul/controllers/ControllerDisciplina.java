package br.edu.ifsul.controllers;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.util.Util;
import br.ifsul.edu.modelo.Aluno;
import br.ifsul.edu.modelo.Disciplina;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mateus
 */
@ManagedBean(name = "controllerDisciplina")
@SessionScoped
public class ControllerDisciplina implements Serializable{
    
    private DisciplinaDAO<Disciplina> dao;
    private Disciplina objeto;
    private Aluno aluno;
    private AlunoDAO<Aluno> daoAluno;
    
    public ControllerDisciplina(){
        dao = new DisciplinaDAO<>();
        daoAluno = new AlunoDAO<>();
        
    }
    
    public void adicionarAluno(){
        if(aluno != null){
            if(!objeto.getAlunos().contains(aluno)){
                objeto.getAlunos().add(aluno);
                Util.mensagemInformacao("Aluno adicionado com sucesso");
            }else{
                Util.mensagemErro("O aluno já existe na lista de participantes");
            }
        }
    }
    
    public void removerAluno(int index){
        objeto.getAlunos().remove(index);
        Util.mensagemInformacao("O aluno foi removido com sucesso");
    }
    
    public String listar(){
        return "/privado/disciplina/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Disciplina();
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

    public Disciplina getObjeto() {
        return objeto;
    }

    public void setObjeto(Disciplina objeto) {
        this.objeto = objeto;
    }

    public DisciplinaDAO<Disciplina> getDao() {
        return dao;
    }

    public void setDao(DisciplinaDAO<Disciplina> dao) {
        this.dao = dao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public AlunoDAO<Aluno> getDaoAluno() {
        return daoAluno;
    }

    public void setDaoAluno(AlunoDAO<Aluno> daoAluno) {
        this.daoAluno = daoAluno;
    }
    
    
    
}
