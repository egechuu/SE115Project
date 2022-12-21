import java.util.Random;

public class Deck {
    Cards[] cards = new Cards[52];
    String[] suits = {"Clubs", "Spades", "Diamonds", "Hearts"};
    String[] ranks = {"Ace","2","3","4","5","6","7","8","9","10","Jake","Queen","King"};
    int gameStart = 0;

    public Deck() {
        int index = 0;
        for(int i=0; i<suits.length; i++) {
            for (int j=0; j<ranks.length; j++) {
                this.cards[index] = new Cards(ranks[j], suits[i]);
                index++;
            }
        }
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

    public void Deal(int numCards, Player user, Player computer, Board board) {
        if (gameStart==0) {
            Shuffle();
            Cut();
            for (int i=0; i<numCards; i++) {
                user.addCardtoHand(this.cards[i]);
                computer.addCardtoHand(this.cards[i+numCards]);
                board.addCardToBoard(this.cards[i+2*numCards]);
            }
            gameStart++;
        } else {
            for (int i=0; i<numCards; i++) {
                user.addCardtoHand(this.cards[i]);
                computer.addCardtoHand(this.cards[i+numCards]);
            }
        }
    }
    
    public Cards[] getDeck() {
        return cards;
    }
}
