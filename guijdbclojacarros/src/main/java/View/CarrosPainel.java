package View;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CarrosPainel extends JPanel{
    // atributos - componentes
    // campo de texto - JTextField
    private JTextField inputPlaca;
    private JTextField inputMarca;
    private JTextField inputModelo;
    private JTextField inputAno;
    private JTextField inputCor;
    private JTextField inputValor;
    //campo escrito - JLabel
    private JLabel labelPlaca;
    private JLabel labelMarca;
    private JLabel labelModelo;
    private JLabel labelAno;
    private JLabel labelCor;
    private JLabel labelValor;
    //tabela
    private DefaultTableModel tableModel; //lógica
    private JTable table; //visual


    // construtor(GUI-JPanel)
    public CarrosPainel() {
        //construir a tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Placa");
        tableModel.addColumn("Marca");
        tableModel.addColumn("Modelo");
        tableModel.addColumn("Ano");
        tableModel.addColumn("Cor");
        tableModel.addColumn("Valor");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);







        // entrada de dados
        // botões de eventos
        // tabela de carros
    }

    
}
