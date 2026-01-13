import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class TelaEstabelecimento extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNome;
    private JTextField txtTelefone;
    private JTextField txtEndereco;
    private JTextArea textIngredientes;
    private JTextArea textAreaLista;
    private String nomeOriginalParaEditar;

    private GerenciadorEstabelecimentos gerenciador = new GerenciadorEstabelecimentos();
    private GerenciadorArquivosEstabelecimento arquivo = new GerenciadorArquivosEstabelecimento();

    public TelaEstabelecimento() {
        setTitle("Sistema de Culinária - Gerenciar Estabelecimentos");
        
        // Tenta carregar dados se o arquivo existir
        try {
            gerenciador.getTodos().addAll(arquivo.carregar());
        } catch (Exception e) {
            System.out.println("Aviso: Arquivo de estabelecimentos ainda não criado.");
        }

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 707, 472);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // --- CAMPOS DE TEXTO ---
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(26, 34, 60, 14);
        contentPane.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(76, 31, 198, 20);
        contentPane.add(txtNome);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(26, 65, 60, 14);
        contentPane.add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setBounds(76, 59, 110, 20);
        contentPane.add(txtTelefone);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(26, 94, 60, 14);
        contentPane.add(lblEndereco);

        txtEndereco = new JTextField();
        txtEndereco.setBounds(86, 91, 188, 20);
        contentPane.add(txtEndereco);

        JLabel lblIngredientes = new JLabel("Ingredientes Disponíveis:");
        lblIngredientes.setBounds(319, 11, 248, 14);
        contentPane.add(lblIngredientes);

        JScrollPane scrollIngred = new JScrollPane();
        scrollIngred.setBounds(319, 34, 325, 87);
        contentPane.add(scrollIngred);
        
        textIngredientes = new JTextArea();
        scrollIngred.setViewportView(textIngredientes);

        JLabel lblLista = new JLabel("Estabelecimentos:");
        lblLista.setBounds(319, 193, 143, 14);
        contentPane.add(lblLista);

        JScrollPane scrollLista = new JScrollPane();
        scrollLista.setBounds(319, 218, 325, 119);
        contentPane.add(scrollLista);
        
        textAreaLista = new JTextArea();
        scrollLista.setViewportView(textAreaLista);


        // CADASTRAR
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(e -> {
            String nome = txtNome.getText();
            String tel = txtTelefone.getText();
            String end = txtEndereco.getText();
            String ingred = textIngredientes.getText();

            if (nome.isEmpty() || tel.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome e Telefone são obrigatórios!");
                return;
            }

            Estabelecimento novo = new Estabelecimento(nome, tel, end, ingred);
            gerenciador.adicionar(novo);
            JOptionPane.showMessageDialog(null, "Adicionado à memória! Clique em Salvar para gravar no arquivo.");
            
            txtNome.setText(""); txtTelefone.setText(""); 
            txtEndereco.setText(""); textIngredientes.setText("");
        });
        btnCadastrar.setBounds(437, 132, 108, 23);
        contentPane.add(btnCadastrar);

        // SALVAR
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText();
            if (!nome.trim().isEmpty()) {
                Estabelecimento est = new Estabelecimento(nome, txtTelefone.getText(), 
                                                           txtEndereco.getText(), textIngredientes.getText());
                if (nomeOriginalParaEditar != null) {
                    gerenciador.editar(nomeOriginalParaEditar, est);
                    nomeOriginalParaEditar = null;
                } else {
                    if(gerenciador.buscarPorNome(nome) == null) {
                        gerenciador.adicionar(est);
                    }
                }
            }
            arquivo.salvar(gerenciador.getTodos());
            JOptionPane.showMessageDialog(null, "Arquivo atualizado com sucesso!");
        });
        btnSalvar.setBounds(555, 132, 89, 23);
        contentPane.add(btnSalvar);

        // LISTAR
        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(e -> {
            textAreaLista.setText(""); // Limpa a tela antes de listar
            for (Estabelecimento est : gerenciador.getTodos()) {
                textAreaLista.append("Local: " + est.getNome() + "\n");
                textAreaLista.append("Tel: " + est.getTelefone() + "\n");
                textAreaLista.append("End: " + est.getEndereco() + "\n");
                textAreaLista.append("Ingredientes: " + est.getIngredientes() + "\n");
                textAreaLista.append("----------------------------\n");
            }
        });
        btnListar.setBounds(542, 348, 102, 23);
        contentPane.add(btnListar);

        // EDITAR
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> {
            String busca = JOptionPane.showInputDialog("Nome do estabelecimento para editar:");
            Estabelecimento encontrado = gerenciador.buscarPorNome(busca);
            if (encontrado != null) {
                txtNome.setText(encontrado.getNome());
                txtTelefone.setText(encontrado.getTelefone());
                txtEndereco.setText(encontrado.getEndereco());
                textIngredientes.setText(encontrado.getIngredientes());
                nomeOriginalParaEditar = encontrado.getNome();
                JOptionPane.showMessageDialog(null, "Dados carregados. Edite e clique em Salvar.");
            } else {
                JOptionPane.showMessageDialog(null, "Estabelecimento não encontrado!");
            }
        });
        btnEditar.setBounds(26, 132, 89, 23);
        contentPane.add(btnEditar);

        // EXCLUIR
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            String busca = JOptionPane.showInputDialog("Nome do estabelecimento para excluir:");
            Estabelecimento encontrado = gerenciador.buscarPorNome(busca);
            if (encontrado != null) {
                int conf = JOptionPane.showConfirmDialog(null, "Excluir " + busca + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if(conf == JOptionPane.YES_OPTION) {
                    gerenciador.getTodos().remove(encontrado);
                    arquivo.salvar(gerenciador.getTodos());
                    JOptionPane.showMessageDialog(null, "Excluído!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não encontrado.");
            }
        });
        btnExcluir.setBounds(133, 132, 89, 23);
        contentPane.add(btnExcluir);

        // VOLTAR
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            new TelaPrincipal().setVisible(true);
            dispose();
        });
        btnVoltar.setBounds(26, 399, 102, 23);
        contentPane.add(btnVoltar);
    }
}