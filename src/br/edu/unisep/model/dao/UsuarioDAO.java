package br.edu.unisep.model.dao;

import br.edu.unisep.hibernate.GenericDAO;
import br.edu.unisep.hibernate.HibernateSessionFactory;
import br.edu.unisep.model.vo.UsuarioVO;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public class UsuarioDAO extends GenericDAO<UsuarioVO> {

    public UsuarioVO login(UsuarioVO u) {
        var session = HibernateSessionFactory.getSession();

        var q = session.createQuery(
                "from UsuarioVO where email = :EMAIL and senha = :SENHA", UsuarioVO.class);
        q.setParameter("EMAIL", u.getEmail());

        var senha = DigestUtils.md5Hex(u.getSenha());
        q.setParameter("SENHA", senha);

        var usuario = q.uniqueResult();

        session.close();

        return usuario;
    }


    public List<UsuarioVO> listarPorTipo(Integer tipo) {
        var session = HibernateSessionFactory.getSession();

        var q = session.createQuery("from UsuarioVO where tipo = :TIPO", UsuarioVO.class);
        q.setParameter("TIPO", tipo);

        var lista = q.list();

        session.close();

        return lista;
    }

}
