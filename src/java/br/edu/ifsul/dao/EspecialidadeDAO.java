package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Especialidade;
import java.io.Serializable;

/**
 *
 * @author Mateus
 */
public class EspecialidadeDAO<T> extends DAOGenerico<Especialidade> implements Serializable{
 
    public EspecialidadeDAO(){
        super();
        super.setClassePersistente(Especialidade.class);
    }
    
    
}
