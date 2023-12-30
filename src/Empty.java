public class Empty extends Square {
    private int amountOfBombs;

    public void setAmountOfBombs(int amountOfBombs) {
        this.amountOfBombs = amountOfBombs;
    }

    public void hit () {
        this.display = (char) String.valueOf(amountOfBombs).charAt(0);
    }
}
