package entities.usuario;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUsuario {

	private static List<Usuario> baseDeDados = new ArrayList<>() {
		
		private static final long serialVersionUID = 1L;

	{
		add(new Usuario("murilo","12345"));
		add(new Usuario("roberto","1234"));
	}};
			
	
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

	public static boolean usuarioExiste(String usuario) {
		for(Usuario u : baseDeDados) {
			if(usuario.equals(u.getNomeDeUsuario())) {
				System.out.println(usuario.toString());
				return true;
				}
			}
			
		return false;
	}
	
	public static boolean cadastrarUsuario(String usuario, String senha) {
		if(usuarioExiste(usuario)) {
			return false;
		} else {
			baseDeDados.add(new Usuario(usuario, senha));
			return true;
		}
	}
}
