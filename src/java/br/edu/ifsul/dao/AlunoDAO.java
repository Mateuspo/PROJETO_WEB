package br.edu.ifsul.dao;

import br.ifsul.edu.modelo.Aluno;
import java.io.Serializable;

/**
 *
 * @author Mateus
 */
public class AlunoDAO<T> extends DAOGenerico<Aluno> implements Serializable{
 
    public AlunoDAO(){
        super();
        super.setClassePersistente(Aluno.class);
    }
    
    
}
