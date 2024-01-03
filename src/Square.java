public abstract class Square {
    public char defaultDisplay = '#';
    public char display = defaultDisplay;
    public void draw () {
        System.out.print(display);
    };
    public void flag () {
        if (this.display == 'F'){
            this.display = defaultDisplay;
        } else if (display == defaultDisplay) {
            this.display = 'F';
        }
    }
    public abstract void hit (int x, int y, boolean lost);
    public abstract void hit (int x, int y);
    public boolean isZero() {
        return this instanceof Empty && ((Empty) this).amountOfBombs == 0;
    };
}
