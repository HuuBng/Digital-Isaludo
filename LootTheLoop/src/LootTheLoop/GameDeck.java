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
        int x = ran.nextInt(9) + 2; // shuffle 2~10 times
        int i = 0;
        while (i < x) {
            Collections.shuffle(temple);
            i++;
        }

    }

    public boolean addToNotes(GameCard e) {
        if (e.getFace() == GameCard.Face.DOWN) {
            System.err.println("Can not add face DOWN to Notes");
            return false;
        }

        if (e.getRank() == GameCard.Rank.JOKER || e.getRank() == GameCard.Rank.JACK
                || e.getRank() == GameCard.Rank.QUEEN || e.getRank() == GameCard.Rank.KING) {
            System.err.println("Can not add " + e.getRank() + " to Notes");
            return false;
        }

        if (notes.size() < 3) {
            notes.add(e);
            temple.remove(e);
            return true;
        } else {
            System.err.println("Notes is FULL");
            return false;
        }
    }

    public void addToTemple(GameCard e) {
        temple.add(e);
        notes.remove(e);
    }

    public boolean addToScore(GameCard e) {
        if (e.getFace() == GameCard.Face.DOWN) {
            System.err.println("Can not add face DOWN to Score");
            return false;
        }

        if (e.getRank() == GameCard.Rank.JOKER || e.getRank() == GameCard.Rank.JACK
                || e.getRank() == GameCard.Rank.QUEEN || e.getRank() == GameCard.Rank.KING) {
            System.err.println("Can not add " + e.getRank() + " to Score");
            return false;
        }

        score.add(e);
        temple.remove(e);
        return true;
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

        int i = -1;
        while (i < movement) {
            tmp.add(temple.get(temple.size() - 1));
            temple.remove(temple.size() - 1);
            i++;
        }
        Collections.reverse(tmp);
        temple.addAll(0, tmp);
    }
}
