package br.com.Vendas.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.Vendas.dao.FornecedorDao;
import br.com.Vendas.dao.ProdutoDao;
import br.com.Vendas.domain.Fornecedor;
import br.com.Vendas.domain.Produto;
import br.com.Vendas.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {

	private Produto Produto;

	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFiltrados;
	private String acao;
	private Long codigo;
	private List<Fornecedor> fornecedores;
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

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

	public Produto getProduto() {

		return Produto;
	}

	public void setProduto(Produto Produto) {
		this.Produto = Produto;
	}

	public ArrayList<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}

	public ArrayList<Produto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	// @PostConstruct //A carregar a tela este metodo para listar ja eh chamdo1
	public void prepararPesquisa() {

		try {
			ProdutoDao pdao = new ProdutoDao();
			itens = (ArrayList<Produto>) pdao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void carregarCadastro() {

		try {
			acao = JSFUtil.getParam("produtoAcao");
			String valor = JSFUtil.getParam("prodcod");

			if (valor != null) {

				
				Long codigo = Long.parseLong(valor);
				ProdutoDao pdao = new ProdutoDao();
				Produto = pdao.buscarPorCodigo(codigo);

			} else {
				Produto = new Produto();

			}
			
			FornecedorDao fdao = new FornecedorDao();
			fornecedores = fdao.listar();
			

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void novo() { // Este metodo faz com que ao clicar no botao ADICIONAR
							// NOVO a tela seja limpa
		Produto = new Produto();
	}

	public void salvar() throws Exception {

		try {
			ProdutoDao pdao = new ProdutoDao();

			pdao.salvar(Produto);
			Produto = new Produto();
			// itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			ProdutoDao pdao = new ProdutoDao();
			pdao.excluir(Produto);

			JSFUtil.adicionarMensagemSucesso("Produto excluido com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir um Produto que tenha uma venda associada!");
			e.printStackTrace();
		}
	}

	public void editar() throws Exception {
		try {
			ProdutoDao pdao = new ProdutoDao();
			pdao.actualizar(Produto);

			JSFUtil.adicionarMensagemSucesso("Dados do Produto actualizados com sucesso!");

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

}
