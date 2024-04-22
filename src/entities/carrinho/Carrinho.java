package entities.carrinho;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Carrinho {

	private List<ItemCarrinho> itensCarrinho = new ArrayList<>();
	
	public ItemCarrinho get(int index) {
		return itensCarrinho.get(index);
	}
	
	public int getSize() {
		return itensCarrinho.size();
	}
	
	public void adicionarItem(ItemCarrinho item) {
		boolean jaExisteNaLista = false;
		for(ItemCarrinho i : itensCarrinho) {
			if(item.equals(i)) {
				i.setQuantidade(i.getQuantidade() + item.getQuantidade());
				jaExisteNaLista = true;
			}
		}
		
		if(!jaExisteNaLista) {
			itensCarrinho.add(item);
		}
		
	}
	
	public void removerItem(ItemCarrinho item) {
		itensCarrinho.remove(item);
	}
	
	public void removerItem(String nomeProduto) {
		for(ItemCarrinho i : itensCarrinho) {
			if(i.getProduto().getNome() == nomeProduto) {
				itensCarrinho.remove(i);
				break;
			}
		}
	}
	
	public void incrementarItem(String nomeProduto) {
		for(ItemCarrinho i : itensCarrinho) {
			if(i.getProduto().getNome() == nomeProduto) {
				i.setQuantidade(i.getQuantidade()+1);
				break;
			}
		}
	}
	
	public void diminuirItem(String nomeProduto) {
		for(ItemCarrinho i : itensCarrinho) {
			if(i.getProduto().getNome() == nomeProduto) {
				if(i.getQuantidade() > 1) {
					i.setQuantidade(i.getQuantidade()-1);
				} else {
					itensCarrinho.remove(i);
				}
				break;
			}
		}
	}
	
	public double getPrecoTotal() {
		double total=0;
		
		for(ItemCarrinho i : itensCarrinho) {
			total+=i.getPreco();
		}
		
		return total;
	}
	
	public void clear() {
		itensCarrinho.clear();
	}
	
	public String[][] toTable(){
		
		Locale localeBR = new Locale("pt", "br");
		NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);
		
		String [][] table = new String[getSize()][3];
		
		for(int i = 0; i<getSize(); i++) {
			ItemCarrinho item = itensCarrinho.get(i);
			
			table[i][0] = item.getProduto().getNome();
			table[i][1] = item.getQuantidade().toString();
			table[i][2] = dinheiroBR.format(item.getPreco()).toString();
		}
		
		return table;
	}
}
