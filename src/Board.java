import java.util.ArrayList;
import java.util.List;

public final class Board {

    private static boolean lost;

    public static boolean isLost() {
        return lost;
    }

    private static boolean win;

    public static boolean isWon() {
        checkWin();
        return win;
    }

    public static List<List<Square>> board;

    private Board() {
    }


    private static Integer randomizeNum(int min, int max) {
        return (Integer) (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static void generateNew(int width, int height, int bombPerc, int ax, int ay) {
        lost = false;
        List<Integer> avoid = new ArrayList<>(2);
        avoid.add(ax);
        avoid.add(ay);
        int amount = Math.round((float) (width * height) * (float) bombPerc / 100);
        List<List<Integer>> minePlaces = new ArrayList<>(amount);
        board = new ArrayList<>(width);

        while (minePlaces.size() < amount) {
            List<Integer> place = new ArrayList<>(2);
            place.add(randomizeNum(0, width));
            place.add(randomizeNum(0, height));
            if (!minePlaces.contains(place) && !place.equals(avoid)) {
                minePlaces.add(place);
            }
        }

        for (int x = 0; x < width; x++) {
            board.add(new ArrayList<>(height));
            for (int y = 0; y < height; y++) {
                List<Integer> place = new ArrayList<>(2);
                place.add(x);
                place.add(y);
                if (minePlaces.contains(place)) {
                    board.get(x).add(new Bomb());
                } else {
                    board.get(x).add(new Empty());
                }
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (board.get(x).get(y) instanceof Empty) {
                    int count = 0;

//                    X##
//                    #+#
//                    ###
                    if (x > 0 && y > 0 && board.get(x - 1).get(y - 1) instanceof Bomb)
                        count ++;
//                    ###
//                    X+#
//                    ###
                    if (x > 0 && board.get(x - 1).get(y) instanceof Bomb)
                        count ++;
//                    ###
//                    #+#
//                    X##
                    if (x > 0 && y < board.getFirst().size() - 1 && board.get(x - 1).get(y + 1) instanceof Bomb)
                        count++;

//                    #X#
//                    #+#
//                    ###
                    if (y > 0 && board.get(x).get(y - 1) instanceof Bomb)
                        count ++;
//                    ###
//                    #+#
//                    #X#
                    if (y < board.getFirst().size() - 1 && board.get(x).get(y + 1) instanceof Bomb)
                        count ++;

//                    ##X
//                    #+#
//                    ###
                    if (x < board.size() - 1 && y > 0 && board.get(x + 1).get(y - 1) instanceof Bomb)
                        count ++;
//                    ###
//                    #+X
//                    ###
                    if (x < board.size() - 1 && board.get(x + 1).get(y) instanceof Bomb)
                        count ++;
//                    ###
//                    #+#
//                    ##X
                    if (x < board.size() - 1 && y < board.getFirst().size() - 1 && board.get(x + 1).get(y + 1) instanceof Bomb)
                        count ++;

                    ((Empty) board.get(x).get(y)).setAmountOfBombs(count);
                }
            }
        }

        board.get(ax).get(ay).hit(ax, ay);
        hitZerosAround(ax, ay);
    }

    public static void draw() {
        System.out.print("   | ");
        for (int i = 1; i <= board.size(); i++) {
            System.out.print((int) (double) (i / 10));
            System.out.print(" ");
        }
        System.out.println();

        System.out.print("   | ");
        for (int i = 1; i <= board.size(); i++) {
            String n = String.valueOf(i);
            System.out.print(n.charAt(n.length() - 1));
            System.out.print(" ");
        }
        System.out.println("X");

        System.out.print("---+-");
        for (int i = 0; i < board.size() * 2; i++) {
            System.out.print("-");
        }
        System.out.println();

        for (int i = 0; i < board.getFirst().size(); i++) {
            if (i < 9) {
                System.out.print(0);
            }
            System.out.print(i + 1);
            System.out.print(" | ");

            for (List<Square> squares : board) {
                squares.get(i).draw();
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println(" Y");
    }

    public static void drawEmpty(int width, int height) {
        List<List<Square>> board = new ArrayList<>(width);
        for (int x = 0; x < width; x++) {
            board.add(new ArrayList<>(height));
            for (int y = 0; y < height; y++) {
                board.get(x).add(new Empty());
            }
        }

        System.out.print("   | ");
        for (int i = 1; i <= board.size(); i++) {
            System.out.print(i / 10);
            System.out.print(" ");
        }
        System.out.println();

        System.out.print("   | ");
        for (int i = 1; i <= board.size(); i++) {
            String n = String.valueOf(i);
            System.out.print(n.charAt(n.length() - 1));
            System.out.print(" ");
        }
        System.out.println("X");

        System.out.print("---+-");
        for (int i = 0; i < board.size() * 2; i++) {
            System.out.print("-");
        }
        System.out.println();

        for (int i = 0; i < board.getFirst().size(); i++) {
            if (i < 9) {
                System.out.print(0);
            }
            System.out.print(i + 1);
            System.out.print(" | ");

            for (List<Square> squares : board) {
                squares.get(i).draw();
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println(" Y");
    }

    private static void hitZerosAround(int x, int y) {
//                    X##
//                    #+#
//                    ###
        if (x > 0 && y > 0 && board.get(x - 1).get(y - 1).isZero()) board.get(x - 1).get(y - 1).hit(x - 1, y - 1);
//                    ###
//                    X+#
//                    ###
        if (x > 0 && board.get(x - 1).get(y).isZero()) board.get(x - 1).get(y).hit(x - 1, y);
//                    ###
//                    #+#
//                    X##
        if (x > 0 && y < board.getFirst().size() - 1 && board.get(x - 1).get(y + 1).isZero())
            board.get(x - 1).get(y + 1).hit(x - 1, y + 1);

//                    #X#
//                    #+#
//                    ###
        if (y > 0 && board.get(x).get(y - 1).isZero()) board.get(x).get(y - 1).hit(x, y - 1);
//                    ###
//                    #+#
//                    #X#
        if (y < board.getFirst().size() - 1 && board.get(x).get(y + 1).isZero()) board.get(x).get(y + 1).hit(x, y + 1);

//                    ##X
//                    #+#
//                    ###
        if (x < board.size() - 1 && y > 0 && board.get(x + 1).get(y - 1).isZero())
            board.get(x + 1).get(y - 1).hit(x + 1, y - 1);
//                    ###
//                    #+X
//                    ###
        if (x < board.size() - 1 && board.get(x + 1).get(y).isZero()) board.get(x + 1).get(y).hit(x + 1, y);
//                    ###
//                    #+#
//                    ##X
        if (x < board.size() - 1 && y < board.getFirst().size() - 1 && board.get(x + 1).get(y + 1).isZero())
            board.get(x + 1).get(y + 1).hit(x + 1, y + 1);
    }

    public static void hitAllAround(int x, int y) {
//                    X##
//                    #+#
//                    ###
        if (x > 0 && y > 0) board.get(x - 1).get(y - 1).hit(x - 1, y - 1);
//                    ###
//                    X+#
//                    ###
        if (x > 0) board.get(x - 1).get(y).hit(x - 1, y);
//                    ###
//                    #+#
//                    X##
        if (x > 0 && y < board.getFirst().size() - 1) board.get(x - 1).get(y + 1).hit(x - 1, y + 1);

//                    #X#
//                    #+#
//                    ###
        if (y > 0) board.get(x).get(y - 1).hit(x, y - 1);
//                    ###
//                    #+#
//                    #X#
        if (y < board.getFirst().size() - 1) board.get(x).get(y + 1).hit(x, y + 1);

//                    ##X
//                    #+#
//                    ###
        if (x < board.size() - 1 && y > 0) board.get(x + 1).get(y - 1).hit(x + 1, y - 1);
//                    ###
//                    #+X
//                    ###
        if (x < board.size() - 1) board.get(x + 1).get(y).hit(x + 1, y);
//                    ###
//                    #+#
//                    ##X
        if (x < board.size() - 1 && y < board.getFirst().size() - 1) board.get(x + 1).get(y + 1).hit(x + 1, y + 1);
    }

    public static void lose() {
        for (List<Square> squares : board) {
            for (int j = 0; j < board.getFirst().size(); j++) {
                squares.get(j).hit(0, 0, true);
            }
        }
        lost = true;
    }

    public static void checkWin() {
        int totalLeft = 0;
        for (List<Square> squares : board) {
            for (int j = 0; j < board.getFirst().size(); j++) {
                Square square = squares.get(j);
                if (square instanceof Empty && square.display == square.defaultDisplay) {
                    totalLeft++;
                }
            }
        }

        if (totalLeft == 0) {
            for (List<Square> squares : board) {
                for (int j = 0; j < board.getFirst().size(); j++) {
                    squares.get(j).hit(0, 0, true);
                }
            }
            win = true;
        }
    }
}
