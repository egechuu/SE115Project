public class Pisti {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.Shuffle();

        // print the cards in the deck to check if they are correct
        System.out.println("Cards in the deck: ");
        for (Cards card : deck.getDeck()) {
            System.out.println(card.getSuit() + " of " + card.getRank());
        }

        deck.Deal(4);

        // print the cards in each hand to check if the code runs correctly
        System.out.println("Player's hand: ");
        for (Cards card : deck.playerHand) {
            System.out.println(card.getSuit() + " of " + card.getRank());
        }
        System.out.println("Computer's hand: ");
        for (Cards card : deck.computerHand) {
            System.out.println(card.getSuit() + " of " + card.getRank());
        }
        System.out.println("Floor: ");
        for (Cards card : deck.floor) {
            System.out.println(card.getSuit() + " of " + card.getRank());
        }
    }
}
