package entities.produto;

import java.util.Objects;

import javax.swing.ImageIcon;

public class Produto {

	public Produto(String nome, Double preco, ImageIcon foto) {

		this.nome = nome;
		this.preco = preco;
		this.foto = foto;
	}

	private String nome;
	private Double preco;
	private ImageIcon foto;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public ImageIcon getFoto() {
		return foto;
	}

	public void setFoto(ImageIcon foto) {
		this.foto = foto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
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
		return Objects.equals(nome, other.nome);
	}

}
