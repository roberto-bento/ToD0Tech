package aluno.ifpb.edu.br.ToDoTech.Controller;

import aluno.ifpb.edu.br.ToDoTech.Models.Usuario;
import java.io.IOException;
import java.util.List;

public class LoginERegistroController {
    List<Usuario> usuarios;

    public void carregarUsuarios() throws IOException {
        usuarios = FileUtil.carregarUsuarios();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario criarUsuario(String nome, String senha) throws IOException {
        carregarUsuarios();
        Usuario usuario = new Usuario(nome, senha);
        getUsuarios().add(usuario);
        FileUtil.salvar(getUsuarios(), "usuarios.bin");
        return usuario;
    }
}
