import javax.swing.*;
// import java.awt.*;
import java.awt.event.*;
public class App extends JFrame{
    Menu m = new Menu(this);
    int lap;
    public App(){
        add(m);
    }
    public void startwin(Game game){
        Win win = new Win(this);
        remove(game);
        game = null;
        add(win);
        revalidate();
        repaint();
    }
    public void winstart(Win win){
        remove(win);
        win = null;
        add(m);
        revalidate();
        repaint();
    }
    public void Over(Game game){
        Over o = new Over();
        remove(game);
        game = null;
        add(o);
        revalidate();
        repaint();
        Timer wait = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(o);
                add(m);
                revalidate();
                repaint();
            }
        });
        wait.setRepeats(false);
        wait.start();
    }
    public void startGame(int round){
        lap = round;
        Game g = new Game(lap, this);
        g.setFocusable(true);
        remove(m);
        add(g);
        g.requestFocusInWindow();
        revalidate();
        repaint();
    }
    public void restart(Continue con, boolean check){
        if(check){
            lap = 1;
            remove(con);
            con = null;
            add(m);
            revalidate();
            repaint();
        }else{
            lap += 1;
            Game g = new Game(lap, this);
            g.setFocusable(true);
            remove(con);
            con = null;
            add(g);
            g.requestFocusInWindow();
            revalidate();
            repaint();
        }
        
    }
    public void startCon(Game game, int round, int score){
        Continue con = new Continue(this, round, score);
        Win win = new Win(this);
        remove(game);
        game = null;
        if(round == 3){
            add(win);
            revalidate();
            repaint();
        }else{
            add(con);
            revalidate();
            repaint();
        }
    }
    public static void main(String[] args) throws Exception {
        JFrame f = new App();
        f.setTitle("Microwave GUI");
        // f.setLayout(null);
        f.setSize(1600, 1000);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
