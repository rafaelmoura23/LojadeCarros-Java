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
        this.add(abas);
        setBounds(300, 250, 1400, 600);
    }

    public void run(){
        setVisible(true);
    }
    
}
