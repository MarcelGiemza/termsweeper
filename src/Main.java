import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Board board = Board.getInstance();
        board.generateNew(10, 10);
        boolean play = true;
        while (play) {
            board.draw();

            System.out.println("Enter command");
            System.out.println("'h {x} {y}' to hit");
            System.out.println("'f {x} {y}' to flag");
            System.out.println("'quit' to quit");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));

                String input = reader.readLine();

                List<String> command = Arrays.asList(input.split("\\s+"));

                if (command.get(0).toLowerCase().equals("f")){
                    board.board.get(Integer.parseInt(command.get(1))).get(Integer.parseInt(command.get(2))).flag();
                } else if (command.get(0).toLowerCase().equals("h")){
                    board.board.get(Integer.parseInt(command.get(1))).get(Integer.parseInt(command.get(2))).hit(Integer.parseInt(command.get(1)), Integer.parseInt(command.get(2)));
                } else if (command.get(0).toLowerCase().equals("quit")) {
                    play = false;
                } else {
                    System.out.println("Unknown command");
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
