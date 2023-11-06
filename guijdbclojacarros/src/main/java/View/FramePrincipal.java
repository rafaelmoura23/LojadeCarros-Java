package View;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class FramePrincipal extends JFrame {
    public FramePrincipal(){
        super("Loja de Carros");
        setDefaultCloseOperation(2);
        JTabbedPane abas = new JTabbedPane();
        abas.add("Carros", new CarrosPainel());
        abas.add("Clientes", new ClientesPainel());
        abas.add("Vendas", new VendasPainel());
    }

    public void run(){
        pack();
        setVisible(true);
    }
    
}
