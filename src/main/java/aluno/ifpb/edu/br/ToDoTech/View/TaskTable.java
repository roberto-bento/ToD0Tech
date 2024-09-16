package aluno.ifpb.edu.br.ToDoTech.View;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TaskTable extends JTable {

    private TaskTableModel model;

    public TaskTable() throws IOException {
        model = new TaskTableModel();
        setModel(model);
        setRowHeight(30);  // Definir altura das linhas
        getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));  // Definir fonte do cabeçalho
        getTableHeader().setReorderingAllowed(false);
    }

    public void fixTable(JScrollPane scroll) {
        scroll.setViewportView(this);
    }

    // Método para adicionar uma nova tarefa
    public void addTask(String task) throws IOException {
        model.addTask(task);  // Chama o método de adicionar tarefa no modelo
    }

    // Método para editar uma tarefa
    public void editTask(int rowIndex, String newTask) throws IOException {
        model.editTask(rowIndex, newTask);
    }

    // Método para remover uma tarefa
    public void removeTask(int rowIndex) throws IOException {
        model.removeTask(rowIndex);
    }
}

