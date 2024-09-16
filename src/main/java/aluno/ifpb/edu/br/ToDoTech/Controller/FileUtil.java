package aluno.ifpb.edu.br.ToDoTech.Controller;

import aluno.ifpb.edu.br.ToDoTech.Models.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static void salvar(Object objeto, String nomeDoArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho() + File.separator +
                nomeDoArquivo))) {
            oos.writeObject(objeto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File caminho() throws IOException {
        File diretorio = new File("bins");
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        return diretorio;
    }

    public static List<String> carregarTarefas() throws IOException {
        File arquivo = new File(caminho() + File.separator + "tarefas.bin");
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho() + File.separator +
                "tarefas.bin"))) {
            return (List<String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Usuario> carregarUsuarios() throws IOException {
        File arquivo = new File(caminho() + File.separator + "usuarios.bin");
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho() + File.separator +
                "usuarios.bin"))) {
            return (List<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
