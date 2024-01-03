public class Bomb extends Square {
    public void hit(int x, int y, boolean lost) {
        this.display = '*';
        if (!lost) {
            Board.lose();
        }
    }
    public void hit(int x, int y) {
        this.hit(x, y, false);
    }
}
