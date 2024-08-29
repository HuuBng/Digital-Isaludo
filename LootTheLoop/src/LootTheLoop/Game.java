package LootTheLoop;

import java.util.*;

public class Game {

    public static void main(String[] args) {

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
        game.add("View temple/notes/score");
        game.add("Exit temple");

        ArrayList<String> cardChoice = new ArrayList<>();
        cardChoice.add("1st card");
        cardChoice.add("2nd card");

        ArrayList<String> decks = new ArrayList<>();
        decks.add("Temple");
        decks.add("Notes");
        decks.add("Score");

        Menu menu = new Menu();
        int choice;
        int choice2;

        System.out.println("--- Are you ready? ---");
        System.out.println("p/s: the top card is at the bottom in every deck.");

        do {
            System.out.println("\n-----------------------------------");
            System.out.println("\n       Loot the Loop (digital)");
            System.out.println("\n What is your action?");
            System.out.println();

            choice = menu.int_getChoice(game);
            switch (choice) {
                case 1: // Action: Look Around
                    if (deck.actLookAround()) {
                        System.out.println("=== Looking around ===");
                        deck.list(deck.temple);
                        if (deck.isDead(deck.temple.get(deck.temple.size() - 1)) && deck.isDead(deck.temple.get(deck.temple.size() - 2)) && deck.notes.isEmpty()) {
                            System.err.println("You landed in a TRAP room");
                            System.err.println("\n === GAME OVER ===");
                            throw new IllegalArgumentException("You are DEAD!!!");
                        }
                    }
                    break;

                case 2: // Action: Explore
                    choice2 = menu.int_getChoice(cardChoice);

                    if (choice2 < 1 || choice2 > 2) {
                        System.err.println("=== Action canceled ===");
                        break;
                    }

                    if (deck.actExplore(deck.temple.get(deck.temple.size() - choice2))) {
                        System.out.println("=== Exploring ===");
                        deck.list(deck.temple);
                        if (deck.isDead(deck.temple.get(deck.temple.size() - 1))) {
                            System.err.println("You landed in a TRAP room");
                            System.err.println("\n === GAME OVER ===");
                            throw new IllegalArgumentException("You are DEAD!!!");
                        }
                    }

                    break;

                case 3: // Action: Mark a path
                    choice2 = menu.int_getChoice(cardChoice);

                    if (choice2 < 1 || choice2 > 2) {
                        System.err.println("=== Action canceled ===");
                        break;
                    }

                    if (deck.addToNotes(deck.temple.get(deck.temple.size() - choice2))) {
                        System.out.println("=== Path marked ===");
                        deck.list(deck.temple);
                    }

                    break;

                case 4: // Action: Return to marked path
                    cardChoice.add("3rd card");
                    deck.list(deck.notes);

                    choice2 = menu.int_getChoice(cardChoice);
                    cardChoice.remove(cardChoice.size() - 1);

                    if (choice2 < 1 || choice2 > deck.notes.size()) {
                        System.err.println("=== Action canceled ===");
                        break;
                    }

                    deck.addToTemple(deck.notes.get(deck.notes.size() - choice2));
                    System.out.println("=== Returned to marked path ===");
                    deck.list(deck.temple);
                    break;

                case 5: // Action: Take jewel/trinket
                    if (deck.addToScore(deck.temple.get(deck.temple.size() - 1))) {
                        System.out.println("=== Card taken ===");
                        deck.list(deck.temple);
                    }

                    break;

                case 6: // View temple/notes/score
                    choice2 = menu.int_getChoice(decks);
                    switch (choice2) {
                        case 1:
                            deck.list(deck.temple);
                            break;
                        case 2:
                            deck.list(deck.notes);
                            break;
                        case 3:
                            deck.list(deck.score);
                            break;
                        default:
                            System.err.println("=== Action canceled ===");
                    }
                    break;

                case 7: // Exit temple
                    if (deck.exitTemple(deck.temple.get(deck.temple.size() - 1))) {
                        throw new IllegalStateException("=== THE END ===");
                    }
                    break;
                default:
                    System.out.println("Thank you for playing!!!");
            }
        } while (choice > 0 && choice <= game.size());

    }
}
