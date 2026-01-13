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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class TelaPrincipal extends JFrame {
    
    // Instâncias dos Gerenciadores (Raul)
    private GerenciadorReceitas gerenciador = new GerenciadorReceitas(); 
    private GerenciadorArquivos arquivoProdutor = new GerenciadorArquivos();
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNome;
    private JTextField txtTempo;
    private JTextArea textAreaLista;
    private JTextArea textModoPreparo;
    private String nomeOriginalParaEditar;

     /* public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPrincipal frame = new TelaPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	DEIXANDO COMENTADO PARA QUE A TELA DE LOGIN SEJA O UNICO METODO ATIVO */
    
    public TelaPrincipal() {
    	setTitle("Sistema de Culinária - Sistema");
        // (Lógica da Mariana)
        gerenciador.getTodasReceitas().addAll(arquivoProdutor.carregar());
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 707, 472);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Componentes de Texto
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(28, 14, 60, 14);
        contentPane.add(lblNome);
        
        txtNome = new JTextField();
        txtNome.setBounds(78, 11, 198, 20);
        contentPane.add(txtNome);
        
        JLabel lblTempo = new JLabel("Tempo:");
        lblTempo.setBounds(28, 45, 46, 14);
        contentPane.add(lblTempo);
        
        txtTempo = new JTextField();
        txtTempo.setBounds(78, 39, 86, 20);
        contentPane.add(txtTempo);
        
        JLabel lblMinutos = new JLabel("(minutos)");
        lblMinutos.setBounds(174, 42, 60, 14);
        contentPane.add(lblMinutos);

        // ComboBoxes (Design (Raul) + Lógica (Sophia))
        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(28, 74, 60, 14);
        contentPane.add(lblCategoria);
        
        JComboBox cbCategoria = new JComboBox();
        cbCategoria.setModel(new DefaultComboBoxModel(new String[] {"Doce", "Salgado"}));
        cbCategoria.setBounds(98, 70, 76, 22);
        contentPane.add(cbCategoria);
        
        JLabel lblDificuldade = new JLabel("Dificuldade:");
        lblDificuldade.setBounds(184, 74, 92, 14);
        contentPane.add(lblDificuldade);
        
        JComboBox cbDificuldade = new JComboBox();
        cbDificuldade.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
        cbDificuldade.setBounds(276, 70, 46, 22);
        contentPane.add(cbDificuldade);

        // Modo de Preparo
        JLabel lblModo = new JLabel("Modo de Preparo e Ingredientes:");
        lblModo.setBounds(28, 108, 248, 14);
        contentPane.add(lblModo);
        
        JScrollPane scrollModo = new JScrollPane();
        scrollModo.setBounds(28, 132, 238, 130);
        contentPane.add(scrollModo);
        
        textModoPreparo = new JTextArea();
        scrollModo.setViewportView(textModoPreparo);

        // Área de Listagem
        JLabel lblLista = new JLabel("Receitas Cadastradas:");
        lblLista.setBounds(491, 11, 143, 14);
        contentPane.add(lblLista);
        
        JScrollPane scrollLista = new JScrollPane();
        scrollLista.setBounds(491, 31, 175, 227);
        contentPane.add(scrollLista);
        
        textAreaLista = new JTextArea();
        scrollLista.setViewportView(textAreaLista);

        // BOTÃO CADASTRAR (Sophia)
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                String cat = cbCategoria.getSelectedItem().toString();
                int dif = Integer.parseInt(cbDificuldade.getSelectedItem().toString());
                int tempo = Integer.parseInt(txtTempo.getText());
                String modo = textModoPreparo.getText();

                if (nome.isEmpty() || modo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                    return;
                }

                Receita nova;
                if (cat.equals("Doce")) {
                    nova = new ReceitaDoce(nome, tempo, dif, modo);
                } else {
                    nova = new ReceitaSalgada(nome, tempo, dif, modo);
                }

                gerenciador.adicionarReceita(nova);
                JOptionPane.showMessageDialog(null, "Cadastrada na memória!");
                
                // Limpar campos
                txtNome.setText("");
                txtTempo.setText("");
                textModoPreparo.setText("");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Tempo deve ser um número!");
            }
        });
        btnCadastrar.setBounds(28, 286, 108, 23);
        contentPane.add(btnCadastrar);

        // BOTÃO SALVAR
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtNome.getText().trim().isEmpty()) {
                        
                        String nome = txtNome.getText();
                        int tempo = Integer.parseInt(txtTempo.getText());
                        int dif = Integer.parseInt(cbDificuldade.getSelectedItem().toString());
                        String modo = textModoPreparo.getText();
                        String cat = cbCategoria.getSelectedItem().toString();

                        Receita nova;
                        if (cat.equals("Doce")) {
                            nova = new ReceitaDoce(nome, tempo, dif, modo);
                        } else {
                            nova = new ReceitaSalgada(nome, tempo, dif, modo);
                        }

                        if (nomeOriginalParaEditar != null) {
                            gerenciador.editarReceita(nomeOriginalParaEditar, nova);
                            nomeOriginalParaEditar = null;
                        } else {
                            if (gerenciador.buscarPorNome(nome) == null) {
                                gerenciador.adicionarReceita(nova);
                            }
                        }
                    }

                    arquivoProdutor.salvar(gerenciador.getTodasReceitas());
                    
                    JOptionPane.showMessageDialog(null, "Dados sincronizados com o arquivo com sucesso!");
                    
                    txtNome.setText("");
                    txtTempo.setText("");
                    textModoPreparo.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: Para salvar alterações, o campo 'Tempo' deve ser um número!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage());
                }
            }
        });
        btnSalvar.setBounds(177, 286, 89, 23);
        contentPane.add(btnSalvar);

        // BOTÃO LISTAR
        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(e -> {
            textAreaLista.setText("");
            for (Receita r : gerenciador.getTodasReceitas()) {
                textAreaLista.append(r.visualizarDetalhes() + "\n");
                textAreaLista.append("----------------------------\n");
            }
        });
        btnListar.setBounds(532, 269, 102, 23);
        contentPane.add(btnListar);

        // BOTÃO EXCLUIR
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog("Nome para excluir:");
            if (nome != null && !nome.isEmpty()) {
                Receita r = gerenciador.buscarPorNome(nome);
                if (r != null) {
                    gerenciador.getTodasReceitas().remove(r);
                    arquivoProdutor.salvar(gerenciador.getTodasReceitas());
                    JOptionPane.showMessageDialog(null, "Excluída com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Não encontrada.");
                }
            }
        });
        btnExcluir.setBounds(276, 134, 89, 23);
        contentPane.add(btnExcluir);

        // BOTÃO ESTATÍSTICAS (Mariana)
        JButton btnStats = new JButton("Estatísticas");
        btnStats.addActionListener(e -> {
            int total = gerenciador.getTodasReceitas().size();
            int doces = 0;
            for (Receita r : gerenciador.getTodasReceitas()) {
                if (r.exibirCategoria().equalsIgnoreCase("Doce")) doces++;
            }
            JOptionPane.showMessageDialog(null, "Total: " + total + "\nDoces: " + doces + "\nSalgadas: " + (total-doces));
        });
        btnStats.setBounds(28, 388, 123, 23);
        contentPane.add(btnStats);
        
        JLabel lblNomeParaExcluir = new JLabel("Clique aqui para excluir receitas:");
        lblNomeParaExcluir.setBounds(274, 108, 238, 14);
        contentPane.add(lblNomeParaExcluir);
        
        // botao de editar
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeBusca = JOptionPane.showInputDialog("Digite o nome exato da receita que deseja editar:");
                
                if (nomeBusca == null || nomeBusca.trim().isEmpty()) return;

                Receita encontrada = gerenciador.buscarPorNome(nomeBusca);

                if (encontrada != null) {
                    txtNome.setText(encontrada.getNome());
                    txtTempo.setText(String.valueOf(encontrada.getTempoPreparo()));
                    textModoPreparo.setText(encontrada.getModoDePreparo());
                    cbCategoria.setSelectedItem(encontrada.exibirCategoria());
                    cbDificuldade.setSelectedItem(String.valueOf(encontrada.getDificuldade()));

                    nomeOriginalParaEditar = encontrada.getNome();

                    JOptionPane.showMessageDialog(null, "Dados carregados! Altere os campos e clique em SALVAR.");
                } else {
                    JOptionPane.showMessageDialog(null, "Receita não encontrada.");
                }
            }
        });
        btnEditar.setBounds(276, 203, 89, 23);
        contentPane.add(btnEditar);
        
        JLabel lblCliqueAquiPara = new JLabel("Clique aqui para editar receitas:");
        lblCliqueAquiPara.setBounds(276, 178, 238, 14);
        contentPane.add(lblCliqueAquiPara);
        
     // BOTÃO VOLTAR (SAIR)
        JButton btnVoltar = new JButton("Sair");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TelaLogin().setVisible(true);
                dispose();
            }
        });
        btnVoltar.setBounds(532, 388, 102, 23);
        contentPane.add(btnVoltar);
        
        JButton btnMinhaConta = new JButton("Minha Conta");
        btnMinhaConta.addActionListener(e -> {
            String[] opcoes = {"Trocar Senha", "Excluir Minha Conta", "Cancelar"};
            int escolha = JOptionPane.showOptionDialog(null, "O que deseja fazer com sua conta?", 
                    "Gerenciar Conta", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                    null, opcoes, opcoes[0]);

            if (escolha == 0) { // Trocar Senha
                String login = JOptionPane.showInputDialog("Confirme seu Usuário:");
                String novaSenha = JOptionPane.showInputDialog("Digite a nova senha:");
                boolean sucesso1 = GerenciadorUsuarios.alterarSenha(login, novaSenha);

                if (sucesso1) {
                    JOptionPane.showMessageDialog(null, "Conta alterada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                }

                
            } else if (escolha == 1) { 
                String loginExcluir = JOptionPane.showInputDialog("Digite seu Usuário para confirmar a exclusão:");
                
                if (loginExcluir != null && !loginExcluir.trim().isEmpty()) {
                    int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza?", "Atenção", JOptionPane.YES_NO_OPTION);
                    
                    if (confirmar == JOptionPane.YES_OPTION) {
                        if (GerenciadorUsuarios.remover(loginExcluir)) {
                            JOptionPane.showMessageDialog(null, "Conta excluída!");
                            new TelaLogin().setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                        }
                    }
                }
            }
        });
        btnMinhaConta.setBounds(276, 388, 132, 23);
        contentPane.add(btnMinhaConta);
        
        JButton btnEstabelecimentos = new JButton("Estabelecimentos");
        btnEstabelecimentos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaEstabelecimento telaEst = new TelaEstabelecimento();
                
                telaEst.setVisible(true);
                
                dispose();
            }
        });
        btnEstabelecimentos.setBounds(514, 303, 137, 23);
        contentPane.add(btnEstabelecimentos);
    }
}