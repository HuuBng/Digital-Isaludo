package LootTheLoop;

public class GameCard {

    public enum Suit {
        JOKER, HEART, DIAMOND, CLUB, SPADE
    }

    public enum Rank {
        JOKER, ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    public enum Face {
        UP, DOWN
    }

    private Rank rank;
    private Suit suit;
    private Face face;

    public GameCard() {
    }

    public GameCard(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        this.face = Face.DOWN;
    }

    public void faceUp() {
        this.face = Face.UP;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Face getFace() {
        return face;
    }

    @Override
    public String toString() {
        if (face == Face.DOWN) {
            return "--- of --- face " + face;
        } else {
            return rank + " of " + suit + " face " + face;
        }
    }

}
