import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
public class Win extends JPanel{
    private Image img_bg = new ImageIcon(getClass().getResource("b.JPG")).getImage();
    private String Name = "Congraturation";
    private JButton Restart = new JButton("Restart"){
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(Color.black);
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g.setColor(Color.red);
            g.fillRoundRect(2, 2, (getWidth() - 4), (getHeight() - 4), 20, 20);
            super.paintComponent(g);
        }
    };
    private App app;
    private Win win;
    private Font font = new Font("Forte", Font.BOLD, 52);
    private Font fontbutton = new Font("Forte", Font.BOLD, 32);
    public Win(App main){
        app = main;
        this.win = this;
        Restart.setBorder(new EmptyBorder(10, 10, 10, 10));
        Restart.setFocusPainted(false);
        Restart.setContentAreaFilled(false); // ทำให้ปุ่มโปร่งใส
        Restart.setOpaque(false); // ทำให้ปุ่มโปร่งใส
        setLayout(null);
        Restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println("dwa");
                app.winstart(win);
            }
        });
        Restart.setFont(fontbutton);
        Restart.setForeground(Color.black);
        Restart.setBounds(680, 550, 220, 50);
        add(Restart);
    }
    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        g.drawImage(img_bg, 0, 0, getWidth(), getHeight(), this);
        g.setFont(font);
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
