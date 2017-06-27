package br.edu.ifsul.dao;

import br.ifsul.edu.modelo.Professor;
import java.io.Serializable;

/**
 *
 * @author Mateus
 */
public class ProfessorDAO<T> extends DAOGenerico<Professor> implements Serializable{
 
    public ProfessorDAO(){
        super();
        super.setClassePersistente(Professor.class);
    }
    
    
}
