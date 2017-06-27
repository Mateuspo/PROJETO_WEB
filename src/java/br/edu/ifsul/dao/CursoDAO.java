package br.edu.ifsul.dao;

import br.ifsul.edu.modelo.Curso;
import java.io.Serializable;

/**
 *
 * @author Mateus
 */
public class CursoDAO<T> extends DAOGenerico<Curso> implements Serializable{
 
    public CursoDAO(){
        super();
        super.setClassePersistente(Curso.class);
    }
    
    
}
