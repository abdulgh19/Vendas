package br.com.Vendas.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.Vendas.dao.FuncionarioDao;
import br.com.Vendas.dao.VendaDao;
import br.com.Vendas.domain.Funcionario;
import br.com.Vendas.domain.Venda;

public class VendaDaoTest {

	//@Test
	@Ignore
	public void salvar() throws Exception {

		FuncionarioDao funcionarioDao = new FuncionarioDao();
		Funcionario funcionario = funcionarioDao.buscarPorCodigo(2L);

		Venda venda = new Venda();
		venda.setFuncionario(funcionario);
		venda.setValorTotal(new BigDecimal(6565.20D));
		venda.setHorario(new Date());

		Venda venda2 = new Venda();
		venda2.setFuncionario(funcionario);
		venda2.setValorTotal(new BigDecimal(656.55D));
		venda2.setHorario(new Date());

		VendaDao dao = new VendaDao();
		dao.salvar(venda);
		dao.salvar(venda2);

	}

	@Test
	//@Ignore
	public void listar() {
		VendaDao dao = new VendaDao();
		List<Venda> vendas = dao.listar();
		for (Venda venda : vendas) {
			System.out.println(venda);
		}
	}

	//@Test
	@Ignore
	public void buscarPorCodigo() {
		VendaDao dao = new VendaDao();
		Venda venda = dao.buscarPorCodigo(3L);

		System.out.println(venda);

	}

	//@Test
	@Ignore
	public void actualizar() throws Exception {

		FuncionarioDao funcionarioDao = new FuncionarioDao();
		Funcionario funcionario = funcionarioDao.buscarPorCodigo(5L);

		VendaDao dao = new VendaDao();
		Venda venda = new Venda();
		venda = dao.buscarPorCodigo(3L);
		venda.setFuncionario(funcionario);
		venda.setHorario(new Date());
		venda.setValorTotal(new BigDecimal(96.86D));
		dao.actualizar(venda);

	}

	//@Test
	@Ignore
	public void excluir() throws Exception {
		VendaDao dao = new VendaDao();
		Venda venda = dao.buscarPorCodigo(4L);

		dao.excluir(venda);

	}

}
