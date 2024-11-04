public class Event {
    public static boolean checkHit(Charector c, Bullet[] b){
        if(c.y < 150){
            // System.out.println("Win");
            return true;
        }
        return false;
    }
    // public static boolean checkHeal(){

    // }
}
