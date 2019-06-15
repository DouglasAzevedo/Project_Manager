package br.edu.unisep.model.dao;

import br.edu.unisep.hibernate.GenericDAO;
import br.edu.unisep.hibernate.HibernateSessionFactory;
import br.edu.unisep.model.vo.ProjetoVO;
import br.edu.unisep.model.vo.TarefaVO;
import br.edu.unisep.model.vo.UsuarioVO;

import java.util.List;

public class TarefaDAO extends GenericDAO<TarefaVO> {

    public List<TarefaVO> listar(ProjetoVO projeto){
        var session = HibernateSessionFactory.getSession();

        var q = session.createQuery("from TarefaVO where id_projeto = :PROJETO", TarefaVO.class);
        q.setParameter("PROJETO", projeto.getId());

        var lista = q.list();

        session.close();

        return lista;
    }

    public List<TarefaVO> listarPorStatus(UsuarioVO usuario, Integer status) {
        var session = HibernateSessionFactory.getSession();

        var q = session.createQuery("from TarefaVO where usuario.id = :USUARIO and status = :STATUS", TarefaVO.class);

        q.setParameter("STATUS", status);
        q.setParameter("USUARIO", usuario.getId());

        var lista = q.list();

        session.close();

        return  lista;
    }
}
