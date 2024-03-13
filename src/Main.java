import java.io.BufferedReader;
import java.io.InputStreamReader;
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

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                System.out.print("> ");
                String selected = reader.readLine().trim();

                int width;
                int height;
                int bombPerc;

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
                    default:
                        System.out.println("Enter board width [5-99]");
                        System.out.print("> ");
                        try {
                            width = Integer.parseInt(reader.readLine().trim());
                        } catch (Exception e) {
                            width = 10;
                            System.out.println("Incorrect width, setting to 10");
                        }
                        System.out.println("Enter board height [5-99]");
                        System.out.print("> ");
                        try {
                            height = Integer.parseInt(reader.readLine().trim());
                        } catch (Exception e) {
                            height = 10;
                            System.out.println("Incorrect height, setting to 10");
                        }
                }

                if (width > 99) {
                    width = 99;
                } else if (width < 5) {
                    width = 5;
                }
                if (height > 99) {
                    height = 99;
                } else if (height < 5) {
                    height = 5;
                }

                System.out.println("Size set to: " + width + "x" + height);

                System.out.println("Enter percentage amount of squares that should be bombs [5-95] (default = 15)");
                System.out.print("> ");
                String bp = reader.readLine().trim();
                try {
                    bombPerc = Integer.parseInt(bp);
                    if (bombPerc > 95) {
                        bombPerc = 95;
                    } else if (bombPerc < 5) {
                        bombPerc = 5;
                    }
                } catch (Exception e) {
                    bombPerc = 15;
                }
                System.out.println("Amount of bombs set to: " + bombPerc + "%");

                boolean game = true;

                boolean start = true;
                while (start) {
                    Board.drawEmpty(width, height);
                    System.out.println();
                    System.out.println("Enter command");
                    System.out.println("'{x} {y}' to hit first square");
                    System.out.println("'quit' to quit");
                    System.out.print("> ");
                    String firstHit = reader.readLine();
                    List<String> command = Arrays.asList(firstHit.split("\\s+"));
                    try {
                        Board.generateNew(width, height, bombPerc, Integer.parseInt(command.get(0)) - 1, Integer.parseInt(command.get(1)) - 1);
                        start = false;
                    } catch (Exception e) {
                        if (firstHit.equals("quit")) {
                            start = false;
                            game = false;
                        } else {
                            System.out.println();
                            System.out.println("Something went wrong, please try again");
                        }
                    }
                }

                while (game) {
                    System.out.println();
                    System.out.println();
                    Board.draw();

                    if (Board.isLost()) {
                        System.out.println();
                        System.out.println("\u001b[41m\u001b[30m You lost! \u001b[0m");
                        game = false;
                    } else if (Board.isWon()) {
                        System.out.println();
                        System.out.println("\u001b[42m\u001b[30m You won! \u001b[0m");
                        game = false;
                    } else {
                        System.out.println();
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
                                    Board.board.get(Integer.parseInt(command.get(1)) - 1).get(Integer.parseInt(command.get(2)) - 1).flag();
                                    break;
                                case "h":
                                    Board.board.get(Integer.parseInt(command.get(1)) - 1).get(Integer.parseInt(command.get(2)) - 1).hit(Integer.parseInt(command.get(1)) - 1, Integer.parseInt(command.get(2)) - 1);
                                    break;
                                case "quit":
                                    game = false;
                                    break;
                                default:
                                    System.out.println("Unknown command");
                            }
                        } catch (Exception e) {
                            System.out.println("Something went wrong, try again.");
                        }
                    }
                }
                System.out.println("Do you want to play again? [y/N]");
                System.out.print("> ");
                String again = reader.readLine().trim().toLowerCase();
                if (!(again.equals("y") || again.equals("ye") || again.equals("yes"))) {
                    play = false;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
