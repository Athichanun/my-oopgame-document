public class Charector {
    public int x;
    public int y;
    public int size;
    public int hp;
    Charector(int x, int y, int size, int hp){
        this.x = x;
        this.y = y;
        this.size = size;
        this.hp = hp;
    }
    public void left(){
        this.x -= 15;
    }
    public void right(){
        this.x += 15;
    }
}
