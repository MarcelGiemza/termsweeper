import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Termseeper");
        try {
            boolean play = true;
            while (play) {
                System.out.println("Select a board size [enter a number]");
                System.out.println("If you want to choose big board size make sure that your terminal window will fit it");
                System.out.println("1. 10x10");
                System.out.println("2. 20x10");
                System.out.println("3. 15x15");
                System.out.println("4. 20x20");
                System.out.println("5. 35x30");
                System.out.println("6. 50x40");
                System.out.println("7. 70x60");
                System.out.println("8. 99x99");
                System.out.println("9. custom");

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));

                System.out.print("> ");
                String selected = reader.readLine().trim();

                int width = 0;
                int height = 0;
                int bombPerc = 90;

                switch (selected) {
                    case "1":
                        width = 10;
                        height = 10;
                        break;
                    case "2":
                        width = 20;
                        height = 10;
                        break;
                    case "3":
                        width = 15;
                        height = 15;
                        break;
                    case "4":
                        width = 20;
                        height = 20;
                        break;
                    case "5":
                        width = 30;
                        height = 30;
                        break;
                    case "6":
                        width = 40;
                        height = 30;
                        break;
                    case "7":
                        width = 50;
                        height = 40;
                        break;
                    case "8":
                        width = 99;
                        height = 99;
                        break;
                    case null, default:
                        System.out.println("Enter board width [5-99]");
                        System.out.print("> ");
                        width = Integer.parseInt(reader.readLine().trim());
                        System.out.println("Enter board height [5-99]");
                        System.out.print("> ");
                        height = Integer.parseInt(reader.readLine().trim());
                }

                if (width > 99) {
                    width = 99;
                } else if (width < 5) {
                    width = 1;
                }
                if (height > 99) {
                    height = 99;
                } else if (height < 5) {
                    height = 1;
                }

                System.out.println("Size set to: " + String.valueOf(width) + "x" + String.valueOf(height));

                System.out.println("Enter percentage amount of squares that should be bombs [1-95] (default = 15)");
                System.out.print("> ");
                String bp = reader.readLine().trim();
                try {
                    bombPerc = Integer.parseInt(bp);
                    if (bombPerc > 95) {
                        bombPerc = 95;
                    } else if (bombPerc < 1) {
                        bombPerc = 1;
                    }
                } catch (Exception e) {
                    bombPerc = 15;
                }
                System.out.println("Amount of bombs set to: " + String.valueOf(bombPerc) + "%");

                Board board = Board.getInstance();

                boolean game = true;

                boolean start = true;
                while (start) {
                    board.drawEmpty(width, height);
                    System.out.println("");
                    System.out.println("Enter command");
                    System.out.println("'{x} {y}' to hit first square");
                    System.out.println("'quit' to quit");
                    System.out.print("> ");
                    String firstHit = reader.readLine();
                    List<String> command = Arrays.asList(firstHit.split("\\s+"));
                    try {
                        board.generateNew(width, height, bombPerc, Integer.parseInt(command.get(0)) - 1, Integer.parseInt(command.get(1)) - 1);
                        start = false;
                    } catch (Exception e) {
                        if (firstHit.equals("quit")) {
                            start = false;
                            game = false;
                        } else {
                            System.out.println("");
                            System.out.println("Something went wrong, please try again");
                        }
                    }
                }

                while (game) {
                    board.draw();

                    if (board.isLost()) {
                        System.out.println("");
                        System.out.println("You lost!");
                        game = false;
                    } else if (board.isWon()) {
                        System.out.println("");
                        System.out.println("You won!");
                        game = false;
                    } else {
                        System.out.println("");
                        System.out.println("Enter command");
                        System.out.println("'h {x} {y}' to hit a square");
                        System.out.println("'f {x} {y}' to place flag");
                        System.out.println("'quit' to quit");

                        System.out.print("> ");
                        String input = reader.readLine();

                        List<String> command = Arrays.asList(input.split("\\s+"));

                        try {
                            switch (command.get(0).toLowerCase()) {
                                case "f":
                                    board.board.get(Integer.parseInt(command.get(1)) - 1).get(Integer.parseInt(command.get(2)) - 1).flag();
                                    break;
                                case "h":
                                    board.board.get(Integer.parseInt(command.get(1)) - 1).get(Integer.parseInt(command.get(2)) - 1).hit(Integer.parseInt(command.get(1)) - 1, Integer.parseInt(command.get(2)) - 1);
                                    break;
                                case "quit":
                                    game = false;
                                    break;
                                case null, default:
                                    System.out.println("Unknown command");
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("x or y was too high");
                        }
                    }
                }
                System.out.println("Do you want to play again? [y/n]");
                System.out.print("> ");
                String again = reader.readLine().trim();
                if (!(again.equals("y") || again.equals("ye") || again.equals("yes"))) {
                    play = false;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
