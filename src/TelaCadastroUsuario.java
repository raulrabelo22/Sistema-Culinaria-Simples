import java.awt.EventQueue;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class TelaCadastroUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNomeCompleto;
	private JTextField textUsuario;
	private JPasswordField textSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroUsuario frame = new TelaCadastroUsuario();
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
	public TelaCadastroUsuario() {
		setTitle("Sistema de Culinária - Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 707, 472);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomeCompleto = new JLabel("Nome completo:");
		lblNomeCompleto.setBounds(20, 36, 108, 14);
		contentPane.add(lblNomeCompleto);
		
		JLabel lblUsuario = new JLabel("Usuário");
		lblUsuario.setBounds(327, 108, 55, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(329, 167, 47, 14);
		contentPane.add(lblSenha);
		
		textNomeCompleto = new JTextField();
		textNomeCompleto.setText("");
		textNomeCompleto.setBounds(126, 33, 198, 20);
		contentPane.add(textNomeCompleto);
		
		textUsuario = new JTextField();
		textUsuario.setText("");
		textUsuario.setBounds(250, 132, 198, 20);
		contentPane.add(textUsuario);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nome = textNomeCompleto.getText();
		        String user = textUsuario.getText();	  
		        String password = new String(textSenha.getPassword()); 

		        if(nome.trim().isEmpty() || user.trim().isEmpty() || password.trim().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Atenção: Todos os campos devem ser preenchidos!");
		        } else {
		            Usuario novo = new Usuario(nome, user, password);
		            GerenciadorUsuarios.adicionar(novo); // Agora funciona porque o método é static
		            JOptionPane.showMessageDialog(null, "Usuário " + user + " cadastrado com sucesso!");
		            dispose();
		        }
		    }
		});
		btnSalvar.setBounds(290, 222, 108, 23);
		contentPane.add(btnSalvar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(34, 386, 89, 23);
		contentPane.add(btnVoltar);
		
		textSenha = new JPasswordField();
		textSenha.setBounds(250, 189, 198, 20);
		contentPane.add(textSenha);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	}

}
