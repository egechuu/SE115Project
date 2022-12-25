import java.util.Random;

public class Deck {
    Cards[] cards = new Cards[52];
    String[] suits = {"Clubs", "Spades", "Diamonds", "Hearts"};
    String[] ranks = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
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
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i<52; i++) {
            int swapIndex = rand.nextInt(52 - i) + i;
            Cards temp = cards[i];
            cards[i] = cards[swapIndex];
            cards[swapIndex] = temp;
        }
    }

    public void Cut() {
        Random rand = new Random(System.currentTimeMillis());
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

    public void removeCardFromDeck(int[] dealtIndices) {
        Cards[] removeDeck = new Cards[this.cards.length-dealtIndices.length];
        int index = 0;
        boolean skipCard = false;
        for(int i=0; i<this.cards.length; i++) {
            skipCard = false;
            for (int j=0; j<dealtIndices.length; j++) {
                if (i==dealtIndices[j]) {
                    skipCard = true;
                    break;
                }
            }
            if(!skipCard) {
                removeDeck[index] = this.cards[i];
                index++;
            }
        }
        this.cards = removeDeck;
    }

    public void Deal(int numCards, Player user, Player computer, Board board) {
        if (gameStart==0) {
            Shuffle();
            Cut();
            int[] dealtIndices = new int[3*numCards];
            for (int i=0; i<3*numCards; i++) {
                if(i<numCards)
                    user.addCardtoHand(this.cards[i]);
                else if (i<2*numCards)
                    computer.addCardtoHand(this.cards[i]);
                else 
                    board.addCardToBoard(this.cards[i]);
                dealtIndices[i] = i;
            }
            removeCardFromDeck(dealtIndices);
            gameStart++;
        } else {
            int[] dealtIndices = new int[2*numCards];
            for (int i=0; i<2*numCards; i++) {
                if (i<numCards)
                    user.addCardtoHand(this.cards[i]);
                else
                    computer.addCardtoHand(this.cards[i]);  
                dealtIndices[i] =i;
            }
            removeCardFromDeck(dealtIndices);
        }
    }
    
    public Cards[] getDeck() {
        return cards;
    }
}
