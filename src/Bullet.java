public class Bullet {
    public int x;
    public int y;
    Bullet(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void forward(){
        this.y += 3;
        if(this.y >= 1200){
            this.y = -200;
            this.x = (int)Math.floor(Math.random() * 1500) + 20;
        }
    }
}
