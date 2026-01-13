import java.io.*;
import java.util.ArrayList;


public class GerenciadorArquivos {
    private static final String CAMINHO_ARQUIVO = "receitas.txt";
    private static int totalOperacoesSucesso = 0;


    public void salvar(ArrayList<Receita> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("receitas.txt"))) {
            
            for (Receita r : lista) {
                writer.write(r.getNome() + ";" + 
                             r.getTempoPreparo() + ";" + 
                             r.getDificuldade() + ";" + 
                             r.getModoDePreparo() + ";" + 
                             r.exibirCategoria().toUpperCase());
                writer.newLine();
            }
            
            System.out.println("Sincronização com o arquivo concluída.");
            
        } catch (IOException e) {
            System.err.println("Erro técnico ao acessar o arquivo: " + e.getMessage());
        }
    }


    public ArrayList<Receita> carregar() {
        ArrayList<Receita> listaCarregada = new ArrayList<>();
        File arquivo = new File(CAMINHO_ARQUIVO);


        if (!arquivo.exists()) {
            return listaCarregada;
        }


        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 5) {
                    String nome = partes[0];
                    int tempo = Integer.parseInt(partes[1]);
                    int dificuldade = Integer.parseInt(partes[2]);
                    String modo = partes[3];
                    String tipo = partes[4];


                    Receita r;
                    if (tipo.equals("DOCE")) {
                        r = new ReceitaDoce(nome, tempo, dificuldade, modo);
                    } else {
                        r = new ReceitaSalgada(nome, tempo, dificuldade, modo);
                    }
                    listaCarregada.add(r);
                }
            }
            if (!listaCarregada.isEmpty()) {
                totalOperacoesSucesso++;
            }
        }
        catch (FileNotFoundException e){
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        }
        catch (NumberFormatException e) {
            System.err.println("Erro de formato numérico no arquivo.");
        }
        catch (IOException e) {
            System.err.println("Erro de leitura do arquivo.");
        }
        
        return listaCarregada;
    }

    public static int getTotalOperacoesSucesso() {
        return totalOperacoesSucesso;
    }
}

