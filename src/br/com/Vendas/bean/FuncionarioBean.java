package br.com.Vendas.bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.Vendas.dao.FuncionarioDao;
import br.com.Vendas.domain.Funcionario;
import br.com.Vendas.util.JSFUtil;

@ManagedBean(name = "MBFuncionario")
@ViewScoped
public class FuncionarioBean {

	private Funcionario funcionario;

	private ArrayList<Funcionario> itens;
	private ArrayList<Funcionario> itensFiltrados;
	private String acao;
	private Long codigo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Funcionario getfuncionario() {

		return funcionario;
	}

	public void setfuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public ArrayList<Funcionario> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Funcionario> itens) {
		this.itens = itens;
	}

	public ArrayList<Funcionario> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Funcionario> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	// @PostConstruct //A carregar a tela este metodo para listar ja eh chamdo1
	public void prepararPesquisa() {

		try {
			FuncionarioDao fdao = new FuncionarioDao();
			itens = (ArrayList<Funcionario>) fdao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void carregarCadastro() {

		try {
			acao = JSFUtil.getParam("funcionarioAcao");
			String valor = JSFUtil.getParam("funcod");

			if (valor != null) {

				
				Long codigo = Long.parseLong(valor);
				FuncionarioDao fdao = new FuncionarioDao();
				funcionario = fdao.buscarPorCodigo(codigo);

			} else {
				funcionario = new Funcionario();

			}

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void novo() { // Este metodo faz com que ao clicar no botao ADICIONAR
							// NOVO a tela seja limpa
		funcionario = new Funcionario();
	}

	public void salvar() throws Exception {

		try {
			FuncionarioDao fdao = new FuncionarioDao();

			fdao.salvar(funcionario);
			funcionario = new Funcionario();
			// itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("funcionario salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			FuncionarioDao fdao = new FuncionarioDao();
			fdao.excluir(funcionario);

			JSFUtil.adicionarMensagemSucesso("funcionario excluido com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir um funcionario que tenha uma venda associada!");
			e.printStackTrace();
		}
	}

	public void editar() throws Exception {
		try {
			FuncionarioDao fdao = new FuncionarioDao();
			fdao.actualizar(funcionario);

			JSFUtil.adicionarMensagemSucesso("Dados do funcionario actualizados com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

}
