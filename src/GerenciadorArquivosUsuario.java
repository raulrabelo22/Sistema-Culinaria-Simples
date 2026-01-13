import java.io.*;
import java.util.*;

public class GerenciadorArquivosUsuario {

    private final String arquivo = "usuarios.txt";

    public void salvar(List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (Usuario u : usuarios) {
                bw.write(u.getNome() + ";" + u.getLogin() + ";" + u.getSenha());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    public List<Usuario> carregar() {
        List<Usuario> lista = new ArrayList<>();
        File f = new File(arquivo);

        if (!f.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
                bw.write("Administrador;admin;123");
                bw.newLine();
            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo: " + e.getMessage());
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    lista.add(new Usuario(partes[0], partes[1], partes[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler usuários: " + e.getMessage());
        }

        return lista;
    }
}
