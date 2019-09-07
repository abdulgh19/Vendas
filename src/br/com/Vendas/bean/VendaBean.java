package br.com.Vendas.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.Vendas.dao.FuncionarioDao;
import br.com.Vendas.dao.ProdutoDao;
import br.com.Vendas.dao.VendaDao;
import br.com.Vendas.domain.Funcionario;
import br.com.Vendas.domain.Item;
import br.com.Vendas.domain.Produto;
import br.com.Vendas.domain.Venda;
import br.com.Vendas.util.JSFUtil;

@ManagedBean(name = "MBVenda")
@ViewScoped
public class VendaBean {

	private Produto Produto;
	private Venda vendaCadastro;
	private List<Item> itens;
	private List<Item> itensFiltrados;

	private List<Produto> produtos;
	private List<Produto> produtosFiltrados;

	public Venda getVendaCadastro() {
		if (vendaCadastro == null) {
			vendaCadastro = new Venda();
			vendaCadastro.setValorTotal(new BigDecimal(0));

		}
		return vendaCadastro;
	}

	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}

	public Produto getProduto() {
		return Produto;
	}

	public void setProduto(Produto produto) {
		Produto = produto;
	}

	public List<Item> getItens() {

		if (itens == null) {
			itens = new ArrayList<Item>();

		}
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public List<Item> getItensFiltrados() {

		return itensFiltrados;
	}

	public void setItensFiltrados(List<Item> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void setProdutosFiltrados(List<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}

	public void carregarProduto() {

		try {
			ProdutoDao pdao = new ProdutoDao();
			produtos = (ArrayList<Produto>) pdao.listar();

		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void adicionarItem(Produto produto) {
		int posicaoEncontrada = -1;

		for (int posicao = 0; posicao < itens.size() && posicaoEncontrada < 0; posicao++) {
			Item itemNaPosicao = itens.get(posicao);
			if (itemNaPosicao.getProduto().equals(produto)) {
				posicaoEncontrada = posicao;
			}

		}

		Item itemPorAdicionar = new Item();
		itemPorAdicionar.setProduto(produto);

		if (posicaoEncontrada < 0) {
			itemPorAdicionar.setQuantidade(1);
			itemPorAdicionar.setValorParcial(produto.getPreco());
			itens.add(itemPorAdicionar);
		} else {

			Item itemEncontrado = itens.get(posicaoEncontrada);
			itemPorAdicionar.setQuantidade(itemEncontrado.getQuantidade() + 1);
			itemPorAdicionar.setValorParcial(produto.getPreco().multiply(
					new BigDecimal(itemPorAdicionar.getQuantidade())));
			itens.set(posicaoEncontrada, itemPorAdicionar);

		}
		vendaCadastro.setValorTotal(vendaCadastro.getValorTotal().add(produto.getPreco()));
	}

	public void removerItem(Item item) {
		int posicaoEncontrada = -1;

		for (int posicao = 0; posicao < itens.size() && posicaoEncontrada < 0; posicao++) { // Encontra
																							// um
																							// produto
																							// que
																							// ja
																							// esteja
																							// nos
																							// item
																							// e
																							// coloca-o
																							// na
																							// posicao
																							// encontrada
			Item itemNaPosicao = itens.get(posicao);
			if (itemNaPosicao.getProduto().equals(item.getProduto())) {
				posicaoEncontrada = posicao;
			}

		}

		if (posicaoEncontrada > -1) { // Remove o item da posicao encontrada
			itens.remove(posicaoEncontrada);
		}
		vendaCadastro.setValorTotal(vendaCadastro.getValorTotal().subtract(item.getValorParcial()));

	}

	public void carregarDadosVenda() {
		vendaCadastro.setHorario(new Date());
		FuncionarioDao dao = new FuncionarioDao();
		Funcionario fun = dao.buscarPorCodigo(4L);
		vendaCadastro.setFuncionario(fun);
	}

	public void salvarVenda() throws RuntimeException {

		try {
			VendaDao vdao = new VendaDao();
			vdao.salvar(vendaCadastro);
			
			vendaCadastro = new Venda();
			vendaCadastro.setValorTotal(new BigDecimal("0.0"));
			itens = new ArrayList<Item>();
			JSFUtil.adicionarMensagemSucesso("Venda feita com sucesso!");

		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

}
