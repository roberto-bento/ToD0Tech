package aluno.ifpb.edu.br.ToDoTech.View;

import aluno.ifpb.edu.br.ToDoTech.Controller.FileUtil;
import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static aluno.ifpb.edu.br.ToDoTech.Controller.FileUtil.caminho;

public class TaskTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Tarefa", "Concluída"};
    private final List<Object[]> data;  // Não usa generics, é uma lista de arrays de objetos

    public TaskTableModel() throws IOException {
        this.data = new ArrayList<>();
        carregarTarefas2();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] row = data.get(rowIndex);
        return row[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnIndex == 1 ? Boolean.class : String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;  // Permite a edição da coluna do checkbox
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Object[] row = data.get(rowIndex);
        row[columnIndex] = value;
        fireTableCellUpdated(rowIndex, columnIndex);
        FileUtil.salvar(data, "tarefas.bin");
    }

    // Método para adicionar uma nova tarefa
    public void addTask(String task) throws IOException {
        data.add(new Object[]{task, false});  // Adiciona uma tarefa com checkbox desmarcado
        fireTableRowsInserted(data.size() - 1, data.size() - 1);  // Notifica a tabela da adição
        FileUtil.salvar(data, "tarefas.bin");
    }

    // Método para editar uma tarefa existente
    public void editTask(int rowIndex, String newTask) throws IOException {
        data.get(rowIndex)[0] = newTask;  // Atualiza o nome da tarefa
        fireTableCellUpdated(rowIndex, 0);  // Notifica a tabela sobre a alteração
        FileUtil.salvar(data, "tarefas.bin");
    }

    // Método para remover uma tarefa
    public void removeTask(int rowIndex) throws IOException {
        data.remove(rowIndex);  // Remove a tarefa
        fireTableRowsDeleted(rowIndex, rowIndex);  // Notifica a tabela sobre a remoção
        FileUtil.salvar(data, "tarefas.bin");
    }

    public List<Object> carregarTarefas2() throws IOException {
        File arquivo = new File(caminho() + File.separator + "tarefas.bin");
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho() + File.separator +
                "tarefas.bin"))) {
            List<Object[]> tarefasCarregadas = (List<Object[]>) ois.readObject();
            data.addAll(tarefasCarregadas);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
