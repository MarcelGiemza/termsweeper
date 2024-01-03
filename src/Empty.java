public class Empty extends Square {
    public int amountOfBombs;

    public void setAmountOfBombs(int amountOfBombs) {
        this.amountOfBombs = amountOfBombs;
    }

    public void hit(int x, int y, boolean lost) {
        if (this.display == this.defaultDisplay) {
            this.display = (char) String.valueOf(amountOfBombs).charAt(0);
            if (amountOfBombs == 0 && !lost) {
                Board.hitAllAround(x, y);
                Board.checkWin();
            }
        }
    }
    public void hit(int x, int y) {
        this.hit(x, y, false);
    }
}
