package br.com.Vendas.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.Vendas.dao.FornecedorDao;
import br.com.Vendas.dao.ProdutoDao;
import br.com.Vendas.domain.Fornecedor;
import br.com.Vendas.domain.Produto;

public class ProdutoDaoTest {

	// @Test
	@Ignore
	public void salvar() throws Exception {

		FornecedorDao fornecedorDao = new FornecedorDao();
		Fornecedor fornecedor = fornecedorDao.buscarPorCodigo(4L);

		Produto produto = new Produto();
		produto.setDescricao("Acucar");
		produto.setFornecedor(fornecedor);
		produto.setPreco(new BigDecimal(67.7D));
		produto.setQuantidade(3);

		Produto produto2 = new Produto();
		produto2.setDescricao("Oleo");
		produto2.setFornecedor(fornecedor);
		produto2.setPreco(new BigDecimal(129.0D));
		produto2.setQuantidade(10);

		ProdutoDao dao = new ProdutoDao();
		dao.salvar(produto);
		dao.salvar(produto2);

	}

	// @Test
	@Ignore
	public void listar() {
		ProdutoDao dao = new ProdutoDao();
		List<Produto> produtos = dao.listar();
		for (Produto produto : produtos) {
			System.out.println(produto);
		}
	}

	// @Test
	@Ignore
	public void buscarPorCodigo() {
		ProdutoDao dao = new ProdutoDao();
		Produto produto = dao.buscarPorCodigo(4L);

		System.out.println(produto);

	}

	@Test
	// @Ignore
	public void actualizar() throws Exception {

		FornecedorDao fornecedorDao = new FornecedorDao();
		Fornecedor fornecedor = fornecedorDao.buscarPorCodigo(6L);

		ProdutoDao dao = new ProdutoDao();
		Produto produto = dao.buscarPorCodigo(2L);
		produto.setDescricao("Alface");
		;
		produto.setPreco(new BigDecimal(15.23D));
		produto.setQuantidade(9);
		produto.setFornecedor(fornecedor);
		dao.actualizar(produto);

	}

	@Test
	// @Ignore
	public void excluir() throws Exception {
		ProdutoDao dao = new ProdutoDao();
		Produto produto = dao.buscarPorCodigo(8L);

		dao.excluir(produto);

	}

}
