import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class Menu extends JPanel{
    private Image img_bg = new ImageIcon(getClass().getResource("b.JPG")).getImage();
    private String Name = "Bazuka BOOM BOOM!!!!";
    private JButton Start = new JButton("Start"){
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(Color.black);
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g.setColor(Color.cyan);
            g.fillRoundRect(2, 2, (getWidth() - 4), (getHeight() - 4), 20, 20);
            super.paintComponent(g);
        }
    };
    private App app;
    private Font font = new Font("Forte", Font.BOLD, 52);
    private Font fontbutton = new Font("Forte", Font.BOLD, 32);
    public Menu(App main){
        app = main;
        Start.setBorder(new EmptyBorder(10, 10, 10, 10));
        Start.setFocusPainted(false);
        Start.setContentAreaFilled(false); // ทำให้ปุ่มโปร่งใส
        Start.setOpaque(false); // ทำให้ปุ่มโปร่งใส
        setLayout(null);
        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.startGame(1);
            }
        });
        Start.setFont(fontbutton);
        Start.setForeground(Color.black);
        Start.setBounds(680, 550, 220, 50);
        add(Start);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img_bg, 0, 0, getWidth(), getHeight(), this);
        g.setFont(font);
        g.setColor(Color.white);
        FontMetrics metrics = g.getFontMetrics(font);
        int x = (getWidth() - metrics.stringWidth(Name)) / 2;
        int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
        drawbroder(g, Name, x, y, font);
    }
    private void drawbroder(Graphics g, String Name, int x, int y, Font font){
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString(Name, x - 2, y - 2);
        g.drawString(Name, x + 2, y - 2);
        g.drawString(Name, x - 2, y + 2);
        g.drawString(Name, x + 2, y + 2);
        g.setColor(Color.white);
        g.drawString(Name, x, y);
    }
}