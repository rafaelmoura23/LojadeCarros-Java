package View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.events.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import Model.Carros;
import javafx.event.ActionEvent;
import Controller.CarrosControl;
import Controller.CarrosDAO;

import java.awt.*;
import java.awt.event.*;

public class CarrosPainel extends JPanel {
    // atributos - componentes
    // campo de texto - JTextField
    private JTextField inputPlaca;
    private JTextField inputMarca;
    private JTextField inputModelo;
    private JTextField inputAno;
    private JTextField inputValor;
    // campo escrito - JLabel
    private JLabel labelPlaca;
    private JLabel labelMarca;
    private JLabel labelModelo;
    private JLabel labelAno;
    private JLabel labelValor;
    private DefaultTableModel tableModel; // lógica
    private JTable table; // visual
    private List<Carros> carros;
    private int linhaSelecionada = -1;
    private JButton cadastrarButton, atualizarButton, apagarButton, editarButton;

    // construtor(GUI-JPanel)
    public CarrosPainel() {
        super();
        JPanel painel1 = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel();
        JPanel buttons = new JPanel();

        // construir a tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Placa");
        tableModel.addColumn("Marca");
        tableModel.addColumn("Modelo");
        tableModel.addColumn("Ano");
        tableModel.addColumn("Valor");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setViewportView(table);

        // criar os componentes
        inputPlaca = new JTextField(10);
        inputMarca = new JTextField(10);
        inputModelo = new JTextField(10);
        inputAno = new JTextField(10);
        inputValor = new JTextField(10);

        // criar os componentes - labels
        labelPlaca = new JLabel("Placa");
        labelMarca = new JLabel("Marca");
        labelModelo = new JLabel("Modelo");
        labelAno = new JLabel("Ano");
        labelValor = new JLabel("Valor");

        // botões
        cadastrarButton = new JButton("Cadastrar");
        atualizarButton = new JButton("Atualizar");
        apagarButton = new JButton("Apagar");
        editarButton = new JButton("Editar");

        // adicionar os componentes
        inputPanel.add(labelPlaca);
        inputPanel.add(inputPlaca);
        inputPanel.add(labelMarca);
        inputPanel.add(inputMarca);
        inputPanel.add(labelModelo);
        inputPanel.add(inputModelo);
        inputPanel.add(labelAno);
        inputPanel.add(inputAno);
        inputPanel.add(labelValor);
        inputPanel.add(inputValor);

        buttons.add(cadastrarButton);
        buttons.add(editarButton);
        buttons.add(atualizarButton);
        buttons.add(apagarButton);

        this.add(painel1);
        painel1.add(scrollPane, BorderLayout.CENTER);
        painel1.add(inputPanel, BorderLayout.NORTH);
        painel1.add(buttons, BorderLayout.SOUTH);

        // cadastrar carros
        HandlerAddCarro eventAddCarro = new HandlerAddCarro();
        cadastrarButton.addActionListener(eventAddCarro);

    }

    CarrosControl operacoes = new CarrosControl(carros, tableModel, table);

    public class HandlerAddCarro implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            operacoes.cadastrar(inputMarca.getText(), inputModelo.getText(), inputAno.getText(),
inputPlaca.getText(), inputValor.getText());
            // Limpa os campos de entrada após a operação de cadastro
            inputMarca.setText("");
            inputModelo.setText("");
            inputAno.setText("");
            inputPlaca.setText("");
            inputValor.setText("");
        }

    }
}
