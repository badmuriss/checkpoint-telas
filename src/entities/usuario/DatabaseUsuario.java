package entities.usuario;

import java.util.List;

public class DatabaseUsuario {

	private static List<Usuario> baseDeDados = List.of(
			
			new Usuario("murilo","12345"), 
			new Usuario("roberto","1234"), 
			new Usuario("marcos","123456")
			
	);
	
	public static boolean autenticarUsuario(String usuario, String senha) {
		for(Usuario u : baseDeDados) {
			if(usuario.equals(u.getNomeDeUsuario())) {
				if(senha.equals(u.getSenha())) {
					return true;
				}
				return false;
			}
		}
			
		return false;
	}

	
}
