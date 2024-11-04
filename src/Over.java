import java.awt.*;

import javax.swing.*;

public class Over extends JPanel{
    Image img_bg = new ImageIcon(getClass().getResource("b.JPG")).getImage();
    String Name = "Game Over!!!!";
    Font font = new Font("Forte", Font.BOLD, 52);
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
