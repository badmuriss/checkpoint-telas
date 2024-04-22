package entities.produto;

import java.util.List;

import javax.swing.ImageIcon;

public class DatabaseProduto {

	private static List<Produto> baseDeDados = List.of(
			
			//imagens devem ser em 200X250px
			
			new Produto("Pitu 350ml", 5.0, new ImageIcon(Produto.class.getResource("../../images/produtos/pitu.png"))),
			new Produto("Jack Daniels 1L", 120.0, new ImageIcon(Produto.class.getResource("../../images/produtos/jack_daniels.png"))),
			new Produto("Absolut 1L", 80.0, new ImageIcon(Produto.class.getResource("../../images/produtos/absolut.png"))),
			new Produto("Campari 750ml", 50.0, new ImageIcon(Produto.class.getResource("../../images/produtos/campari.png"))),
			new Produto("Ballena 750ml", 130.0, new ImageIcon(Produto.class.getResource("../../images/produtos/ballena.png"))),
			new Produto("Paratudo 900ml", 12.50, new ImageIcon(Produto.class.getResource("../../images/produtos/paratudo.png"))),
			new Produto("Velho Barreiro 910ml", 12.99, new ImageIcon(Produto.class.getResource("../../images/produtos/velho_barreiro.png")))
			
			
			);
	
	
	public static Produto get(int index) {
		return baseDeDados.get(index);
	}
	
	public static int getSize(){
		return baseDeDados.size();
	}
}
