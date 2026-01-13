import java.io.*;
import java.util.*;

public class GerenciadorArquivosEstabelecimento {
    private final String arquivoNome = "estabelecimentos.txt";

    public void salvar(List<Estabelecimento> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoNome))) {
            for (Estabelecimento e : lista) {
                bw.write(e.getNome() + ";" + e.getTelefone() + ";" + e.getEndereco() + ";" + e.getIngredientes());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public List<Estabelecimento> carregar() {
        List<Estabelecimento> lista = new ArrayList<>();
        File file = new File(arquivoNome);
        
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    lista.add(new Estabelecimento(partes[0], partes[1], partes[2], partes[3]));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}