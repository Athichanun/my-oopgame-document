import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Game extends JPanel{
    Image img_bg;
    Image img_actor = new ImageIcon(getClass().getResource("actor.png")).getImage();
    Image img_main = new ImageIcon(getClass().getResource("main.png")).getImage();
    Image img_boss = new ImageIcon(getClass().getResource("boss.png")).getImage();
    Image img_boom = new ImageIcon(getClass().getResource("boom.png")).getImage();
    Image img_medic = new ImageIcon(getClass().getResource("medic.png")).getImage();
    int maxHP = 600;
    Charector c = new Charector(800, 700, 80, 600);
    Bullet[] b;
    Medic[] heal;
    App main;
    Game game;
    int round;
    Timer t;
    Timer going;
    Rectangle[] blockBull;
    Rectangle[] blockHeal;
    Rectangle blockmain;
    Timer healtime ;
    // Timer drop ;
    // Timer fire;
        Game(int round, App main){
            this.main = main;
            this.round = round;
            this.game = this;
            // int bot = ((round * 25) + 100) * (round * 3);
            int bot = round * 20;
            this.b = new Bullet[bot];
            this.blockBull = new Rectangle[b.length];
            this.heal = new Medic[round + 2];
            this.blockHeal = new Rectangle[heal.length];
            switch (round) {
                case 1:
                    img_bg = new ImageIcon(getClass().getResource("state1.JPG")).getImage();
                    break;
                case 2 :
                    img_bg = new ImageIcon(getClass().getResource("state2.JPG")).getImage();
                    break;
                case 3 :
                    img_bg = new ImageIcon(getClass().getResource("state3.JPG")).getImage();
                    break;
                default:
                    break;
            }
            // System.out.println(bot);
            for(int i = 0; i < bot; i++){
                double posx = Math.floor(Math.random() * 1500) + 20;
                double posy = (Math.floor(Math.random()* -2000));
                b[i] = new Bullet((int)posx, (int)posy);
                // pew[i] = new Timer(5, new LasorPew(i));
                // pew[i].start();
            }
            for(int i = 0; i < heal.length; i++){
                int posx = (int)Math.floor(Math.random() * 1500) + 20;
                int posy = (int)(Math.floor(Math.random()* -2000));
                heal[i] = new Medic(posx, posy, 60);
            }
            healtime = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int buff = 100 / round;
                    for(int i = 0; i < heal.length; i++){
                        if(blockmain.intersects(blockHeal[i])){
                            if(heal[i].visible){
                                if(c.hp != maxHP){
                                    if(c.hp <= maxHP - buff){
                                        c.hp += buff;
                                    }else{
                                        int less = 600 - c.hp;
                                        c.hp += less;
                                    }
                                    heal[i].visible = false;
                                } 
                            }
                        }
                    }
                }
            });
            // drop = new Timer(3, new ActionListener() {
            //     @Override
            //     public void actionPerformed(ActionEvent e) {
            //         for(int i = 0; i < heal.length; i++){
            //             if(heal[i].visible){
            //                 heal[i].drop(getWidth());
            //                 g.setColor(Color.green);
            //                 g.drawImage(img_medic,heal[i].x, heal[i].y, heal[i].size, heal[i].size, game);
            //                 blockHeal[i] = new Rectangle(heal[i].x, heal[i].y, 30, 30);
            //             }
                        
            //         }
            //     }
            // });
            // fire  = new Timer(5, new ActionListener() {
            //     @Override
            //     public void actionPerformed(ActionEvent e) {
            //         for(int i = 0; i < b.length; i++){
            //             // b[i].forward();
            //             // g.drawImage(img_actor, b[i].x, b[i].y, 30, 100, game);
            //             // blockBull[i] = new Rectangle( b[i].x, b[i].y, 10, 80);
            //         }
            //     } 
            //  });
            this.t = new Timer(3, new Listener());
            addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == 65){
                        if(c.x > 0){
                            c.left();
                        }
                    }
                    if(e.getKeyCode() == 68){
                        if(c.x < getWidth() - 50){
                            c.right();
                        }
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {
                    // TODO Auto-generated method stub
                    
                }
                @Override
                public void keyTyped(KeyEvent e) {
                    // TODO Auto-generated method stub
                    
                }
            });
            this.t.start();
            this.going = new Timer(50 * round, new GoingThrough());
            this.going.start();
            this.healtime.start();
            // drop.start();
            // fire.start();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g.drawImage(img_bg, 0,0,getWidth(), getHeight(), this);
            for(int i = 0; i < b.length; i++){
                b[i].forward();
                g.drawImage(img_actor, b[i].x, b[i].y, 30, 100, this);
                blockBull[i] = new Rectangle( b[i].x, b[i].y, 10, 80);
            }
            for(int i = 0; i < heal.length; i++){
                if(heal[i].visible){
                    heal[i].drop(getWidth());
                    g.setColor(Color.green);
                    g.drawImage(img_medic,heal[i].x, heal[i].y, heal[i].size, heal[i].size, this);
                    blockHeal[i] = new Rectangle(heal[i].x, heal[i].y, 30, 30);
                }
                
            }
            g.drawImage(img_boss, (getWidth() - 800) / 2, -30, 800, 300, this);
            g.drawImage(img_main, c.x, c.y, c.size, c.size + 20, this);
            blockmain = new Rectangle(c.x, c.y, c.size - 20, c.size);
            g.setColor(Color.white);
            g.drawRect(40, 40, 301, 31);
            g.setColor(Color.red);
            g.fillRect(41, 41, c.hp / 2, 30);


            ///Check Event//////
            for(int i = 0; i < b.length ;i++){
                if(blockmain.intersects(blockBull[i])){
                    if(c.hp <= 0){
                        Timer change = new Timer(2000, new ChangeOver(this, main));
                        g.drawImage(img_boom, c.x, c.y, c.size + 20, c.size + 20, this);
                        this.t.stop();
                        this.going.stop();
                        this.healtime.stop();
                        change.setRepeats(false);
                        change.start();
                    }else{
                        c.hp -= round;
                    }
                    g2d.setStroke(new BasicStroke(20));
                    g2d.setColor(Color.RED);
                    g2d.drawRect(0, 0, getWidth(), getHeight());
                }
            }
            if(Event.checkHit(c, b)){
                this.t.stop();
                this.going.stop();
                this.healtime.stop();
                g.drawImage(img_boom, (getWidth() - 600) / 2, -30, 600, 300, this);
                Timer conchange = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        main.startCon(game, round, c.hp);
                    }
                });
                conchange.setRepeats(false);
                conchange.start();
            }
            
            
            
        }
    class Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }
    class GoingThrough implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            c.y--;
        }
    }
    class ChangeOver implements ActionListener{
        Game game;
        App main;
        public ChangeOver(Game game, App app){
            this.game = game;
            this.main = app;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            this.main.Over(game);
        }
    }
}
