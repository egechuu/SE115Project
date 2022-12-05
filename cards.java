public class cards {
    private String suit;
    private String face;

    public cards(String suit, String face) {
        this.suit = suit;
        this.face = face;
    }

    public String getSuit(String suit) {
        return this.suit;
    }

    public String getFace(String face) {
        return this.face;
    }
}
