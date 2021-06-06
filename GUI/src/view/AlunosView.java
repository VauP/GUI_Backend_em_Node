package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import dao.AlunosDAO;
import model.Dados;
import model.Fila;
import table.AlunoTableModel;
import table.PesquisaAlunos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlunosView extends JFrame {

	private JPanel contentPane;
	private JTextField tfCod;
	private JTextField tfNome;
	private JTextField tfRA;
	private JTextField tfFreq;
	private JTextField tfNota;
	private JTable tbDados;


	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public AlunosView() throws Exception {
		
		final Dados dadosAluno = new Dados();
//		AlunosDAO alunos = new AlunosDAO();
		final Fila fila = new Fila(100);
		final PesquisaAlunos bdController = new PesquisaAlunos();
		
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final JButton btExcluir = new JButton("Excluir");
		btExcluir.setEnabled(false);
		JLabel lblInsirirAluno = new JLabel("Gerenciador de dados 3000");
		lblInsirirAluno.setFont(new Font("Century Schoolbook L", Font.BOLD, 19));
		lblInsirirAluno.setBounds(33, 12, 319, 28);
		contentPane.add(lblInsirirAluno);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Century Schoolbook L", Font.BOLD, 16));
		lblNome.setBounds(33, 52, 117, 15);
		contentPane.add(lblNome);
		
		JLabel lblRa = new JLabel("RA");
		lblRa.setFont(new Font("Century Schoolbook L", Font.BOLD, 16));
		lblRa.setBounds(33, 82, 117, 15);
		contentPane.add(lblRa);
		
		JLabel lblCdigoDaDisciplina = new JLabel("Código da Disciplina");
		lblCdigoDaDisciplina.setFont(new Font("Century Schoolbook L", Font.BOLD, 16));
		lblCdigoDaDisciplina.setBounds(33, 109, 202, 15);
		contentPane.add(lblCdigoDaDisciplina);
		
		JLabel lblFrequncia = new JLabel("Frequência");
		lblFrequncia.setFont(new Font("Century Schoolbook L", Font.BOLD, 16));
		lblFrequncia.setBounds(33, 136, 117, 15);
		contentPane.add(lblFrequncia);
		
		JLabel lblNota = new JLabel("Nota");
		lblNota.setFont(new Font("Century Schoolbook L", Font.BOLD, 16));
		lblNota.setBounds(33, 163, 117, 15);
		contentPane.add(lblNota);
		
		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBounds(224, 51, 437, 19);
		contentPane.add(tfNome);
		
		tfRA = new JTextField();
		tfRA.setColumns(10);
		tfRA.setBounds(224, 81, 437, 19);
		contentPane.add(tfRA);
		
		tfCod = new JTextField();
		tfCod.setColumns(10);
		tfCod.setBounds(224, 108, 437, 19);
		contentPane.add(tfCod);
		
		tfFreq = new JTextField();
		tfFreq.setColumns(10);
		tfFreq.setBounds(224, 136, 437, 19);
		contentPane.add(tfFreq);
		
		tfNota = new JTextField();
		tfNota.setColumns(10);
		tfNota.setBounds(224, 162, 437, 19);
		contentPane.add(tfNota);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!tfNota.getText().isEmpty() || !tfNota.getText().isBlank()) {
					dadosAluno.setNota(Integer.parseInt(tfNota.getText()));
				}
				
				if(!tfRA.getText().isEmpty() || !tfRA.getText().isBlank()) {
					dadosAluno.setRA(Integer.parseInt(tfRA.getText()));
				}
				
				if(!tfCod.getText().isEmpty() || !tfCod.getText().isBlank()) {
					dadosAluno.setCod(Integer.parseInt(tfCod.getText()));
				}
				
				if(!tfFreq.getText().isEmpty() || !tfFreq.getText().isBlank()) {
					dadosAluno.setFreq(Integer.parseInt(tfFreq.getText()));
				}
				try {
					fila.guardeUmItem(dadosAluno);
					tfRA.setText("");
					tfFreq.setText("");
					tfCod.setText("");
					tfNota.setText("");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSalvar.setBounds(33, 218, 200, 30);
		contentPane.add(btnSalvar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 277, 628, 292);
		contentPane.add(scrollPane);
		
		tbDados = new JTable();
		tbDados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfRA.setText(tbDados.getValueAt(tbDados.getSelectedRow(), AlunoTableModel.COL_RA)
						.toString());
				tfCod.setText(tbDados
						.getValueAt(tbDados.getSelectedRow(), AlunoTableModel.COL_COD).toString());
				tfFreq.setText(tbDados.getValueAt(tbDados.getSelectedRow(), AlunoTableModel.COL_FREQ)
						.toString());
				tfNota.setText(tbDados.getValueAt(tbDados.getSelectedRow(), AlunoTableModel.COL_NOTA)
						.toString());
				btExcluir.setEnabled(true);
			}
		});
		tbDados.setSurrendersFocusOnKeystroke(true);
		tbDados.setFillsViewportHeight(true);
		tbDados.setCellSelectionEnabled(true);
		tbDados.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(tbDados);
		
		tbDados.setModel(new AlunoTableModel(new PesquisaAlunos().listarTodos()));
		
		JButton btnEnviarBd = new JButton("Enviar BD");
		btnEnviarBd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					bdController.enviarParaBD(fila);
					tfRA.setText("");
					tfFreq.setText("");
					tfCod.setText("");
					tfNota.setText("");
					tbDados.setModel(new AlunoTableModel(new PesquisaAlunos().listarTodos()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEnviarBd.setBounds(245, 218, 202, 30);
		contentPane.add(btnEnviarBd);
		
		
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int escolha = JOptionPane.showConfirmDialog(null, "Deseja excluir?", "Produto - Excluir",
						JOptionPane.YES_NO_OPTION);
				if (escolha == 0) {
					int cod = Integer.parseInt(tfCod.getText());
					int RA = Integer.parseInt(tfRA.getText());
					try {
						bdController.remover(cod, RA);
						tbDados.setModel(new AlunoTableModel(new PesquisaAlunos().listarTodos()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					tfRA.setText("");
					tfFreq.setText("");
					tfCod.setText("");
					tfNota.setText("");
					btExcluir.setEnabled(false);
				}	
			}
		});
		btExcluir.setBounds(461, 218, 200, 30);
		contentPane.add(btExcluir);
	}
}
