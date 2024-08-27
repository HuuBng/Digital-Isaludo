package LootTheLoop;

import java.util.*;

public class GameDeck extends ArrayList<GameCard> {

    ArrayList<GameCard> temple;
    ArrayList<GameCard> notes;
    ArrayList<GameCard> score;

    public GameDeck() {
        temple = new ArrayList<>();
        notes = new ArrayList<>();
        score = new ArrayList<>();
    }

    public void reset() {
        // Add a joker
        temple.add(new GameCard(GameCard.Rank.JOKER, GameCard.Suit.JOKER));

        // Add other cards
        for (int i = 1; i < GameCard.Suit.values().length; i++) {
            for (int j = 1; j < GameCard.Rank.values().length; j++) {
                temple.add(new GameCard(GameCard.Rank.values()[j], GameCard.Suit.values()[i]));
            }
        }
    }

    public void shuffle() {
        Random ran = new Random();
        int x = ran.nextInt(9) + 2;
        int i = 0;
        while (i < x) {
            Collections.shuffle(temple);
            i++;
        }

    }

    public boolean addToNotes(GameCard e) throws IllegalArgumentException {
        if (e.getFace() == GameCard.Face.DOWN) {
            System.err.println("Face is DOWN");
            return false;
        }

        if (e.getRank() == GameCard.Rank.JOKER) {
            System.err.println("Can not add JOKER to Notes");
            return false;
        }

        if (notes.size() < 3) {
            notes.add(e);
            return temple.remove(e);
        } else {
            System.err.println("Notes is FULL");
            return false;
        }
    }

    public void addToTemple(GameCard e) {
        temple.add(e);
        notes.remove(e);
    }

    public void addToDiscard(GameCard e) {
        score.add(e);
        temple.remove(e);
    }

    public void list(ArrayList gameList) {
        gameList.forEach((gc) -> {
            System.out.println(gc);
        });
    }

    public void listAll() {
        System.out.println("\n--- Temple ---");
        int ti = 1;
        for (int i = 0; i < temple.size(); i++) {
            System.out.println(ti + ": " + temple.get(i));
            ti++;
        }

        System.out.println("\n--- Notes ---");
//        notes.forEach((ngc) -> {
//            System.out.println(ngc);
//        });

        int ni = 1;
        for (int i = 0; i < notes.size(); i++) {
            System.out.println(ni + ": " + notes.get(i));
            ni++;
        }
    }

    public void actLookAround() {
        if (temple.get(temple.size() - 1).getFace() == GameCard.Face.DOWN) {
            temple.get(temple.size() - 1).faceUp();
            temple.get(temple.size() - 2).faceUp();
        } else {
            System.err.println("Top card is face UP");
        }
    }

    public void actExplore(GameCard e) {

        ArrayList<GameCard> tmp = new ArrayList<>();

        int movement = 0;

        if (e.getRank() == GameCard.Rank.TWO) {
            movement = 2;
        }

        if (e.getRank() == GameCard.Rank.THREE) {
            movement = 3;
        }

        if (e.getRank() == GameCard.Rank.FOUR) {
            movement = 4;
        }

        if (e.getRank() == GameCard.Rank.FIVE) {
            movement = 5;
        }

        if (e.getRank() == GameCard.Rank.SIX) {
            movement = 6;
        }

        if (e.getRank() == GameCard.Rank.SEVEN) {
            movement = 7;
        }

        if (e.getRank() == GameCard.Rank.EIGHT) {
            movement = 8;
        }

        if (e.getRank() == GameCard.Rank.NINE) {
            movement = 9;
        }

        if (e.getRank() == GameCard.Rank.TEN) {
            movement = 10;
        }

        int i = 0;
        while (i <= movement) {
            tmp.add(temple.get(temple.size() - 1));
            temple.remove(temple.size() - 1);
            i++;
        }
        Collections.reverse(tmp);
        temple.addAll(0, tmp);
    }
}
