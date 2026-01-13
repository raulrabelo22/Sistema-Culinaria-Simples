public class ReceitaDoce extends Receita {

    public ReceitaDoce(String nome, int tempoDePreparo, int dificuldade, String modoDePreparo) {
        super(nome, tempoDePreparo, dificuldade, modoDePreparo);
    }

    @Override
    public String visualizarDetalhes() {
        return "Nome: " + getNome() + 
               "\nTempo de Preparo: " + getTempoPreparo() + " min " +
               "\nDificuldade: " + getDificuldade() + 
               "\nModo de Preparo: " + getModoDePreparo() +
               "\nCategoria: " + (this instanceof ReceitaDoce ? "Doce" : "Salgado");
    }

    @Override
    public String exibirCategoria() {
        return "Doce";
    }
}