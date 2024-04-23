
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

import entities.usuario.DatabaseUsuario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastro extends JFrame {

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
					TelaCadastro frame = new TelaCadastro();
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
	public TelaCadastro() {
		setTitle("CATALOGO EMPORIO ALQUIMISTA");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastro.class.getResource("/images/icon_emporio.png")));
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
	
		JPanel iconVoltar = new JPanel();
		iconVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				TelaLogin telaLogin = new TelaLogin();
				telaLogin.setVisible(true);
			}
		});
		
		iconVoltar.setBorder(null);
		iconVoltar.setOpaque(false);
		iconVoltar.setBounds(10, 0, 64, 64);
		iconVoltar.add(new JLabel(new ImageIcon(getClass().getResource("../images/icon_voltar.png"))));
		contentPane.add(iconVoltar);
		
		
		
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
		
		JButton botaoCadastro = new JButton("Criar conta");
		botaoCadastro.setForeground(new Color(120, 0, 180));
		botaoCadastro.setBackground(new Color(255, 255, 255));
		botaoCadastro.setFont(new Font("Tahoma", Font.BOLD, 15));
		botaoCadastro.setBounds(122, 483, 189, 44);
		
		botaoCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(DatabaseUsuario.cadastrarUsuario(campoUsername.getText(), String.valueOf(campoSenha.getPassword()))) {
					campoUsername.setText("");
					campoSenha.setText("");
					JOptionPane.showMessageDialog(botaoCadastro, "Cadastro realizado!");
				} else {
					JOptionPane.showMessageDialog(botaoCadastro, "Usuario ja existente!");
				}
				
			}
		});
		
		
		contentPane.add(campoUsername);
		contentPane.add(campoSenha);
		contentPane.add(botaoCadastro);
		
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

	
