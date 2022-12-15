import java.util.Random;

public class Deck {
    Cards[] cards = new Cards[52];
    String[] suits = {"A","2","3","4","5","6","7","8","9","10","Jake","Queen","King"};
    String[] ranks = {"Clubs", "Spades", "Diamonds", "Hearts"};
    Cards[] playerHand = new Cards[4];
    Cards[] computerHand = new Cards[4];
    Cards[] floor = new Cards[4];

    public Deck() {
        int index = 0;
        for(int i=0; i<suits.length; i++) {
            for (int j=0; j<ranks.length; j++) {
                this.cards[index] = new Cards(suits[i], ranks[j]);
                index++;
            }
        }
    }


    public Cards[] getDeck() {
        return cards;
    }

    public void Shuffle() {
        Random rand = new Random();
        for (int i = 0; i<52; i++) {
            int swapIndex = rand.nextInt(52 - i) + i;
            Cards temp = cards[i];
            cards[i] = cards[swapIndex];
            cards[swapIndex] = temp;
        }
    }

    public void Cut() {
        Random rand = new Random();
        int cutIndex = rand.nextInt(51);
        Cards[] cutDeck = new Cards[cutIndex];
        for (int i=0; i<cutIndex; i++) {
            cutDeck[i] = cards[i];
        }
        for (int i=cutIndex; i<52; i++) {
            cards[i-cutIndex] = cards[i];
        }
        for (int i=0; i<cutIndex; i++) {
            cards[52-cutIndex+i] = cutDeck[i];
        }
    }

    public void Deal(int numCards) {
        Shuffle();
        Cut();
        for (int i=0; i<numCards; i++) {
            playerHand[i] = cards[i];
            computerHand[i] = cards[i+numCards];
            floor[i] = cards[i+2*numCards];
        }
    }
}
