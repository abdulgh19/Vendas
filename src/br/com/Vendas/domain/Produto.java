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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "produto")
@NamedQueries({
		@NamedQuery(name = "Produto.listar", query = "SELECT produto FROM Produto produto"),
		@NamedQuery(name = "Produto.buscarPorCodigo", query = "SELECT produto FROM Produto produto WHERE produto.codigo = :codigo") })
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Long codigo;

	@NotEmpty(message="Informe  descricao do produto")
	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;

	@NotNull(message="Informe o preco do produto")
	@DecimalMin(value="0.00", message="O preco nao pode ser negativo")
	@Column(name = "preco", nullable = false, scale = 2, precision = 8)
	private BigDecimal preco;

	@NotNull(message="Informe a quantidade em Stock")
	@Min(value=0)
	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;

	@NotNull(message="Insira um fornecedor")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_fornecedor", referencedColumnName = "codigo", nullable = false)
	private Fornecedor fornecedor;
	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco + ", quantidade="
				+ quantidade + ", fornecedor=" + fornecedor + "]";
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	

}
