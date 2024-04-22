package entities.carrinho;

import java.util.Objects;

import entities.produto.Produto;

public class ItemCarrinho {

	public ItemCarrinho(Produto produto, Integer quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}

	private Produto produto;
	private Integer quantidade;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return produto.getPreco() * quantidade;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(produto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCarrinho other = (ItemCarrinho) obj;
		return Objects.equals(produto, other.produto);
	}

}
