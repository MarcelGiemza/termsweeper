public class Empty extends Square {
    public int amountOfBombs;

    public void setAmountOfBombs(int amountOfBombs) {
        this.amountOfBombs = amountOfBombs;
    }

    public void hit(int x, int y) {
        if (this.display == '=') {
            this.display = (char) String.valueOf(amountOfBombs).charAt(0);
            if (amountOfBombs == 0) {
                Board.hitAllAround(x, y);
            } else {
                Board.hitZerosAround(x, y);
            }
        }
    }
}
