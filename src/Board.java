import java.util.ArrayList;
import java.util.List;

public final class Board {

    private static Board INSTANCE;

    public static List<List<Square>> board;

    private Board() {
    }

    public static Board getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Board();
        }

        return INSTANCE;
    }

    private static Integer randomizeNum(int min, int max) {
        return (Integer) (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static void generateNew(int width, int height) {
        int amount = Math.round((float) (width * height) * (float) 0.15);
        List<List<Integer>> minePlaces = new ArrayList<>(amount);
        board = new ArrayList<List<Square>>(width);

        while (minePlaces.size() < amount) {
            List<Integer> place = new ArrayList<>(2);
            place.add(randomizeNum(0, width));
            place.add(randomizeNum(0, height));
            if (!minePlaces.contains(place)) {
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
                    if (x > 0 && y > 0 && board.get(x-1).get(y-1) instanceof Bomb) {count += 1;};
//                    ###
//                    X+#
//                    ###
                    if (x > 0 && board.get(x-1).get(y) instanceof Bomb) {count += 1;};
//                    ###
//                    #+#
//                    X##
                    if (x > 0 && y < board.get(0).size() && board.get(x-1).get(y+1) instanceof Bomb) {count += 1;};

//                    #X#
//                    #+#
//                    ###
                    if (y > 0 && board.get(x).get(y-1) instanceof Bomb) {count += 1;};
//                    ###
//                    #+#
//                    #X#
                    if (y < board.get(0).size() && board.get(x).get(y+1) instanceof Bomb) {count += 1;};

//                    ##X
//                    #+#
//                    ###
                    if (x > 0 && y > 0 && board.get(x-1).get(y-1) instanceof Bomb) {count += 1;};
//                    ###
//                    #+X
//                    ###
                    if (x > 0 && board.get(x-1).get(y) instanceof Bomb) {count += 1;};
//                    ###
//                    #+#
//                    ##X
                    if (x > 0 && y < board.get(0).size() && board.get(x-1).get(y+1) instanceof Bomb) {count += 1;};
                }
            }
        }
    }

    public static void draw() {
        for (int i = 0; i < board.size(); i++) {
            List<Square> col = board.get(i);
            for (int j = 0; j < col.size(); j++) {
                Square square = col.get(j);
                square.hit(); // TODO: Delete this hit() after testing
                square.draw();
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
}
