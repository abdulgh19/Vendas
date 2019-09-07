package br.com.Vendas.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.Vendas.dao.FuncionarioDao;
import br.com.Vendas.domain.Funcionario;

public class FuncionarioDaoTest {

	@Test
	// @Ignore
	public void salvar() throws Exception {

		Funcionario funcionario = new Funcionario();
		Funcionario funcionario2 = new Funcionario();

		funcionario.setNome("Henrique Abdul");
		funcionario2.setNome("Muenda Luisa");

		funcionario.setFuncao("Tecnico");
		funcionario2.setFuncao("Professora");

		funcionario.setSenha("abdc23");
		funcionario2.setSenha("56567b");

		funcionario.setNuit("5666743");
		funcionario2.setNuit("788787");

		FuncionarioDao dao = new FuncionarioDao();
		dao.salvar(funcionario);
		dao.salvar(funcionario2);

	}

	@Test
	@Ignore
	public void listar() {
		FuncionarioDao dao = new FuncionarioDao();
		List<Funcionario> funcionarios = dao.listar();
		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario);
		}
	}

	@Test
	//@Ignore
	public void buscarPorCodigo() {
		FuncionarioDao dao = new FuncionarioDao();
		Funcionario funcionario = dao.buscarPorCodigo(2L);
		Funcionario funcionario2 = dao.buscarPorCodigo(3L);

		System.out.println(funcionario);
		System.out.println(funcionario2);

	}

	@Test
	//@Ignore
	public void excluir() throws Exception {
		FuncionarioDao dao = new FuncionarioDao();
		Funcionario funcionario = dao.buscarPorCodigo(7L);

		dao.excluir(funcionario);

	}

	/*
	 * @Test
	 * 
	 * @Ignore public void excluirPorCodigo() throws Exception { FornecedorDao
	 * dao = new FornecedorDao(); dao.excluirPorCodigo(3L);
	 * 
	 * }
	 */

	@Test
	//@Ignore
	public void actualizar() throws Exception {

		FuncionarioDao dao = new FuncionarioDao();
		Funcionario funcionario = dao.buscarPorCodigo(4L);
		funcionario.setNome("Txio Adam");
		funcionario.setFuncao("Estragar tudo ca em casa");
		funcionario.setNuit("1323434");
		funcionario.setSenha("ssss3");
		dao.actualizar(funcionario);

	}

}
