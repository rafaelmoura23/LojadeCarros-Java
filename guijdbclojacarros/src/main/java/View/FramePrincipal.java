package View;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FramePrincipal extends JFrame {

    ImageIcon imagem = new ImageIcon(getClass().getResource("walpaper.jpg"));

    public FramePrincipal() {
        super("Loja de Carros");
        setDefaultCloseOperation(2);

        // criação dos painéis
        CardLayout cardLayout = new CardLayout();
        JPanel cardsPanel = new JPanel(cardLayout);
        JPanel initialPanel = new JPanel(new BorderLayout());

        // criando os componentes do painel principal
        JLabel label = new JLabel(imagem);
        JButton button = new JButton("GO TO THE CAR STORE ->");
        button.setBackground(Color.black);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Monospaced", Font.PLAIN, 30));

        // ação para trocar os cards
         button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(cardsPanel); // Muda para o próximo card (JTabbedPane)
            }
        });

        //adicionando os elementos ao painelInitial
        initialPanel.add(label, BorderLayout.NORTH);
        initialPanel.add(button, BorderLayout.CENTER);

        // configuração do TabbedPane
        cardsPanel.add(initialPanel, "InitialPanel");
        JTabbedPane abas = new JTabbedPane();
        abas.add("Carros", new CarrosPainel());
        abas.add("Clientes", new ClientesPainel());
        abas.add("Vendas", new VendasPainel());
        cardsPanel.add(abas, "TabbedPanePanel");
        add(cardsPanel);

        setBounds(300, 250, 1400, 620);
    }

    public void run() {
        setVisible(true);
    }

}
