import java.util.ArrayList;

public abstract class Receita {
   
    private String nome;
    private int tempoPreparo;
    private int dificuldade;
    private String modoDePreparo;
    private ArrayList<Ingrediente> ingredientes;
    private ArrayList<Utensilio> utensilios;
    private ArrayList<Avaliacao> avaliacoes;

    public Receita(String nome, int tempoPreparo, int dificuldade, String modoDePreparo) {
        this.nome = nome;
        this.tempoPreparo = tempoPreparo;
        this.dificuldade = dificuldade;
        this.modoDePreparo = modoDePreparo;
        this.ingredientes = new ArrayList<>();
        this.utensilios = new ArrayList<>();
        this.avaliacoes = new ArrayList<>();
    }

    public abstract String visualizarDetalhes();

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getTempoPreparo() { return tempoPreparo; }
    public int getDificuldade() { return dificuldade; }
    public String getModoDePreparo() { return modoDePreparo; }
    
    public String exibirCategoria() {
        return "Indefinida"; 
    }

    public void setTempoPreparo(int tempoPreparo) throws TempoInvalidoException {
        if(tempoPreparo > 0){
            this.tempoPreparo = tempoPreparo;
        } else {
            throw new TempoInvalidoException("Tempo de preparo invalido.");
        }
    }

    public ArrayList<Ingrediente> getIngredientes() { return ingredientes; }
    public void adicionarIngrediente(Ingrediente ingrediente) {
        if(ingrediente.isValido()) this.ingredientes.add(ingrediente);
        else System.out.println("ingrediente inválido");
    }

    public ArrayList<Utensilio> getUtensilios() { return utensilios; }
    public void adicionarUtensilio(Utensilio utensilio) {
         if(utensilio.isValido()) this.utensilios.add(utensilio);
         else System.out.println("utensílio inválido");
    }

    public void adicionarAvaliacao(Avaliacao a) {
        if (a != null) {
            this.avaliacoes.add(a);
        }
    }

    public void removerAvaliacao(Avaliacao a) {
        this.avaliacoes.remove(a);
    }

    public ArrayList<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public double calcularMediaAvaliacoes() {
        if (avaliacoes.isEmpty()) return 0.0;
        
        double soma = 0;
        for (Avaliacao a : avaliacoes) {
            soma += a.getNota();
        }
        return soma / avaliacoes.size();
    }

    public String listarAvaliacoes() {
        if (avaliacoes.isEmpty()) return "Nenhuma avaliação ainda.";
        
        StringBuilder sb = new StringBuilder();
        for (Avaliacao a : avaliacoes) {
            sb.append("Usuário: ").append(a.getUsuario())
              .append(", Nota: ").append(a.getNota())
              .append(", Comentário: ").append(a.getComentario())
              .append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Receita: " + nome + ", Tempo de Preparo: " + tempoPreparo + " minutos";
    }
}