package aluno.ifpb.edu.br.ToDoTech.Models;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = -6819037833242347954L;
    private String nome;
    private String senha;

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

}
