public class Ingrediente {
   
private String nome;
    private String quantidade;


    public Ingrediente(String nome, String quantidade){
        this.nome = nome;
        this.quantidade = quantidade;
    }


    public String getNome(){
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getQuantidade(){
        return quantidade;
    }


    public void setQuantidade(String quantidade){
        this.quantidade = quantidade;
    }

    //método para validar se ingredientes possuem nome e quantidade (quantidade essa que nao foi utilizada) definidos antes de permitir o salvamento
    public boolean isValido() {
        return nome != null && !nome.isBlank() && quantidade !=null && !quantidade.isBlank();
    }

    @Override
    public String toString(){
        return "Nome: " + nome + " - Quantidade: " + quantidade;
    }
}
