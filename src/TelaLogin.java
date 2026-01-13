import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GerenciadorUsuarios.carregar();
		
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
		setBounds(100, 100, 707, 472);
		setLocationRelativeTo(null);
		setTitle("Sistema de Culinária - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usuário");
		lblUsurio.setBounds(327, 108, 55, 14);
		contentPane.add(lblUsurio);
		
		textUsuario = new JTextField();
		textUsuario.setText("");
		textUsuario.setBounds(250, 132, 198, 20);
		contentPane.add(textUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(329, 167, 47, 14);
		contentPane.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(250, 189, 198, 20);
		contentPane.add(txtSenha);
		
		// BOTÃO CADASTRAR NOVO USUARIO
		JButton btnCadastrar = new JButton("Cadastrar Novo Usuário");
		btnCadastrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        TelaCadastroUsuario telaCad = new TelaCadastroUsuario();
		        telaCad.setVisible(true); 
		    }
		});
		btnCadastrar.setBounds(23, 373, 183, 23);
		contentPane.add(btnCadastrar);
		
		// BOTÃO ENTRAR
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String user = textUsuario.getText();
			    String senha = new String(txtSenha.getPassword());
			    
			    if (GerenciadorUsuarios.autenticar(user, senha)) {
			        new TelaPrincipal().setVisible(true);
			        dispose(); 
			    } else {
			        JOptionPane.showMessageDialog(null, "Usuário ou Senha Inválidos!");
			    }
			}
		});
		btnEntrar.setBounds(290, 222, 108, 23);
		contentPane.add(btnEntrar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSair.setBounds(530, 373, 108, 23);
		contentPane.add(btnSair);
		
		JButton btnEsqueceuASenha = new JButton("Esqueceu a senha?");
		btnEsqueceuASenha.addActionListener(e -> {
		    String loginBusca = JOptionPane.showInputDialog("Para recuperar, digite seu Usuário:");
		    
		    if (loginBusca != null && !loginBusca.isEmpty()) {
		       Usuario u = GerenciadorUsuarios.buscarPorLogin(loginBusca);
		          if (u != null) {
		              JOptionPane.showMessageDialog(
		                  null,
		                  "Usuário encontrado:\nSenha: " + u.getSenha()
		              );
		          } else {
		              JOptionPane.showMessageDialog(
		                  null,
		                  "Usuário não encontrado."
		              );
		          }
		      }
		    });
		
		btnEsqueceuASenha.setBounds(250, 373, 198, 23);
		contentPane.add(btnEsqueceuASenha);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		

	}
}
