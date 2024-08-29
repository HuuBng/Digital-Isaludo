package LootTheLoop;

public class GameCard {

    public enum Suit {
        JOKER, HEART, DIAMOND, CLUB, SPADE
    }

    public enum Rank {
        JOKER("J"), ACE("A"), TWO("M"), THREE("M"), FOUR("M"), FIVE("M"), SIX("M"), SEVEN("M"), EIGHT("M"), NINE("M"), TEN("M"), JACK("T"), QUEEN("T"), KING("T");

        private final String type;

        Rank(String type) {
            this.type = type;
        }
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

    public String getRankType() {
        return rank.type;
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
            return "--- of ---";
        } else {
            return rank + " of " + suit;
        }
    }

}
