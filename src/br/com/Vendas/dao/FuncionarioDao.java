package br.com.Vendas.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.Vendas.domain.Funcionario;
import br.com.Vendas.util.HibernateUtil;

public class FuncionarioDao {

	public void salvar(Funcionario funcionario) throws Exception {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(funcionario);
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
	public List<Funcionario> listar() {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Funcionario> funcionarios = null;

		try {
			Query consulta = sessao.getNamedQuery("Funcionario.listar");
			funcionarios = consulta.list();

		} catch (RuntimeException ex) {

			throw ex;
		} finally {
			sessao.close();

		}
		return funcionarios;

	}

	public Funcionario buscarPorCodigo(Long codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Funcionario funcionario = null;

		try {
			Query consulta = sessao.getNamedQuery("Funcionario.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			funcionario = (Funcionario) consulta.uniqueResult();

		} catch (RuntimeException ex) {

			throw ex;
		} finally {
			sessao.close();

		}
		return funcionario;

	}

	public void excluir(Funcionario funcionario) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(funcionario);
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

	public void actualizar(Funcionario  funcionario) throws Exception {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(funcionario);
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
