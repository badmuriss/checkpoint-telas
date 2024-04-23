package entities.usuario;

public class Usuario {

	public Usuario(String nomeDeUsuario, String senha) {
		this.nomeDeUsuario = nomeDeUsuario;
		this.senha = senha;
	}

	private String nomeDeUsuario;
	private String senha;

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [nomeDeUsuario=" + nomeDeUsuario + ", senha=" + senha + "]";
	}

	
	
}
