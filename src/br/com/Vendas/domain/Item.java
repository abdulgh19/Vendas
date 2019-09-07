package br.com.Vendas.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "item")
@NamedQueries({
		@NamedQuery(name = "Item.listar", query = "SELECT item FROM Item item"),
		@NamedQuery(name = "Item.buscarPorCodigo", query = "SELECT item FROM Item item WHERE item.codigo = :codigo") })
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Long codigo;

	@NotNull(message="Informe a quantidade que deseja comprar")
	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;

	@Column(name = "valorParcial", nullable = false, scale = 2, precision = 7)
	private BigDecimal valorParcial;

	@NotNull(message="Seleccione o produto")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_produto", referencedColumnName = "codigo", nullable = false)
	private Produto produto;

	@NotNull(message="Indique a venda")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_venda", referencedColumnName = "codigo", nullable = false)
	private Venda venda;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	@Override
	public String toString() {
		return "Item [codigo=" + codigo + ", quantidade=" + quantidade + ", valorParcial=" + valorParcial
				+ ", produto=" + produto + ", venda=" + venda + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	

}
