package LootTheLoop;

import java.util.ArrayList;
import java.util.Collections;

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
        for (int i = 0; i < 100; i++) {
            Collections.shuffle(temple);
        }
    }
    
    public boolean addToNotes(int index) {
        
        GameCard e = temple.get(temple.size() - index);
        
        if (e.getFace() == GameCard.Face.DOWN) {
            System.err.println("Can not add face DOWN to Notes");
            return false;
        }
        
        String eType = e.getRankType();
        if (eType.equals("J") || eType.equals("T") || eType.equals("A")) {
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
    
    public void addToTemple(int index) {
        GameCard e = notes.get(notes.size() - index);
        temple.add(e);
        notes.remove(e);
    }
    
    public boolean addToScore(int index) {
        
        GameCard e = temple.get(temple.size() - index);
        
        if (e.getFace() == GameCard.Face.DOWN) {
            System.err.println("Can not add face DOWN to Score");
            return false;
        }
        
        String eType = e.getRankType();
        if (eType.equals("J") || eType.equals("T")) {
            System.err.println("Can not add " + e.getRank() + " to Score");
            return false;
        }
        
        score.add(e);
        temple.remove(e);
        return true;
    }
    
    public void list(ArrayList<GameCard> gameList) {
        gameList.forEach(System.out::println);
    }
    
    public boolean actLookAround() {
        if (temple.get(temple.size() - 1).getFace() == GameCard.Face.DOWN) {
            temple.get(temple.size() - 1).faceUp();
            temple.get(temple.size() - 2).faceUp();
            return true;
        } else {
            System.err.println("Top card is face UP");
            return false;
        }
    }
    
    public boolean actExplore(int index) {
        
        GameCard e = temple.get(temple.size() - index);
        
        ArrayList<GameCard> tmp = new ArrayList<>();
        
        int movement;
        
        if (e.getFace() == GameCard.Face.DOWN) {
            System.err.println("Can not explore face DOWN card");
            return false;
        }
        
        if (null == e.getRank()) {
            System.err.println("Can not explore " + e.getRank() + " card");
            return false;
        } else {
            switch (e.getRank()) {
                case TWO:
                    movement = 2;
                    break;
                case THREE:
                    movement = 3;
                    break;
                case FOUR:
                    movement = 4;
                    break;
                case FIVE:
                    movement = 5;
                    break;
                case SIX:
                    movement = 6;
                    break;
                case SEVEN:
                    movement = 7;
                    break;
                case EIGHT:
                    movement = 8;
                    break;
                case NINE:
                    movement = 9;
                    break;
                case TEN:
                    movement = 10;
                    break;
                default:
                    System.err.println("Can not explore " + e.getRank() + " card");
                    return false;
            }
        }
        
        int i = -1;
        
        while (i < movement) {
            tmp.add(temple.get(temple.size() - 1));
            temple.remove(temple.size() - 1);
            i++;
        }
        
        Collections.reverse(tmp);
        temple.addAll(0, tmp);
        return true;
    }
    
    public boolean isDead(int index) {
        
        GameCard e = temple.get(temple.size() - index);
        
        if (e.getFace() == GameCard.Face.DOWN) {
            return false;
        }
        
        String eType = e.getRankType();
        return eType.equals("J") || eType.equals("T");
    }
    
    public boolean isDead2() {
        
        String firCard = temple.get(temple.size() - 1).getRankType();
        String secCard = temple.get(temple.size() - 2).getRankType();
        
        if (firCard.equals("J") || firCard.equals("T")) {
            return secCard.equals("J") || secCard.equals("T") || secCard.equals("A");
        } else if (firCard.equals("A")) {
            return secCard.equals("J") || secCard.equals("T");
        }
        
        return false;
    }
    
    public boolean exitTemple(int index) {
        
        GameCard e = temple.get(temple.size() - index);
        
        if (e.getFace() == GameCard.Face.DOWN) {
            System.err.println("Can not exit on face DOWN card");
            return false;
        }
        
        if (e.getRank() != GameCard.Rank.JOKER) {
            System.err.println("Must exit on JOKER card");
            return false;
        }
        
        int count = 0;
        for (GameCard sc : score) {
            if (sc.getRank() == GameCard.Rank.ACE) {
                count++;
            }
        }
        
        if (count != 4) {
            System.err.println("You have not collected enough ACES");
            return false;
        }
        
        int point = 0;
        int tmp;
        
        System.out.println("\n--- Score ---");
        for (int i = 0; i < score.size(); i++) {
            System.out.println((i + 1) + ": " + score.get(i));
            
            switch (score.get(i).getRank()) {
                case TWO:
                    tmp = 2;
                    break;
                case THREE:
                    tmp = 3;
                    break;
                case FOUR:
                    tmp = 4;
                    break;
                case FIVE:
                    tmp = 5;
                    break;
                case SIX:
                    tmp = 6;
                    break;
                case SEVEN:
                    tmp = 7;
                    break;
                case EIGHT:
                    tmp = 8;
                    break;
                case NINE:
                    tmp = 9;
                    break;
                case TEN:
                    tmp = 10;
                    break;
                default:
                    tmp = 0;
            }
            point += tmp;
        }
        
        System.out.println("Your score is: " + point);
        return true;
    }
}
