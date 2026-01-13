import java.util.ArrayList;
import java.util.List;

public class GerenciadorEstabelecimentos {
    private List<Estabelecimento> estabelecimentos;

    public GerenciadorEstabelecimentos() {
        this.estabelecimentos = new ArrayList<>();
    }

    public void adicionar(Estabelecimento e) {
        estabelecimentos.add(e);
    }

    public List<Estabelecimento> getTodos() {
        return estabelecimentos;
    }

    public Estabelecimento buscarPorNome(String nome) {
        for (Estabelecimento e : estabelecimentos) {
            if (e.getNome().equalsIgnoreCase(nome)) {
                return e;
            }
        }
        return null;
    }

    public void editar(String nomeOriginal, Estabelecimento novo) {
        for (int i = 0; i < estabelecimentos.size(); i++) {
            if (estabelecimentos.get(i).getNome().equalsIgnoreCase(nomeOriginal)) {
                estabelecimentos.set(i, novo);
                break;
            }
        }
    }
}