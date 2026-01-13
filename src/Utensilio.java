public class Utensilio {
    
    private String nome;

    public Utensilio(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    
    public boolean isValido() {
        return nome != null && !nome.isBlank();
    }

    @Override
    public String toString(){
        return "Utensílio: " + nome;
    }

}
