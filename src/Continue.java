import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
public class Continue extends JPanel{
    private Image img_bg = new ImageIcon(getClass().getResource("b.JPG")).getImage();
    private JButton Next = new JButton("Next"){
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(Color.black);
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g.setColor(Color.green);
            g.fillRoundRect(2, 2, (getWidth() - 4), (getHeight() - 4), 20, 20);
            super.paintComponent(g);
        }
    };
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
    private String Name = "Complete State ";
    private String Score = "Your Score is : ";
    private App app;
    private Continue con;
    private Font font = new Font("Forte", Font.BOLD, 52);
    private Font font2 = new Font("Copperplate Gothic Bold", Font.BOLD, 22);
    private Font fontbutton = new Font("Forte", Font.BOLD, 32);
    public Continue(App main, int round, int score){
        app = main;
        con = this;
        Name += (": " + round);
        if(score > 500){
            Score = "Awesome";
        }else if(score > 400){
            Score = "Good job";
        }else if(score > 300){
            Score = "So So";
        }else if(score > 200){
            Score = "Better next time";
        }else{
            Score = "Almost lose";
        }
        Restart.setBorder(new EmptyBorder(10, 10, 10, 10));
        Restart.setFocusPainted(false);
        Restart.setContentAreaFilled(false); // ทำให้ปุ่มโปร่งใส
        Restart.setOpaque(false); // ทำให้ปุ่มโปร่งใส
        Next.setBorder(new EmptyBorder(10, 10, 10, 10));
        Next.setFocusPainted(false);
        Next.setContentAreaFilled(false); // ทำให้ปุ่มโปร่งใส
        Next.setOpaque(false); // ทำให้ปุ่มโปร่งใส
        setLayout(null);
        Restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println("dwa");
                app.restart(con, true);
            }
        });
        Next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.restart(con, false);
            }
        });
        Restart.setFont(fontbutton);
        Next.setFont(fontbutton);
        Restart.setBounds(480, 550, 220, 50);
        Next.setBounds(880, 550, 220, 50);
        add(Restart);
        add(Next);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img_bg, 0, 0, getWidth(), getHeight(), this);
        FontMetrics metrics = g.getFontMetrics(font);
        int x = (getWidth() - metrics.stringWidth(Name)) / 2;
        int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
        drawbroder(g, Name, x, y - 20, font);
        metrics = g.getFontMetrics(font2);
        x = (getWidth() - metrics.stringWidth(Score)) / 2;
        drawbroder(g, Score, x, y + 20, font2);
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
