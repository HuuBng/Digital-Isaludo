package LootTheLoop;

import java.util.*;

public class Game {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Prepare temple
        GameDeck deck = new GameDeck();
        deck.reset();
        deck.shuffle();

        // Prepare menu
        ArrayList<String> game = new ArrayList<>();
        game.add("Action: Look Around");
        game.add("Action: Explore");
        game.add("Action: Mark a path");
        game.add("Action: Return to marked path");
        game.add("Action: Take jewel/trinket");
        game.add("View temple");
        game.add("View notes");

        Menu menu = new Menu();
        int choice;

        System.out.println("--- Are you ready? ---");
        System.out.println("p/s: the top card is at the bottom.");

        do {
            System.out.println("-----------------------------------");
            System.out.println("\n       Loot the Loop (digital)");
            System.out.println("\n What is your action?");
            System.out.println();

            choice = menu.int_getChoice(game);
            switch (choice) {
                case 1:
                    deck.actLookAround();
                    deck.list(deck.temple);
                    break;
                case 2:
                    int choice2 = 0;
                    do {
                        System.out.println("Which card?(choose 1 or 2) \n1. top card\n2. top 2 card");
                        choice2 = Integer.parseInt(sc.nextLine());
                    } while (choice2 < 1 || choice2 > 2);
                    deck.actExplore(deck.temple.get(deck.temple.size() - choice2));
                    break;
                case 3:
                    int choice3 = 0;
                    do {
                        System.out.println("Which card?(choose 1 or 2) \n1. top card\n2. top 2 card");
                        choice3 = Integer.parseInt(sc.nextLine());
                    } while (choice3 < 1 || choice3 > 2);
                    deck.addToNotes(deck.temple.get(deck.temple.size() - choice3));
                    break;
                case 4:
                    int choice4 = 0;
                    deck.list(deck.notes);
                    do {
                        System.out.println("Which card?(choose 1, 2, 3)");
                        choice4 = Integer.parseInt(sc.nextLine());
                    } while (choice4 < 1 || choice4 > 3);
                    deck.addToTemple(deck.notes.get(choice4));
                    break;
                case 5:
                    deck.addToScore(deck.temple.get(deck.temple.size() - 1));
                    break;
                case 6:
                    deck.list(deck.temple);
                    break;
                case 7:
                    deck.list(deck.notes);
                    break;
                default:
                    System.out.println("Thank you for playing!!!");
            }
        } while (choice > 0 && choice <= game.size());

    }
}
