package br.com.Vendas.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.Vendas.dao.ItemDao;
import br.com.Vendas.dao.ProdutoDao;
import br.com.Vendas.dao.VendaDao;
import br.com.Vendas.domain.Item;
import br.com.Vendas.domain.Produto;
import br.com.Vendas.domain.Venda;

public class ItemDaoTest {

	// @Test
	@Ignore
	public void salvar() throws Exception {

		ProdutoDao produtoDao = new ProdutoDao();
		Produto produto = produtoDao.buscarPorCodigo(2L);
		Produto produto2 = produtoDao.buscarPorCodigo(9L);

		VendaDao vendaDao = new VendaDao();
		Venda venda = vendaDao.buscarPorCodigo(3L);

		Item item = new Item();
		item.setProduto(produto);
		item.setVenda(venda);
		item.setQuantidade(6);
		item.setValorParcial(new BigDecimal(17.33D));

		Item item2 = new Item();
		item2.setProduto(produto2);
		item2.setVenda(venda);
		item2.setQuantidade(9);
		item2.setValorParcial(new BigDecimal(11.25D));

		ItemDao dao = new ItemDao();
		dao.salvar(item);
		dao.salvar(item2);

	}

	// @Test
	@Ignore
	public void listar() {
		ItemDao dao = new ItemDao();
		List<Item> items = dao.listar();
		for (Item item : items) {
			System.out.println(item);
		}
	}

	// @Test
	@Ignore
	public void buscarPorCodigo() {
		ItemDao dao = new ItemDao();
		Item item = dao.buscarPorCodigo(4L);

		System.out.println(item);

	}

	// @Test
	@Ignore
	public void actualizar() throws Exception {

		ProdutoDao produtoDao = new ProdutoDao();
		Produto produto = produtoDao.buscarPorCodigo(7L);

		VendaDao vendaDao = new VendaDao();
		Venda venda = vendaDao.buscarPorCodigo(1L);

		ItemDao dao = new ItemDao();
		Item item = dao.buscarPorCodigo(2L);
		item.setValorParcial(new BigDecimal(17.9D));
		item.setProduto(produto);
		item.setVenda(venda);
		item.setQuantidade(11);

		dao.actualizar(item);

	}

	@Test
	// @Ignore
	public void excluir() throws Exception {
		ItemDao dao = new ItemDao();
		Item item = dao.buscarPorCodigo(4L);

		dao.excluir(item);

	}

}
