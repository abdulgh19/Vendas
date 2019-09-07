package br.com.Vendas.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.Vendas.dao.FornecedorDao;
import br.com.Vendas.domain.Fornecedor;
import br.com.Vendas.util.JSFUtil;

@ManagedBean(name = "MBFornecedor")
@ViewScoped
public class FornecedorBean {

	private Fornecedor fornecedor;

	private ArrayList<Fornecedor> itens;
	private ArrayList<Fornecedor> itensFiltrados;
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

	public Fornecedor getFornecedor() {

		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public ArrayList<Fornecedor> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fornecedor> itens) {
		this.itens = itens;
	}

	public ArrayList<Fornecedor> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fornecedor> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	// @PostConstruct //A carregar a tela este metodo para listar ja eh chamdo1
	public void prepararPesquisa() {

		try {
			FornecedorDao fdao = new FornecedorDao();
			itens = (ArrayList<Fornecedor>) fdao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void carregarCadastro() {

		try {
			acao = JSFUtil.getParam("fornecedorAcao");
			String valor = JSFUtil.getParam("forcod");

			if (valor != null) {

				
				Long codigo = Long.parseLong(valor);
				FornecedorDao fdao = new FornecedorDao();
				fornecedor = fdao.buscarPorCodigo(codigo);

			} else {
				fornecedor = new Fornecedor();

			}

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void novo() { // Este metodo faz com que ao clicar no botao ADICIONAR
							// NOVO a tela seja limpa
		fornecedor = new Fornecedor();
	}

	public void salvar() throws Exception {

		try {
			FornecedorDao fdao = new FornecedorDao();

			fdao.salvar(fornecedor);
			fornecedor = new Fornecedor();
			// itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Fornecedor salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			FornecedorDao fdao = new FornecedorDao();
			fdao.excluir(fornecedor);

			JSFUtil.adicionarMensagemSucesso("Fornecedor excluido com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir um fornecedor que tenha um produto associado!");
			e.printStackTrace();
		}
	}

	public void editar() throws Exception {
		try {
			FornecedorDao fdao = new FornecedorDao();
			fdao.actualizar(fornecedor);

			JSFUtil.adicionarMensagemSucesso("Dados do fornecedor actualizados com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

}
