package br.edu.ifsul.controllers;

import br.edu.ifsul.dao.CursoDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import br.edu.ifsul.modelo.Curso;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.modelo.Instituicao;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mateus
 */
@ManagedBean(name = "controllerCurso")
@SessionScoped
public class ControllerCurso implements Serializable{
    
    private CursoDAO<Curso> dao;
    private Curso objeto;
    private InstituicaoDAO<Instituicao> daoInstituicao;
    private Boolean novo;
    private Disciplina disciplina;
    private DisciplinaDAO<Disciplina> daoDisciplina;
    
    public ControllerCurso(){
        dao = new CursoDAO<>();
        daoInstituicao = new InstituicaoDAO<>();
        daoDisciplina = new DisciplinaDAO<>();
    }
    
    public void relatorio(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("helpteste", parametros,dao.getListaTodos());
    }
    
    public void adicionarDisciplina(){
        if(disciplina != null){
            if (novo){
                disciplina.setCurso(objeto);
                System.out.println("Curso: "+disciplina.getCurso().getNome());
                objeto.getListaDisciplinas().add(disciplina);
                Util.mensagemInformacao("Disciplina adicionado com sucesso");
            }/*else{
                System.out.println("Curso: " +disciplina.getCurso());
                System.out.println("Nome: " +disciplina.getNome());
                System.out.println("Descricao: " +disciplina.getDescricao());
                System.out.println("Carga Horaria: " +disciplina.getCargaHoraria());
                
                objeto.getListaDisciplinas().remove(disciplina);
                objeto.getListaDisciplinas().add(disciplina);
                Util.mensagemInformacao("Disciplina atualizada com sucesso");
            }*/
        }else{
            System.out.println("Caiu aqui");
        }
    }
    
     public void novaDisciplina(){
        disciplina = new Disciplina();
        System.out.println("nova disciplina instanciada");
        novo = true;
    }
    
    public void alterarDisciplina(int index){
        disciplina = objeto.getListaDisciplinas().get(index);
        novo = false;
    }
    
    public void removerDisciplina(int index){
        objeto.getListaDisciplinas().remove(index);
        Util.mensagemInformacao("A disciplina foi removido com sucesso");
    }
    
    public String listar(){
        return "/privado/curso/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Curso();
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

    public Curso getObjeto() {
        return objeto;
    }

    public void setObjeto(Curso objeto) {
        this.objeto = objeto;
    }

    public CursoDAO<Curso> getDao() {
        return dao;
    }

    public void setDao(CursoDAO<Curso> dao) {
        this.dao = dao;
    }

    public InstituicaoDAO<Instituicao> getDaoInstituicao() {
        return daoInstituicao;
    }

    public void setDaoInstituicao(InstituicaoDAO<Instituicao> daoInstituicao) {
        this.daoInstituicao = daoInstituicao;
    }

    public Boolean getNovoDado() {
        return novo;
    }

    public void setNovoDado(Boolean novo) {
        this.novo = novo;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public DisciplinaDAO<Disciplina> getDaoDisciplina() {
        return daoDisciplina;
    }

    public void setDaoDisciplina(DisciplinaDAO<Disciplina> daoDisciplina) {
        this.daoDisciplina = daoDisciplina;
    }
    
}
