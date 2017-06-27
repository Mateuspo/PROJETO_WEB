package br.edu.ifsul.dao;

import br.ifsul.edu.modelo.Disciplina;
import java.io.Serializable;

/**
 *
 * @author Mateus
 */
public class DisciplinaDAO<T> extends DAOGenerico<Disciplina> implements Serializable{
 
    public DisciplinaDAO(){
        super();
        super.setClassePersistente(Disciplina.class);
    }
    
    
}
