public class Board {
    private Cards[] cards;

    public Board() {
        this.cards = new Cards[0];
    }

    public void addCardToBoard(Cards card) {
        Cards[] newCards = new Cards[this.cards.length+1];
        for(int i=0;i<this.cards.length;i++)
            newCards[i] = this.cards[i];
        newCards[newCards.length-1] = card;
        this.cards = newCards;
    }

    public void removeCardFromBoard(int numberOfCards) {
        Cards[] newCards = new Cards[this.cards.length-numberOfCards];
        for(int i=0; i<newCards.length; i++)
            newCards[i] = this.cards[i];
        this.cards = newCards;
    }

    public boolean canPlayOn(Cards card) {
        if(this.cards.length ==0) 
            return true;
        Cards topCard = this.getTopCard();
        if(topCard.getRank().equals(card.getRank()))
            return true;
        if(card.getRank().equals("Jack"))
            return true;
        return false;
    }

    public int getNumOfCards() {
        return this.cards.length;
    }

    public Cards getTopCard() {
        return this.cards[this.cards.length-1];
    }
}
