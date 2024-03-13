public abstract class Square {
    public char defaultDisplay = '#';
    public char display = defaultDisplay;

    public void draw() {
        switch (this.display) {
            case '0':
                System.out.print("\u001B[32m");
                break;
            case '1', '2':
                System.out.print("\u001B[36m");
                break;
            case '3', '4':
                System.out.print("\u001B[33m");
                break;
            case  '5', '6':
                System.out.print("\u001B[31m");
                break;
            case '7', '8':
                System.out.print("\u001B[35m");
                break;
            case '*':
                System.out.print("\u001B[41m");
        }
        System.out.print(this.display);
        System.out.print("\u001B[0m");
    }

    public void flag() {
        if (this.display == 'F') {
            this.display = defaultDisplay;
        } else if (display == defaultDisplay) {
            this.display = 'F';
        }
    }

    public abstract void hit(int x, int y, boolean lost);

    public abstract void hit(int x, int y);

    public boolean isZero() {
        return this instanceof Empty && ((Empty) this).amountOfBombs == 0;
    }
}
