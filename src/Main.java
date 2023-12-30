public class Main {
    public static void main(String[] args) {
        Board board = Board.getInstance();
        board.generateNew(10, 10);
//        for (int i = 0; i < board.board.size(); i++) {
//            System.out.println(board.board.get(i));
//        }
        board.draw();
    }
}
