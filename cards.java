public class Cards {

    private final String suit;
    private final String rank;

    public Cards (String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
       
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }
    
    public String toString() {
        return suit + " of " + rank;
    }
}
