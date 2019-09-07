package br.com.Vendas.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.Vendas.dao.FornecedorDao;
import br.com.Vendas.domain.Fornecedor;

public class FornecedorDaoTest {

	@Test
	//@Ignore
	public void salvar() throws Exception {

		Fornecedor fornecedor = new Fornecedor();
		Fornecedor fornecedor2 = new Fornecedor();

		fornecedor.setDescricao("Henrique Abdul");
		fornecedor2.setDescricao("Muenda Luisa");


		FornecedorDao dao = new FornecedorDao();
		dao.salvar(fornecedor);
		dao.salvar(fornecedor2);

	}

	@Test
	@Ignore
	public void listar() {
		FornecedorDao dao = new FornecedorDao();
		List<Fornecedor> fornecedores = dao.listar();
		for (Fornecedor fornecedor : fornecedores) {
			System.out.println(fornecedor);
		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		FornecedorDao dao = new FornecedorDao();
		Fornecedor fornecedor = dao.buscarPorCodigo(2L);
		Fornecedor fornecedor2 = dao.buscarPorCodigo(3L);

		System.out.println(fornecedor);
		System.out.println(fornecedor2);

	}

	@Test
	@Ignore
	public void excluir() throws Exception {
		FornecedorDao dao = new FornecedorDao();
		Fornecedor fornecedor = dao.buscarPorCodigo(7L);

			dao.excluir(fornecedor);

	}
	
	

/*	@Test
	@Ignore
	public void excluirPorCodigo() throws Exception {
		FornecedorDao dao = new FornecedorDao();
		dao.excluirPorCodigo(3L);

	}*/

	@Test
	public void actualizar() throws Exception {
		
		FornecedorDao dao = new FornecedorDao();
		Fornecedor fornecedor = dao.buscarPorCodigo(7L);
		fornecedor.setDescricao("Marcos Henrique");
		dao.actualizar(fornecedor);

	}

}
