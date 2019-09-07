package br.com.Vendas.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.Vendas.domain.Item;
import br.com.Vendas.util.HibernateUtil;

public class ItemDao {

	public void salvar(Item item) throws Exception {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(item);
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
	public List<Item> listar() {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Item> itens = null;

		try {
			Query consulta = sessao.getNamedQuery("Item.listar");
			itens = consulta.list();

		} catch (RuntimeException ex) {

			throw ex;
		} finally {
			sessao.close();

		}
		return itens;

	}

	public Item buscarPorCodigo(Long codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Item item = null;

		try {
			Query consulta = sessao.getNamedQuery("Item.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			item = (Item) consulta.uniqueResult();

		} catch (RuntimeException ex) {

			throw ex;
		} finally {
			sessao.close();

		}
		return item;

	}

	public void excluir(Item item) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(item);
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

	/*
	 * public void excluirPorCodigo(Long codigo) {
	 * 
	 * Session sessao = HibernateUtil.getSessionFactory().openSession();
	 * 
	 * Transaction transacao = null;
	 * 
	 * try { transacao = sessao.beginTransaction(); Fornecedor fornecedor =
	 * buscarPorCodigo(codigo); sessao.delete(fornecedor); transacao.commit(); }
	 * catch (RuntimeException ex) {
	 * 
	 * if (transacao != null) { transacao.rollback(); }
	 * 
	 * throw ex; } finally { sessao.close();
	 * 
	 * }
	 * 
	 * }
	 */

	public void actualizar(Item item) throws Exception {

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(item);
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
