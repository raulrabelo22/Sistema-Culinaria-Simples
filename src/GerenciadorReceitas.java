import java.util.ArrayList;

public class GerenciadorReceitas {
    private ArrayList<Receita> listaReceitas;

    public GerenciadorReceitas() {
        this.listaReceitas = new ArrayList<>();
    }
    
    public ArrayList<Receita> getTodasReceitas() {
        return listaReceitas;
    }

    public void adicionarReceita(Receita r) {
        if (r != null && r.getNome() != null && !r.getNome().trim().isEmpty()) {
            listaReceitas.add(r);
        } else {
            System.out.println("Erro: Receita inválida ou sem nome.");
        }
    }
    
    // Raul Editar Receita
    public boolean editarReceita(String nomeAntigo, Receita receitaEditada) {
        for (int i = 0; i < listaReceitas.size(); i++) {
            if (listaReceitas.get(i).getNome().equalsIgnoreCase(nomeAntigo)) {
                listaReceitas.set(i, receitaEditada); 
                return true;
            }
        }
        return false;
    }

    public Receita buscarPorNome(String nomeBusca) {
        for (Receita r : listaReceitas) {
            if (r.getNome().equalsIgnoreCase(nomeBusca)) {
                return r;
            }
        }
        return null;
    }
    
    //codigo Mariana
    public boolean removerPorNome(String nome) {
        Receita r = buscarPorNome(nome);
        if (r != null) {
            listaReceitas.remove(r);
            return true;
        }
        return false;
    }

    public void imprimirTodas() {
        if (listaReceitas.isEmpty()) {
            System.out.println("Nenhuma receita cadastrada.");
        } else {
            for (Receita r : listaReceitas) {
                System.out.println(r.visualizarDetalhes());
                System.out.println("--------------------");
            }
        }
    }
}