package frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entities.carrinho.Carrinho;
import entities.carrinho.ItemCarrinho;
import entities.produto.DatabaseProduto;
import entities.produto.Produto;
import net.miginfocom.swing.MigLayout;

public class TelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Carrinho carrinhoSessao) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal(carrinhoSessao);
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
	public TelaPrincipal(Carrinho carrinhoSessao) {
		setTitle("CATALOGO EMPORIO ALQUIMISTA");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/images/icon_emporio.png")));
		setResizable(false);
		setBackground(new Color(128, 0, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		getContentPane().setLayout(new GridBagLayout());
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(120, 0, 180));
		contentPane.setLayout(new MigLayout("", "[64px][132px][78px][64px]", "[74px][250px][87px][40px][40px][49px]"));

		JPanel iconLogout = new JPanel();
		iconLogout.setOpaque(false);
		iconLogout.setBorder(null);
		iconLogout.add(new JLabel(new ImageIcon(getClass().getResource("../images/icon_logout.png"))));
		contentPane.add(iconLogout, "cell 0 0,growx,aligny top");

		iconLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame telaLogin = new TelaLogin();
				telaLogin.setVisible(true);

			}
		});

		JPanel iconCarrinho = new JPanel();
		iconCarrinho.setBorder(null);
		iconCarrinho.setOpaque(false);
		iconCarrinho.add(new JLabel(new ImageIcon(getClass().getResource("../images/icon_carrinho.png"))));
		contentPane.add(iconCarrinho, "cell 4 0,growx,aligny top");

		iconCarrinho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame telaCarrinho = new TelaCarrinho(carrinhoSessao);
				telaCarrinho.setVisible(true);

			}
		});

		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		contentPane.add(imagePanel, "cell 1 1 3 1,growx,aligny center");

		JLabel precoLabel = new JLabel();
		precoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		precoLabel.setForeground(new Color(255, 255, 255));
		precoLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(precoLabel, "cell 1 4 3 1,grow");

		JList<String> listaProdutos = new JList<String>();

		JScrollPane listaScrollPane = new JScrollPane();
		listaScrollPane.setViewportView(listaProdutos);
		listaProdutos.setForeground(new Color(255, 255, 255));
		listaProdutos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		listaProdutos.setBackground(new Color(120, 0, 180));
		listaProdutos.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;

			public int getSize() {
				return DatabaseProduto.getSize();
			}

			public String getElementAt(int index) {
				return DatabaseProduto.get(index).getNome();
			}
		});
		listaProdutos.setSelectedIndex(0);
		listaProdutos.setBounds(101, 353, 227, 57);
		contentPane.add(listaScrollPane, "cell 1 2 3 1,grow");

		JLabel qtdLabel = new JLabel();
		qtdLabel.setText("Quantidade:");
		qtdLabel.setForeground(Color.WHITE);
		qtdLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(qtdLabel, "cell 1 3,alignx right,growy");

		JSpinner contadorQtd = new JSpinner();

		contadorQtd.setModel(new SpinnerNumberModel(1, 1, 99, 1));
		contentPane.add(contadorQtd, "cell 3 3,alignx left,aligny center");

		// CLASSE QUE VERIFICA SE HÁ ALGUMA ATUALIZACAO NA SELECAO DE PRODUTO OU
		// QUANTIDADE
		class AtualizadorDeProduto implements ListSelectionListener, ChangeListener {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				atualizarProduto();
			}

			@Override
			public void stateChanged(ChangeEvent e) {
				atualizarProduto();
			}

			private void atualizarProduto() {
				Locale localeBR = new Locale("pt", "br");
				NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);
				Produto produtoSelecionado = DatabaseProduto.get(listaProdutos.getSelectedIndex());
				imagePanel.removeAll();
				imagePanel.add(new JLabel(produtoSelecionado.getFoto()));

				int quantidade = (Integer) contadorQtd.getValue();
				double preco = produtoSelecionado.getPreco() * quantidade;
				precoLabel.setText("Preço: " + dinheiroBR.format(preco));
			}
		}

		AtualizadorDeProduto atualizador = new AtualizadorDeProduto();
		atualizador.atualizarProduto();

		listaProdutos.addListSelectionListener(atualizador);
		contadorQtd.addChangeListener(atualizador);

		JButton botaoAdicionarCarrinho = new JButton("Adicionar ao carrinho");
		botaoAdicionarCarrinho.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(botaoAdicionarCarrinho, "cell 1 5 3 1,grow");

		botaoAdicionarCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Produto produtoSelecionado = DatabaseProduto.get(listaProdutos.getSelectedIndex());
				try {
					contadorQtd.commitEdit();
				} catch (java.text.ParseException exception) {
					exception.printStackTrace();
				}
				int quantidade = (Integer) contadorQtd.getValue();
				carrinhoSessao.adicionarItem(new ItemCarrinho(produtoSelecionado, quantidade));
				JOptionPane.showMessageDialog(botaoAdicionarCarrinho, quantidade + "x " + produtoSelecionado.getNome() + " adicionado ao carrinho.");
				
			}

		});

	}

}
