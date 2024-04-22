package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import entities.carrinho.Carrinho;
import net.miginfocom.swing.MigLayout;

public class TelaCarrinho extends JFrame {

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
					TelaCarrinho frame = new TelaCarrinho(carrinhoSessao);
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
	public TelaCarrinho(Carrinho carrinhoSessao) {
		setTitle("CATALOGO EMPORIO ALQUIMISTA");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCarrinho.class.getResource("/images/icon_emporio.png")));
		setResizable(false);
		setBackground(new Color(128, 0, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		getContentPane().setLayout(new GridBagLayout());
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(120, 0, 180));
		contentPane.setLayout(new MigLayout("", "[70px][302px][70px]", "[90px][280px][120px][40px][40px]"));

		JPanel iconVoltar = new JPanel();
		iconVoltar.setOpaque(false);
		iconVoltar.setBorder(null);
		iconVoltar.add(new JLabel(new ImageIcon(getClass().getResource("../images/icon_voltar.png"))));
		contentPane.add(iconVoltar, "cell 0 0,alignx right,aligny top");

		iconVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame telaPrincipal = new TelaPrincipal(carrinhoSessao);
				telaPrincipal.setVisible(true);

			}
		});

		class NoBorderColumnModel extends DefaultTableColumnModel {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void setColumnMargin(int newMargin) {
				// Always set ColumnMargin to zero.
				// Because after the column data binding its internally set one as ColumnMargin.
				// That course to paint white color grid.
				// To stop we override the setColumnMargin and pass zero to ColumnMargin.
				super.setColumnMargin(0);
			}
		}

		JTable tabelaCarrinho = new JTable();
		tabelaCarrinho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaCarrinho.setForeground(new Color(255, 255, 255));
		tabelaCarrinho.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabelaCarrinho.setBorder(BorderFactory.createEmptyBorder());
		tabelaCarrinho.setRowMargin(0);
		tabelaCarrinho.setColumnModel(new NoBorderColumnModel());
		tabelaCarrinho.setShowVerticalLines(false);
		tabelaCarrinho.setShowHorizontalLines(false);
		tabelaCarrinho.setShowGrid(false);
		tabelaCarrinho.setFillsViewportHeight(true);
		tabelaCarrinho.setBackground(new Color(120, 0, 180));
		tabelaCarrinho.setDefaultEditor(getClass(), null);
		tabelaCarrinho.setRowHeight(50);

		JTableHeader header = tabelaCarrinho.getTableHeader();
		header.setOpaque(false);
		header.setBackground(new Color(120, 0, 180));
		header.setForeground(Color.WHITE);

		JScrollPane carrinhoScrollPane = new JScrollPane(tabelaCarrinho);
		carrinhoScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		carrinhoScrollPane.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(carrinhoScrollPane, "cell 1 1,grow");

		JPanel containerIcons = new JPanel();
		containerIcons.setOpaque(false);
		contentPane.add(containerIcons, "cell 1 2,alignx center,aligny top");
		containerIcons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel iconIncrementar = new JPanel();
		iconIncrementar.setAlignmentX(Component.LEFT_ALIGNMENT);
		iconIncrementar.setOpaque(false);
		iconIncrementar.setBorder(null);
		iconIncrementar.add(new JLabel(new ImageIcon(getClass().getResource("../images/icon_incrementar.png"))));
		containerIcons.add(iconIncrementar);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(12);
		panel.setOpaque(false);
		containerIcons.add(panel);

		JPanel iconDiminuir = new JPanel();
		iconDiminuir.setOpaque(false);
		iconDiminuir.setBorder(null);
		iconDiminuir.add(new JLabel(new ImageIcon(getClass().getResource("../images/icon_diminuir.png"))));
		containerIcons.add(iconDiminuir);

		JPanel panel1 = new JPanel();
		FlowLayout fl_panel1 = (FlowLayout) panel1.getLayout();
		fl_panel1.setHgap(12);
		panel1.setOpaque(false);
		containerIcons.add(panel1);

		JPanel iconDeletar = new JPanel();
		iconDeletar.setOpaque(false);
		iconDeletar.setBorder(null);
		iconDeletar.add(new JLabel(new ImageIcon(getClass().getResource("../images/icon_deletar.png"))));
		containerIcons.add(iconDeletar);

		JLabel labelPreco = new JLabel();
		labelPreco.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelPreco.setForeground(new Color(255, 255, 255));

		contentPane.add(labelPreco, "cell 1 3,alignx center,aligny top");

		atualizarCarrinho(tabelaCarrinho, carrinhoSessao, labelPreco);

		JButton botaoFinalizar = new JButton("        Finalizar pedido        ");
		botaoFinalizar.setSize(120, 80);
		botaoFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(botaoFinalizar, "cell 1 4,alignx center,growy");

		iconIncrementar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tabelaCarrinho.getSelectedRow();
				String nomeProduto = (String) tabelaCarrinho.getValueAt(row, 0);
				carrinhoSessao.incrementarItem(nomeProduto);
				atualizarCarrinho(tabelaCarrinho, carrinhoSessao, labelPreco);
				tabelaCarrinho.setRowSelectionInterval(row, row);
			}
		});

		iconDiminuir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tabelaCarrinho.getSelectedRow();
				String nomeProduto = (String) tabelaCarrinho.getValueAt(row, 0);
				carrinhoSessao.diminuirItem(nomeProduto);
				atualizarCarrinho(tabelaCarrinho, carrinhoSessao, labelPreco);
				if (carrinhoSessao.getSize() > 0) {
					tabelaCarrinho.setRowSelectionInterval(row, row);
				}
			}
		});

		iconDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tabelaCarrinho.getSelectedRow();
				String nomeProduto = (String) tabelaCarrinho.getValueAt(row, 0);
				carrinhoSessao.removerItem(nomeProduto);
				atualizarCarrinho(tabelaCarrinho, carrinhoSessao, labelPreco);
			}
		});

		botaoFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (carrinhoSessao.getSize() > 0) {
					int numeroPedido = (int) (Math.random() * 100000000);
					JOptionPane.showMessageDialog(botaoFinalizar, "Pedido n° " + numeroPedido
							+ " confirmado! Em até 2 dias úteis\nvocê receberá um e-mail confirmando a disponibilidade\npara a retirada dos produtos.");
					carrinhoSessao.clear();
					atualizarCarrinho(tabelaCarrinho, carrinhoSessao, labelPreco);
				} else {
					JOptionPane.showMessageDialog(botaoFinalizar, "Seu carrinho está vazio!");
				}

			}
		});

	}

	public static void atualizarCarrinho(JTable tabelaCarrinho, Carrinho carrinhoSessao, JLabel labelPreco) {
		Locale localeBR = new Locale("pt", "br");
		NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);
		tabelaCarrinho.setModel(
				new DefaultTableModel(carrinhoSessao.toTable(), new String[] { "Item", "Qtd", "Pre\u00E7o" }));
		double precoTotal = carrinhoSessao.getPrecoTotal();
		labelPreco.setText("Total: " + dinheiroBR.format(precoTotal));

		TableColumn columnNome = tabelaCarrinho.getColumnModel().getColumn(0);
		TableColumn columnQtd = tabelaCarrinho.getColumnModel().getColumn(1);
		TableColumn columnPreco = tabelaCarrinho.getColumnModel().getColumn(2);
		columnNome.setPreferredWidth(100);
		columnQtd.setPreferredWidth(20);
		columnPreco.setPreferredWidth(50);

	}

}
