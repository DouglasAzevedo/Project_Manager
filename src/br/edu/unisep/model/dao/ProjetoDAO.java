package br.edu.unisep.model.dao;

import br.edu.unisep.hibernate.GenericDAO;
import br.edu.unisep.hibernate.HibernateSessionFactory;
import br.edu.unisep.model.vo.ProjetoVO;
import br.edu.unisep.model.vo.UsuarioVO;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public class ProjetoDAO extends GenericDAO<ProjetoVO> {

    public List<ProjetoVO> listarPorGerente(UsuarioVO gerente){

        var session = HibernateSessionFactory.getSession();

        var q = session.createQuery("from ProjetoVO where gerente.id = :GERENTE", ProjetoVO.class);
        q.setParameter("gerente",gerente.getId());

        var lista = q.list();

        session.close();

        return lista;
    }


}
