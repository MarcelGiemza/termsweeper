public abstract class Square {
    public char display = '=';
    public void draw () {
        System.out.print(display);
    };
    public void flag () {
        if (this.display == 'F'){
            this.display = '=';
        } else {
            this.display = 'F';
        }
    }
    public abstract void hit (int x, int y);
    public boolean isZero() {
        return this instanceof Empty && ((Empty) this).amountOfBombs == 0;
    };
}
