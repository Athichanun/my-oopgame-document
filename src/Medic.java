public class Medic {
    public int x;
    public int y;
    public int size;
    public boolean visible;
    public Medic(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
        this.visible = true;
    }
    public void drop(int height){
        this.y += 4;
        if(this.y >= height + 200){
            this.y = (int)Math.floor(Math.random()* -2000);
            // this.y = -200;
            this.x = (int)Math.floor(Math.random() * 1500) + 20;
        }
    }
}
