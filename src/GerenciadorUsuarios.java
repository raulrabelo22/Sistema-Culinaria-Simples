import java.util.*;

public class GerenciadorUsuarios {

    private static List<Usuario> listaUsuarios = new ArrayList<>();
    private static GerenciadorArquivosUsuario arquivoUsuarios = new GerenciadorArquivosUsuario();

    public static void carregar() {
        listaUsuarios = arquivoUsuarios.carregar(); 
    }

    public static boolean autenticar(String login, String senha) {
        for (Usuario u : listaUsuarios) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public static boolean adicionar(Usuario u) {
        for (Usuario user : listaUsuarios) {
            if (user.getLogin().equals(u.getLogin())) return false;
        }
        listaUsuarios.add(u);
        arquivoUsuarios.salvar(listaUsuarios);
        return true;
    }

    public static boolean remover(String login) {
        Iterator<Usuario> it = listaUsuarios.iterator();
        while (it.hasNext()) {
            Usuario u = it.next();
            if (u.getLogin().equals(login)) {
                it.remove();
                arquivoUsuarios.salvar(listaUsuarios);
                return true;
            }
        }
        return false;
    }
    
    public static Usuario buscarPorLogin(String login) {
    	for (Usuario u : listaUsuarios) { 
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }

    public static boolean alterarSenha(String login, String novaSenha) {
        for (Usuario u : listaUsuarios) {
            if (u.getLogin().equals(login)) {
                u.setSenha(novaSenha);
                arquivoUsuarios.salvar(listaUsuarios);
                return true;
            }
        }
        return false;
    }
}