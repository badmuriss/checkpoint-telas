package frames;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entities.carrinho.Carrinho;
import entities.usuario.DatabaseUsuario;

public class TelaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setTitle("CATALOGO EMPORIO ALQUIMISTA");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLogin.class.getResource("/images/icon_emporio.png")));
		setResizable(false);
		setBackground(new Color(128, 0, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		getContentPane().setLayout(new GridBagLayout());
		contentPane = new JPanel();
		contentPane.setBorder(null);	
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(120,0,180));
		contentPane.setLayout(null);
	
		JPanel logoPanel = new JPanel();
		logoPanel.setBorder(null);
		logoPanel.setOpaque(false);
		logoPanel.setBounds(122, 79, 189, 231);
		logoPanel.add(new JLabel(new ImageIcon(getClass().getResource("../images/logo_emporio.png"))));
		contentPane.add(logoPanel);
		
		JTextField campoUsername = new JTextField(12);
		campoUsername.setBounds(122, 364, 189, 32);
		
		JPasswordField campoSenha = new JPasswordField(12);
		campoSenha.setBounds(122, 427, 189, 32);
		
		JButton botaoLogin = new JButton("Fazer Login");
		botaoLogin.setForeground(new Color(120, 0, 180));
		botaoLogin.setBackground(new Color(255, 255, 255));
		botaoLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		botaoLogin.setBounds(122, 483, 189, 44);
		
		botaoLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(DatabaseUsuario.autenticarUsuario(campoUsername.getText(), String.valueOf(campoSenha.getPassword()))) {
					dispose();
					Carrinho carrinhoSessao = new Carrinho();
					JFrame telaPrincipal = new TelaPrincipal(carrinhoSessao);
					telaPrincipal.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(botaoLogin, "Usuario ou senha invalidos");
				}
				
			}
		});
		
		
		contentPane.add(campoUsername);
		contentPane.add(campoSenha);
		contentPane.add(botaoLogin);
		
		JLabel labelUsername = new JLabel("Usuário");
		labelUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelUsername.setForeground(new Color(255, 255, 255));
		labelUsername.setBounds(122, 344, 68, 14);
		contentPane.add(labelUsername);
		
		JLabel labelSenha = new JLabel("Senha");
		labelSenha.setFont(new Font("Dialog", Font.PLAIN, 14));
		labelSenha.setForeground(Color.WHITE);
		labelSenha.setBounds(122, 407, 46, 14);
		contentPane.add(labelSenha);
		
		
	}
}

	
