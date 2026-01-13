public class Estabelecimento {
    private String nome;
    private String telefone; // Alterado para String para aceitar (88) 9...
    private String endereco;
    private String ingredientes;

    public Estabelecimento(String nome, String telefone, String endereco, String ingredientes){
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.ingredientes = ingredientes;
    }

    public String getNome(){ return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone(){ return telefone; }
    public void setTelefone(String telefone){ this.telefone = telefone; }

    public String getEndereco(){ return endereco; }
    public void setEndereco(String endereco){ this.endereco = endereco; }

    public String getIngredientes() { return ingredientes; }
    public void setIngredientes(String ingredientes) { this.ingredientes = ingredientes; }
    
    @Override
    public String toString(){
        return nome + " - Tel: " + telefone;
    }
}