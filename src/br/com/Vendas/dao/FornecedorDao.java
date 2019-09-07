package br.com.Vendas.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.Vendas.domain.Fornecedor;
import br.com.Vendas.util.HibernateUtil;

public class FornecedorDao {

	public void salvar(Fornecedor fornecedor) throws Exception {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(fornecedor);
			transacao.commit();
		} catch (RuntimeException ex) {

			if (transacao != null) {
				transacao.rollback();
			}

			throw ex;
		} finally {
			sessao.close();

		}

	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> listar() {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Fornecedor> fornecedor = null;

		try {
			Query consulta = sessao.getNamedQuery("Fornecedor.listar");
			fornecedor = consulta.list();

		} catch (RuntimeException ex) {

			throw ex;
		} finally {
			sessao.close();

		}
		return fornecedor;

	}

	public Fornecedor buscarPorCodigo(Long codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Fornecedor fornecedor = null;

		try {
			Query consulta = sessao.getNamedQuery("Fornecedor.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			fornecedor = (Fornecedor) consulta.uniqueResult();

		} catch (RuntimeException ex) {

			throw ex;
		} finally {
			sessao.close();

		}
		return fornecedor;

	}

	public void excluir(Fornecedor fornecedor) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(fornecedor);
			transacao.commit();
		} catch (RuntimeException ex) {

			if (transacao != null) {
				transacao.rollback();
			}

			throw ex;
		} finally {
			sessao.close();

		}

	}

	/*public void excluirPorCodigo(Long codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			Fornecedor fornecedor = buscarPorCodigo(codigo);
			sessao.delete(fornecedor);
			transacao.commit();
		} catch (RuntimeException ex) {

			if (transacao != null) {
				transacao.rollback();
			}

			throw ex;
		} finally {
			sessao.close();

		}

	}*/

	public void actualizar(Fornecedor  fornecedor) throws Exception {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(fornecedor);
			transacao.commit();
		} catch (RuntimeException ex) {

			if (transacao != null) {
				transacao.rollback();
			}

			throw ex;
		} finally {
			sessao.close();

		}

	}

}
